package nl.avans.ivh11.a2b.mock;

import lombok.Getter;
import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.battle.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Getter
@Repository
public class BattleServiceImplMock implements BattleService {
    private static final String BREAK = "<br/><br/>";
    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private UsableRepository usableRepository;
    private final CharacterService characterService;
    private final EnemyService enemyService;
    private Battle battle;

    @Autowired
    public BattleServiceImplMock(CharacterRepository characterRepo, EnemyRepository enemyRepo, UsableRepository usableRepo, CharacterService characterService, EnemyService enemyService) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
        this.usableRepository = usableRepo;
        this.characterService = characterService;
        this.enemyService = enemyService;
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
        }

        this.teardownBattle((Character) this.battle.getCharacter());

        // clear messages to prevent duplicates
        if(this.battle != null) {
            this.battle.getMessages().clear();
        }

        return battleReport;
    }

    /**
     * Executes a battle event with a normal attack for the Character
     */
    @Override
    public void attack() {
        this.battle.getCharacter().setActionBehavior(new NormalAttackMock());
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

    @Override
    public boolean hasOngoingBattle() {
        return false;
    }

    /**
     * Execute a battle action
     */
    private void doAction() {
        // let the character attack
        this.battle.playTurn(new ActionCommand(this.battle.getCharacter(), this.battle.getEnemy()));

        if (this.battle.getEnemy().isAlive()) {
            // let the enemy attack
            this.battle.playTurn(new ActionCommand(this.battle.getEnemy(), this.battle.getCharacter()));
        }

        Character character = (Character) this.battle.getCharacter();
        Random r = new Random();
        if (r.nextInt(20) == 1 && this.battle.getEnemy().isAlive() && this.battle.getCharacter().isAlive()) {
            List<String> messages = this.battle.getMessages();
            character.setState(character.getWeakenedState());
            messages.add(this.battle.getEnemy().getName() + " has just set you back to your weakened state");
            this.battle.setMessages(messages);
        }
    }

    /**
     * Generates a random Opponent based on the Stats of the other Opponent.
     * @param character an Opponent
     * @return an randomly generated Opponent
     */
    private Opponent randomEnemy(Opponent character) {
        List<Enemy> enemies = this.enemyService.findAll();
        Opponent enemy = enemies.get(CustomRandom.getInstance().randomEnemy(enemies.size()));
        enemy.setStats(CustomRandom.getInstance().randomEnemyStats(character));
        return enemy;
    }
}
