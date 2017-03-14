package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.battle.Heal;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Elf;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        this.character = new Troll("Mountain Troll", stats);
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
//        Character elf = new Elf("Elf Opponent", new Stats());
//        character.setActionBehavior(new NormalAttack());
//        int oldHitpoints = elf.getCurrentHitpoints();
//
//        //Act
//        character.performAction(elf);
//
//        //Assert
//        assertEquals(oldHitpoints - 10, elf.getCurrentHitpoints());
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
