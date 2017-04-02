package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.battle.Heal;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        this.character = new Troll("Mountain Troll", stats, new Media("test.png", "test"));
        this.character.setInventory(new Inventory());
        this.character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
    }

    /**
     * Heal method
     */
    @Test
    public void healIncreasingHitpointsActionBehaviorTest() {
        //Arrange
        character.getStats().setCurrentHitpoints(5);
        character.getInventory().addUsable(new HealPotion(UsableType.POTION_HEAL));
        this.character.setActionBehavior(new Heal());
        int oldHitpoints = stats.getCurrentHitpoints();

        //Act
        this.character.performAction(character);

        // Assert
        assertTrue(character.getStats().getCurrentHitpoints() > oldHitpoints);

    }

    // TODO Check tests when Random is implemented
    /**
     * Heal method adds 10 hp
     */
    @Test
    public void healAboveMaxHitpointsNotAllowedActionBehaviorTest() {
        //Arrange
        character.getStats().setCurrentHitpoints(5);
        this.character.setActionBehavior(new Heal());
        int maxHitpoints = character.getHitpoints();

        Usable potion1 = new HealPotion(UsableType.POTION_HEAL);
        potion1.setId(1L);
        Usable potion2 = new HealPotion(UsableType.POTION_HEAL);
        potion1.setId(2L);
        Usable potion3 = new HealPotion(UsableType.POTION_HEAL);
        potion1.setId(3L);

        character.getInventory().addUsable(potion1);
        character.getInventory().addUsable(potion2);
        character.getInventory().addUsable(potion3);

        //Act
        this.character.performAction(character);
        this.character.performAction(character);
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
