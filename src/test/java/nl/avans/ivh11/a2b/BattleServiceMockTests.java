package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.battle.AttackUtil;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.service.*;
import nl.avans.ivh11.a2b.domain.character.Character;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManagerFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

public class BattleServiceMockTests {
    private BattleService battleService;
    private EnemyService enemyService;
    private CharacterService characterService;

    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private UsableRepository usableRepository;

    private CustomRandom customRandom;

    private Character character;
    private Enemy enemy;

    /**
     * Setup the battle and service before attack
     */
    @Before
    public void setup() {
//        Mocking repositories and service
        characterRepository = mock(CharacterRepository.class);
        enemyRepository = mock(EnemyRepository.class);
        usableRepository = mock(UsableRepository.class);
        enemyService= mock(EnemyServiceImpl.class);
        characterService = mock(CharacterServiceImpl.class);
        customRandom = mock(CustomRandom.class);
//        Creating battleService
        battleService = new BattleServiceImplMock(characterRepository, enemyRepository, usableRepository, characterService, enemyService);
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
    }

    /**
     * Opponents attack eachother till one is dead
     */
    @Test
    public void AttackTest() {
        enemy.setActionBehavior(new NormalAttack());
        while(character.isAlive() && enemy.isAlive()) {
            battleService.attack();
        }
        if (enemy.isAlive()) {
            assertNotEquals(0, enemy.getCurrentHitpoints());
            assertEquals(0, character.getCurrentHitpoints());
        } else {
            assertTrue(character.getHitpoints() >= character.getCurrentHitpoints());
        }
    }

    /**
     * Teardown battle if one of the opponents is dead
     */
    @After
    public void battleReportTest() {
        battleService.battleReport();
    }
}
