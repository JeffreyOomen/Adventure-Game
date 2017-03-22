package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.battle.Heal;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Elf;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.enemy.Builder;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.usable.EquipmentFactory;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by matthijs on 8-3-17.
 */
public class ActionBehaviorTests
{

    private Character character;
    private Stats stats;

    @Before
    public void setup() {
        this.stats = new Stats();
        this.character = new Troll("Mountain Troll", stats, new Media("test.png"));
        this.character.setInventory(new Inventory());
        this.character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
    }

    /**
     * Heal method adds 10 hp
     */
    @Test
    public void healBelowMaxHitpointsActionBehaviorTest() {
        //Arrange
        character.getStats().setCurrentHitpoints(30);
        this.character.setActionBehavior(new Heal());
        int oldHitpoints = stats.getCurrentHitpoints();

        //Act
        this.character.performAction(character);

        //Assert
        assertEquals(oldHitpoints + 10, character.getStats().getCurrentHitpoints());
    }

    //TODO Check tests when Ranom is implemented
    /**
     * Heal method adds 10 hp
     */
    @Test
    public void healAboveMaxHitpointsActionBehaviorTest() {
        //Arrange
        character.getStats().setCurrentHitpoints(45);
        this.character.setActionBehavior(new Heal());
        int maxHitpoints = character.getHitpoints();

        //Act
        this.character.performAction(character);

        //Assert
        assertEquals(maxHitpoints, character.getStats().getCurrentHitpoints());
    }

    /**
     * NormalAttack does 10 damage
     */
    //UNABLE TO TEST BECAUSE RANDOM CLASS IS USED
//    @Test
//    public void performNormalAttack() {
//        //Arrange
//        this.character.setActionBehavior(new NormalAttack());
//
//        List<Usable> loot = new ArrayList<>();
//        loot.add(new EquipmentFactory().createUsable(UsableType.EQUIPMENT_BODY, this.character.getStats().getLevel()));
//
//        Builder builder = new EnemyBuilder();
//        EnemyBuilderDirector builderDirector = new EnemyBuilderDirector(builder);
//        Enemy enemy = builderDirector.createEnemy("Angry Snake", "A very angry snake", new NormalAttack(), new Stats(), loot);
//
//        System.out.println(this.character.getActionBehavior().toString());
//
//        // Act
//        this.character.performAction(enemy);
//        int oldHitpoints = enemy.getCurrentHitpoints();
//
//        //Assert
//        assertEquals(oldHitpoints - 10, enemy.getCurrentHitpoints());
//    }
//
//    /**
//     * NormalAttack does 20 damage
//     */
//    @Test
//    public void performSpecialAttack() {
//        //Arrange
//        Character elf = new Elf("Elf Opponent", new Stats());
//        character.setActionBehavior(new SpecialAttack());
//        int oldHitpoints = elf.getCurrentHitpoints();
//
//        //Act
//        character.performAction(elf);
//
//        //Assert
//        assertEquals(oldHitpoints - 20, elf.getCurrentHitpoints());
//    }

    @After
    public void tearDown() {
        this.character = null;
        this.stats = null;
    }
}
