package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.battle.*;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.service.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BattleServiceMockTests {
    private BattleService battleService;
    private EnemyService enemyService;
    private CharacterService characterService;

    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private UsableRepository usableRepository;

    private Character character;
    private Enemy enemy;

    private Battle battle;
    /**
     * Setup service
     */
    @Before
    public void setup() {
//        Mocking repositories and service
        characterRepository = mock(CharacterRepository.class);
        enemyRepository = mock(EnemyRepository.class);
        usableRepository = mock(UsableRepository.class);
        enemyService= mock(EnemyServiceImpl.class);
        characterService = mock(CharacterServiceImpl.class);
//        Creating battleService
        battleService = new BattleServiceImplMock(characterRepository, enemyRepository, usableRepository, characterService, enemyService);
    }

    /**
     * Setup battle before attack
     */
    @Before
    public void setupBattle() {
//        List of enemies for stub
        List<Enemy> listOfEnemies = new ArrayList<>();
        listOfEnemies.add(new Enemy(new Stats()));
        listOfEnemies.add(new Enemy(new Stats()));
        listOfEnemies.add(new Enemy(new Stats()));

//        Stub for method calling in repository
        when(enemyService.findAll()).thenReturn(listOfEnemies);

//        Make new Character
        character = new Dwarf("Jeffrey", new Stats(), new Media());
        character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
        character.setState(NormalState.getInstance());

//        Get the returned enemy from the battle that was set up
        enemy = (Enemy) battleService.setupBattle(character);
        enemy.setActionBehavior(new NormalAttackMock());

        battle = spy(battleService.getBattle());
    }

    /**
     * Testing a strong character fighting a weak enemy
     */
    @Test
    public void strongCharacterWeakEnemy() {
        character.getStats().setStrength(35);
        character.getStats().setDefense(8);
        enemy.getStats().setStrength(4);
        enemy.getStats().setDefense(3);

        int characterDamage = enemy.getStats().getStrength() - character.getStats().getDefense();
        int enemyDamage = character.getStats().getStrength() - enemy.getStats().getDefense();

        if (enemyDamage < 0) {
            enemyDamage = 0;
        } else if (enemyDamage > enemy.getCurrentHitpoints()) {
            enemyDamage = enemy.getCurrentHitpoints();
        }
        if (characterDamage < 0) {
            characterDamage = 0;
        } else if (characterDamage > character.getCurrentHitpoints()) {
            characterDamage = character.getCurrentHitpoints();
        }

        battleService.attack();

        assertEquals(3, battle.getMessages().size());
        assertEquals(0, enemy.getCurrentHitpoints());
        assertTrue(!enemy.isAlive());
        assertEquals(character.getHitpoints(), character.getCurrentHitpoints());
        assertTrue(character.isAlive());
        assertEquals(characterDamage, character.getHitpoints() - character.getCurrentHitpoints());
        assertEquals(enemyDamage, enemy.getHitpoints() - enemy.getCurrentHitpoints());
    }

    /**
     * Testing a strong character fighting a strong enemy
     */
    @Test
    public void strongCharacterStrongEnemy() {
        character.getStats().setStrength(8);
        character.getStats().setDefense(5);
        enemy.getStats().setStrength(9);
        enemy.getStats().setDefense(6);

        int characterDamage = enemy.getStats().getStrength() - character.getStats().getDefense();
        int enemyDamage = character.getStats().getStrength() - enemy.getStats().getDefense();

        if (enemyDamage < 0) {
            enemyDamage = 0;
        } else if (enemyDamage > enemy.getCurrentHitpoints()) {
            enemyDamage = enemy.getCurrentHitpoints();
        }
        if (characterDamage < 0) {
            characterDamage = 0;
        } else if (characterDamage > character.getCurrentHitpoints()) {
            characterDamage = character.getCurrentHitpoints();
        }

        battleService.attack();

        assertEquals(2, battle.getMessages().size());
        assertEquals(enemy.getHitpoints() - 2, enemy.getCurrentHitpoints());
        assertTrue(enemy.isAlive());
        assertEquals(character.getHitpoints() - 4, character.getCurrentHitpoints());
        assertTrue(character.isAlive());
        assertEquals(characterDamage, character.getHitpoints() - character.getCurrentHitpoints());
        assertEquals(enemyDamage, enemy.getHitpoints() - enemy.getCurrentHitpoints());
    }

    /**
     * Testing a weak character fighting a weak enemy
     */
    @Test
    public void weakCharacterWeakEnemy() {
        character.getStats().setStrength(5);
        character.getStats().setDefense(2);
        enemy.getStats().setStrength(4);
        enemy.getStats().setDefense(3);

        int characterDamage = enemy.getStats().getStrength() - character.getStats().getDefense();
        int enemyDamage = character.getStats().getStrength() - enemy.getStats().getDefense();

        if (enemyDamage < 0) {
            enemyDamage = 0;
        } else if (enemyDamage > enemy.getCurrentHitpoints()) {
            enemyDamage = enemy.getCurrentHitpoints();
        }
        if (characterDamage < 0) {
            characterDamage = 0;
        } else if (characterDamage > character.getCurrentHitpoints()) {
            characterDamage = character.getCurrentHitpoints();
        }

        battleService.attack();

        assertEquals(2, battle.getMessages().size());
        assertEquals(enemy.getHitpoints() - 2, enemy.getCurrentHitpoints());
        assertTrue(enemy.isAlive());
        assertEquals(character.getHitpoints() - 2, character.getCurrentHitpoints());
        assertTrue(character.isAlive());
        assertEquals(characterDamage, character.getHitpoints() - character.getCurrentHitpoints());
        assertEquals(enemyDamage, enemy.getHitpoints() - enemy.getCurrentHitpoints());
    }

    /**
     * Testing a weak character fighting a strong enemy
     */
    @Test
    public void weakCharacterStrongEnemy() {
        character.getStats().setStrength(5);
        character.getStats().setDefense(2);
        enemy.getStats().setStrength(40);
        enemy.getStats().setDefense(4);

        int characterDamage = enemy.getStats().getStrength() - character.getStats().getDefense();
        int enemyDamage = character.getStats().getStrength() - enemy.getStats().getDefense();

        if (enemyDamage < 0) {
            enemyDamage = 0;
        } else if (enemyDamage > enemy.getCurrentHitpoints()) {
            enemyDamage = enemy.getCurrentHitpoints();
        }
        if (characterDamage < 0) {
            characterDamage = 0;
        } else if (characterDamage > character.getCurrentHitpoints()) {
            characterDamage = character.getCurrentHitpoints();
        }

        battleService.attack();

        assertEquals(4, battle.getMessages().size());
        assertEquals(enemy.getHitpoints() - 1, enemy.getCurrentHitpoints());
        assertTrue(enemy.isAlive());
        assertEquals(0, character.getCurrentHitpoints());
        assertTrue(!character.isAlive());
        assertEquals(characterDamage, character.getHitpoints() - character.getCurrentHitpoints());
        assertEquals(enemyDamage, enemy.getHitpoints() - enemy.getCurrentHitpoints());
    }

    /**
     * Teardown battle if one of the opponents is dead
     */
    @After
    public void teardown() {
        battleService.battleReport();
    }
}
