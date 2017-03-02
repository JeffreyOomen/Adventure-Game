package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// ############################################################################################################
// ###################################### Decorated Character Tests ###########################################
// ############################################################################################################
public class DecoratedCharacterTests
{
    private Character character;
    private Stats stats;

    @Before
    public void setup() {
        System.out.println("SETUP CALLED");
        this.stats = new Stats();
        this.character = new Troll("Mountain Troll", stats);
        this.character.setAttackStyle(EquipmentEnum.SWORD);
    }

    @Test
    public void testDecoratedCharacterInstantiating() {
        // Act
        Character decoratedCharacter = new Mage(this.character);

        // Assert
        assertEquals(10, decoratedCharacter.getStrengthAccuracy());
        assertEquals(22, decoratedCharacter.getMagicAccuracy());
        assertEquals(12, decoratedCharacter.getDefenseAccuracy());
        assertEquals(5, decoratedCharacter.getArcheryAccuracy());
        assertEquals(50, decoratedCharacter.getHitpoints());
        assertEquals(50, decoratedCharacter.getCurrentHitpoints());
        assertEquals("A Troll specialized in Magic", decoratedCharacter.getDescription());
    }

    /**
     * Tests whether a decorated Character still gets the right levels
     * and XP's when receiving some XP, too low for leveling.
     */
    @Test
    public void testDecoratedStatsReceiveXpNotLeveling() {
        // Arrange
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);

        // Act
        decoratedCharacter.receiveXp(180);

        // Assert
        assertEquals(200, decoratedCharacter.getStats().getStrengthTotalXp(), 0);
        assertEquals(200, decoratedCharacter.getStats().getStrengthXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getStrength(), 0);

        assertEquals(200, decoratedCharacter.getStats().getMagicTotalXp(), 0);
        assertEquals(92, decoratedCharacter.getStats().getMagicXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getMagic(), 0);

        assertEquals(200, decoratedCharacter.getStats().getDefenseTotalXp(), 0);
        assertEquals(164, decoratedCharacter.getStats().getDefenseXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getDefense(), 0);

        assertEquals(200, decoratedCharacter.getStats().getArcheryTotalXp(), 0);
        assertEquals(200, decoratedCharacter.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getArchery(), 0);

        assertEquals(200, decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
        assertEquals(164, decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
        assertEquals(50, decoratedCharacter.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether a decorated Character still gets the right levels and XP's
     * when receiving a high amount of XP causing multiple levels to go up.
     */
    @Test
    public void testDecoratedStatsReceiveXpLeveling() {
        // Arrange
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.BOW);

        // Act
        decoratedCharacter.receiveXp(1200);

        // Assert
        assertEquals(200, decoratedCharacter.getStats().getStrengthTotalXp(), 0);
        assertEquals(200, decoratedCharacter.getStats().getStrengthXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getStrength(), 0);

        assertEquals(200, decoratedCharacter.getStats().getMagicTotalXp(), 0);
        assertEquals(200, decoratedCharacter.getStats().getMagicXpLeft(), 0);
        assertEquals(1, decoratedCharacter.getStats().getMagic(), 0);

        assertEquals(322, decoratedCharacter.getStats().getDefenseTotalXp(), 0);
        assertEquals(282, decoratedCharacter.getStats().getDefenseXpLeft(), 0);
        assertEquals(2, decoratedCharacter.getStats().getDefense(), 0);

        assertEquals(510, decoratedCharacter.getStats().getArcheryTotalXp(), 0);
        assertEquals(312, decoratedCharacter.getStats().getArcheryXpLeft(), 0);
        assertEquals(3, decoratedCharacter.getStats().getArchery(), 0);

        assertEquals(322, decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
        assertEquals(282, decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
        assertEquals(55, decoratedCharacter.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether Equipment can be worn
     */
    @Test
    public void testDecoratedCharacterMountingEquipment() {
        // Arrange
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);

        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        // Act
        decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
        decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
        decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
        decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
        decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
        decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);

        // Assert
        assertEquals(6, decoratedCharacter.getEquipment().size());
        assertEquals("Bronze Helmet", decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Bronze Torso", decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Silver Boots", decoratedCharacter.getEquipment().get(EquipmentEnum.BOOTS).getName());
        assertEquals("Bronze Gloves", decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
        assertEquals("A Golden Sword", decoratedCharacter.getEquipment().get(EquipmentEnum.WEAPON).getName());
    }

    /**
     * Tests whether only one Equipment piece per type is in the Equipment Map
     */
    @Test
    public void testDecoratedCharacterMountingEquipmentWhenExists() {
        // Arrange
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);

        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
        decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
        decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
        decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
        decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
        decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);

        Equipment torso_2 = new Equipment("Golden Torso", EquipmentEnum.TORSO);
        Equipment bow = new Equipment("A Golden Bow", EquipmentEnum.BOW);

        // Act
        decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso_2);
        decoratedCharacter.mountEquipment(EquipmentEnum.STAFF, bow);

        // Assert
        assertEquals(6, decoratedCharacter.getEquipment().size());
        assertEquals("Bronze Helmet", decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Golden Torso", decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Silver Boots", decoratedCharacter.getEquipment().get(EquipmentEnum.BOOTS).getName());
        assertEquals("Bronze Gloves", decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
        assertEquals("A Golden Bow", decoratedCharacter.getEquipment().get(EquipmentEnum.WEAPON).getName());
    }

    /**
     * Tests whether unmounting Equipment has the right behavior
     */
    @Test
    public void testDecoratedCharacterUnmountingEquipment() {
        // Arrange
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);

        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
        decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
        decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
        decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
        decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
        decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);

        // Act
        decoratedCharacter.unMountEquipment(EquipmentEnum.BOOTS);
        decoratedCharacter.unMountEquipment(EquipmentEnum.WEAPON);

        // Assert
        assertEquals(4, decoratedCharacter.getEquipment().size());
        assertEquals("Bronze Helmet", decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Bronze Torso", decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Bronze Gloves", decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN CALLED");
        this.character = null;
        this.stats = null;
    }
}
