package nl.avans.ivh11.a2b.service.battle;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.ActionCommand;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("battleService")
@Repository
@Transactional
public class BattleService {

    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private Battle battle;
    private int turnCounter = 0;

    private Character character;
    private Enemy enemy;

    @Autowired
    public BattleService(CharacterRepository characterRepo, EnemyRepository enemyRepo) {
        this.characterRepository = characterRepo;
        this.enemyRepository = enemyRepo;
    }

    public void startBattle(Character c, Enemy e) {
        battle = new Battle();

        this.character = c;
        this.enemy = e;

        // Attach observers to subject
        this.character.attach(battle);
        this.enemy.attach(battle);
    }

    public Character findCharacter(long id) {
        return characterRepository.findOne(id);
    }

    public Enemy findEnemy(long id) {
        return enemyRepository.findOne(id);
    }

    public String battleAction() {
        String message = "";

        int oldHp = 0;
        int newHp = 0;
        int damage = 0;
        String attacker = "";
        String defender = "";

        // Validate who turns it is
        if(turnCounter % 2 == 0) {
            attacker = character.getName();
            defender = enemy.getName();
            oldHp = enemy.getStats().getCurrentHitpoints();

            // Character attacks enemy
            battle.playTurn(new ActionCommand(character, enemy));

            newHp = enemy.getStats().getCurrentHitpoints();

            //saveEnemy(enemy);

        } else {
            attacker = enemy.getName();
            defender = character.getName();
            oldHp = character.getStats().getCurrentHitpoints();

            // Enemy attacks character
            battle.playTurn(new ActionCommand(enemy, character));

            newHp = character.getStats().getCurrentHitpoints();

            //saveCharacter(character);
        }

        damage = oldHp - newHp;
        message = attacker +  " hits " + damage + " on " + defender;

        turnCounter++;

        // Update character and enemy
        return "test";
//        return battle.getNextMessage();
    }

    private void battleCharacterMove() {
        this.battle.playTurn(new ActionCommand(character, enemy));
    }

    private void battleEnemyMove() {
        this.battle.playTurn(new ActionCommand(enemy, character));
    }

    public void battleMove() {
        // let the character attack
        this.battleCharacterMove();

        // let the enemy attack
        this.battleEnemyMove();

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
        this.enemyRepository.save(this.enemy);
    }

    public Battle getBattle() {
        return this.battle;
    }
}