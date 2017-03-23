package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service("battleService")
@Repository
@Transactional
public class BattleServiceImpl implements BattleService
{
    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private OpponentService opponentService;
    private Battle battle;

    private Character character;
    private Enemy enemy;
    private List<Enemy> possibleEnemies;

    @PostConstruct
    public void init() {
        possibleEnemies = opponentService.findAllEnemies();
    }

    @Autowired
    public BattleServiceImpl(CharacterRepository characterRepo, EnemyRepository enemyRepo, OpponentService opponentService) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
        this.opponentService = opponentService;
    }

    /**
     * Starts a new battle between a Character and a Enemy
     * @param c the character to participate in the battle
     * @param e the enemy to participate in the battle
     */
    @Override
    public void startBattle(Character c, Enemy e) {
        battle = new Battle();

        this.character = c;
        this.enemy = e;

        // Attach observers to subject
        this.character.attach(battle);
        this.enemy.attach(battle);
    }

    public Enemy randomEnemy() {
        this.enemy = this.possibleEnemies.get(CustomRandom.getInstance().randomEnemy(this.possibleEnemies.size()));
        System.out.println("ALL GOOD");
        System.out.println(this.possibleEnemies.size());
        System.out.println(this.character.getName());
        this.enemy.setStats(CustomRandom.getInstance().randomEnemyStats(this.character));

        return this.enemy;
    }

    /**
     * Executes a battle event with a normal attack for the Character
     */
    @Override
    public void attack() {
        this.character.setActionBehavior(new NormalAttack());
        this.doAction();
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @Override
    public void specialAttack() {
        this.character.setActionBehavior(new SpecialAttack());
        this.doAction();
    }

    /**
     * Executes a battle event with a heal for the Character
     */
    @Override
    public void heal() {
        this.character.setActionBehavior(new Heal());
        this.doAction();
    }

    /**
     * Gets the current battle
     * @return An object of Battle
     */
    @Override
    public Battle getBattle() {
        return this.battle;
    }

    /**
     * Execute a battle action
     */
    private void doAction() {
        // let the character attack
        this.battle.playTurn(new ActionCommand(character, enemy));

        // let the enemy attack
        this.battle.playTurn(new ActionCommand(enemy, character));

        // save the battle state
        this.saveBattleState();
    }

    /**
     * Saves the state of the Character and the Enemy caused
     * by the Battle.
     */
    @Transactional
    public void saveBattleState() {
        this.characterRepository.save(this.character);
        //this.enemyRepository.save(this.enemy);
    }
}