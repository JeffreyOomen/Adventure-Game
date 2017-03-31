package nl.avans.ivh11.a2b.service;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.battle.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
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

    @Autowired
    private CharacterService characterService;
    @Autowired
    private OpponentService opponentService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private Battle battle;
    private List<Enemy> possibleEnemies;

    @PostConstruct
    public void init() {
        this.possibleEnemies = opponentService.findAllEnemies();
    }

    @Autowired
    public BattleServiceImpl(CharacterRepository characterRepo, EnemyRepository enemyRepo, UsableRepository usableRepo) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
        this.usableRepository = usableRepo;
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
    private void teardownBattle(Character character) {
        character.setState(character.getNormalState());
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

        //First add attack messages before adding possible victory messages
        for (String message: this.battle.getMessages()) {
            battleReport += message + BREAK;
        }
        this.battle.getMessages().clear();

        if (!this.battle.getEnemy().isAlive()) {
            // give out XP to the character
            this.battle.getCharacter().receiveXp(this.getBattle().getEnemy().getHitpoints());

            for (String message: this.battle.getMessages()) {
                battleReport += message + BREAK;
            }

            battleReport += this.handleEnemyDrop();

            this.teardownBattle((Character) this.battle.getCharacter());
        }

        // clear messages to prevent duplicates
        if(this.battle != null) {
            this.battle.getMessages().clear();
        }

        return battleReport;
    }

    /**
     * Programmatically (without using declarative approach @Transactional) handle
     * an Enemy drop. This includes randomly creating a drop, assigning it to a
     * Character, and logging to the battle report.
     * @return a modified battle report
     * @throws HibernateException when something goes wrong
     */
    private String handleEnemyDrop() throws HibernateException {
        String battleReport = "";
        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            session.beginTransaction();

            Enemy enemy = (Enemy)this.battle.getEnemy();
            Character character = (Character) this.battle.getCharacter();

            Usable randomDrop = enemy.randomDrop();
            session.save(randomDrop);

            characterService.addInventoryItem(character, randomDrop);

            battleReport += "<span class=\"message-warning\">" + enemy.getName() + " just dropped " + randomDrop.getName() + BREAK;

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
    private void saveBattleState() {
        Session session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
        try {
            session.beginTransaction();

            Enemy enemy = (Enemy)this.battle.getEnemy();
            Character character = (Character) this.battle.getCharacter();

            session.saveOrUpdate(enemy);
            session.saveOrUpdate(character);

            // commit the transaction
            session.getTransaction().commit();
        } catch (HibernateException e1) {
            rollback(session);
            throw e1;
        } finally {
            close(session);
        }
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

    /**
     * Closes the Hibernate Session Connection
     * @param session a session in which a transaction is started
     */
    private static void close(Session session) {
        try {
            session.close();
        } catch (HibernateException e) {
            System.err.println("Could not close the session: " + e);
        }
    }

    /**
     * Rollback the Transaction which happened inside the Session.
     * This must be done when a Transaction has failed.
     * @param session a session in which a transaction is started
     */
    private static void rollback(Session session) {
        try {
            Transaction tx = session.getTransaction();
            tx.rollback();
        } catch (HibernateException e) {
            System.err.println("Could not rollback the session: " + e);
        }
    }
}