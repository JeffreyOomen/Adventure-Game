package nl.avans.ivh11.a2b.service;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.battle.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Service("battleService")
@Repository
@Getter
@Setter
public class BattleServiceImpl implements BattleService
{
    private static final String BREAK = "<br/><br/>";

    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private UsableRepository usableRepository;
    private OpponentService opponentService;
    private Battle battle;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private CharacterService characterService;


    private List<Enemy> possibleEnemies;

    @PostConstruct
    public void init() {
        this.possibleEnemies = opponentService.findAllEnemies();
    }

    @Autowired
    public BattleServiceImpl(CharacterRepository characterRepo, EnemyRepository enemyRepo, UsableRepository usableRepo, OpponentService opponentService) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
        this.usableRepository = usableRepo;
        this.opponentService = opponentService;
    }

    /**
     * Setup a new Battle between two Opponent's. Setting up includes
     * making a Battle Object. This Battle Object will observe the actions
     * taking place on the Opponent's.
     * @param character an Opponent
     * @return a random enemy
     */
    @Override
    public Opponent setupBattle(Opponent character) {
        // generate a random enemy
        Opponent enemy = this.randomEnemy(character);

        // setup battle between character and enemy
        this.battle = new Battle(character, enemy);

        return enemy;
    }

    /**
     * Teardown the Battle between the two Opponent's. Tearing down includes
     * giving the Character XP, saving the state of both Character and Enemy
     * and clears any memory allocation.
     */
    private void teardownBattle() {
        this.saveBattleState(); // save current state
        this.battle = null; // clear memory allocation
    }

    /**
     * Report about the events happened in the battle during the player's actions.
     * @return A String containing battle report information
     */
    @Transactional
    public String battleReport() {
        String battleReport = "";

        List<String> messages = this.battle.getMessages();
        if (!this.battle.getEnemy().isAlive()) {
            // give out XP to the character
            this.battle.getCharacter().receiveXp(this.getBattle().getEnemy().getHitpoints());
            this.handleEnemyDrop(battleReport);
            messages = this.battle.getMessages(); // add any level up messages

            this.teardownBattle();
        }

        for (String message: messages) {
            battleReport += message + BREAK;
        }

        // clear messages to prevent duplicates
        if(this.battle != null) this.battle.getMessages().clear();

        return battleReport;
    }

    private String handleEnemyDrop(String battleReport) throws HibernateException {
        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            session.beginTransaction();

            Enemy enemy = (Enemy)this.battle.getEnemy();
            Character character = (Character) this.battle.getCharacter();

            Usable randomDrop = enemy.randomDrop();
            session.save(randomDrop);

            characterService.addInventoryItem(character, randomDrop);

            battleReport += enemy.getName() + " just dropped " + randomDrop.getName() + BREAK;

            // commit the transaction
            session.getTransaction().commit();
        } catch (HibernateException e1) {
            rollback(session);
            throw e1;
        } finally {
            close(session);
        }

        return battleReport;
    }

    public static void close(Session session) {
        try {
            session.close();
        } catch (HibernateException e) {
            System.err.println("Could not close the session: " + e);
        }
    }
    public static void rollback(Session session) {
        try {
            Transaction tx = session.getTransaction();
            tx.rollback();
        } catch (HibernateException e) {
            System.err.println("Could not rollback the session: " + e);
        }
    }

    /**
     * Executes a battle event with a normal attack for the Character
     */
    @Override
    public void attack() {
        this.battle.getCharacter().setActionBehavior(new NormalAttack());
        this.doAction();
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @Override
    public void specialAttack() {
        this.battle.getCharacter().setActionBehavior(new SpecialAttack());
        this.doAction();
    }

    /**
     * Executes a battle event with a heal for the Character
     */
    @Override
    public void heal() {
        this.battle.getCharacter().setActionBehavior(new Heal());
        this.doAction();
    }

    /**
     * Execute a battle action
     */
    private void doAction() {
        // let the character attack
        this.battle.playTurn(new ActionCommand(this.battle.getCharacter(), this.battle.getEnemy()));

        // let the enemy attack
        this.battle.playTurn(new ActionCommand(this.battle.getEnemy(), this.battle.getCharacter()));

        // save the battle state
        this.saveBattleState();
    }

    /**
     * Saves the state of the Character and the Enemy caused by the Battle.
     */
    @Transactional
    public void saveBattleState() {
        System.out.println("Think it will go wrong frm here");
        this.characterRepository.save((Character) this.battle.getCharacter());
        this.enemyRepository.save((Enemy) this.battle.getEnemy());
        System.out.println("Gone wrong...");
    }

    /**
     * Generates a random Opponent based on the Stats of the other Opponent.
     * @param character an Opponent
     * @return an randomly generated Opponent
     */
    private Opponent randomEnemy(Opponent character) {
        Opponent enemy = this.possibleEnemies.get(CustomRandom.getInstance().randomEnemy(this.possibleEnemies.size()));
        enemy.setStats(CustomRandom.getInstance().randomEnemyStats(character));

        return enemy;
    }
}