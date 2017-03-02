package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// ############################################################################################################
// ######################################### Character Tests ##################################################
// ############################################################################################################
public class CharacterTests
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

    /**
     * Tests whether making an Object of Character got the right
     * beginner stats when being a Troll.
     */
    @Test
    public void statsWithoutEquipment() {
        // Assert
        assertEquals(10, this.character.getStrengthAccuracy());
        assertEquals(2, this.character.getMagicAccuracy());
        assertEquals(2, this.character.getDefenseAccuracy());
        assertEquals(5, this.character.getArcheryAccuracy());
        assertEquals(50, this.character.getHitpoints());
        assertEquals(50, this.character.getCurrentHitpoints());
        assertEquals("A Troll", this.character.getDescription());
    }

    /**
     * Tests whether the Stats are calculated the right way when
     * the Character wear Equipment.
     */
    @Test
    public void statsWithEquipment() {
        // Arrange
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);

        Stats torsoStats = new Stats();
        torsoStats.setStrengthAccuracy(10);
        torsoStats.setDefenseAccuracy(20);
        torso.setStats(torsoStats);

        Stats legsStats = new Stats();
        legsStats.setMagicAccuracy(5);
        legsStats.setDefenseAccuracy(1);
        legs.setStats(legsStats);

        Stats bootsStats = new Stats();
        bootsStats.setArcheryAccuracy(15);
        bootsStats.setDefenseAccuracy(3);
        boots.setStats(bootsStats);

        // Act
        this.character.mountEquipment(EquipmentEnum.TORSO, torso);
        this.character.mountEquipment(EquipmentEnum.LEGS, legs);
        this.character.mountEquipment(EquipmentEnum.BOOTS, boots);

        // Assert
        assertEquals(20, this.character.getStrengthAccuracy());
        assertEquals(7, this.character.getMagicAccuracy());
        assertEquals(26, this.character.getDefenseAccuracy());
        assertEquals(20, this.character.getArcheryAccuracy());
    }

    /**
     * Tests whether the right levels and XP's are calculated
     * when the XP is low, so that no level up's are present.
     */
    @Test
    public void statsProcessingLowXp() {
        // Act
        this.character.receiveXp(180);

        // Assert
        assertEquals(200, character.getStats().getStrengthTotalXp(), 0);
        assertEquals(92, character.getStats().getStrengthXpLeft(), 0);
        assertEquals(1, character.getStats().getStrength(), 0);

        assertEquals(200, character.getStats().getMagicTotalXp(), 0);
        assertEquals(200, character.getStats().getMagicXpLeft(), 0);
        assertEquals(1, character.getStats().getMagic(), 0);

        assertEquals(200, character.getStats().getDefenseTotalXp(), 0);
        assertEquals(164, character.getStats().getDefenseXpLeft(), 0);
        assertEquals(1, character.getStats().getDefense(), 0);

        assertEquals(200, character.getStats().getArcheryTotalXp(), 0);
        assertEquals(200, character.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, character.getStats().getArchery(), 0);

        assertEquals(200, character.getStats().getHitpointsTotalXp(), 0);
        assertEquals(164, character.getStats().getHitpointsXpLeft(), 0);
        assertEquals(50, character.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether the right levels and XP's are calculated
     * when the XP is high enough to level some skills.
     */
    @Test
    public void statsProcessingHighXp() {
        // Act
        this.character.receiveXp(1200);

        // Assert
        assertEquals(510, character.getStats().getStrengthTotalXp(), 0);
        assertEquals(312, character.getStats().getStrengthXpLeft(), 0);
        assertEquals(3, character.getStats().getStrength(), 0);

        assertEquals(200, character.getStats().getMagicTotalXp(), 0);
        assertEquals(200, character.getStats().getMagicXpLeft(), 0);
        assertEquals(1, character.getStats().getMagic(), 0);

        assertEquals(322, character.getStats().getDefenseTotalXp(), 0);
        assertEquals(282, character.getStats().getDefenseXpLeft(), 0);
        assertEquals(2, character.getStats().getDefense(), 0);

        assertEquals(200, character.getStats().getArcheryTotalXp(), 0);
        assertEquals(200, character.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, character.getStats().getArchery(), 0);

        assertEquals(322, character.getStats().getHitpointsTotalXp(), 0);
        assertEquals(282, character.getStats().getHitpointsXpLeft(), 0);
        assertEquals(55, character.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether Equipment can be worn
     */
    @Test
    public void mountingEquipment() {
        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        this.character.mountEquipment(EquipmentEnum.HELMET, helmet);
        this.character.mountEquipment(EquipmentEnum.TORSO, torso);
        this.character.mountEquipment(EquipmentEnum.LEGS, legs);
        this.character.mountEquipment(EquipmentEnum.BOOTS, boots);
        this.character.mountEquipment(EquipmentEnum.GLOVES, gloves);
        this.character.mountEquipment(EquipmentEnum.SWORD, sword);

        // Assert
        assertEquals(6, this.character.getEquipment().size());
        assertEquals("Bronze Helmet", this.character.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Bronze Torso", this.character.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", this.character.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Silver Boots", this.character.getEquipment().get(EquipmentEnum.BOOTS).getName());
        assertEquals("Bronze Gloves", this.character.getEquipment().get(EquipmentEnum.GLOVES).getName());
        assertEquals("A Golden Sword", this.character.getEquipment().get(EquipmentEnum.WEAPON).getName());
    }

    /**
     * Tests whether only one Equipment piece per type is in the Equipment Map
     */
    @Test
    public void mountingMoreEquipment() {
        // Arrange
        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        this.character.mountEquipment(EquipmentEnum.HELMET, helmet);
        this.character.mountEquipment(EquipmentEnum.TORSO, torso);
        this.character.mountEquipment(EquipmentEnum.LEGS, legs);
        this.character.mountEquipment(EquipmentEnum.BOOTS, boots);
        this.character.mountEquipment(EquipmentEnum.GLOVES, gloves);
        this.character.mountEquipment(EquipmentEnum.SWORD, sword);

        Equipment torso_2 = new Equipment("Golden Torso", EquipmentEnum.TORSO);
        Equipment bow = new Equipment("A Golden Bow", EquipmentEnum.BOW);

        // Act
        this.character.mountEquipment(EquipmentEnum.TORSO, torso_2);
        this.character.mountEquipment(EquipmentEnum.STAFF, bow);

        // Assert
        assertEquals(6, this.character.getEquipment().size());
        assertEquals("Bronze Helmet", this.character.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Golden Torso", this.character.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", this.character.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Silver Boots", this.character.getEquipment().get(EquipmentEnum.BOOTS).getName());
        assertEquals("Bronze Gloves", this.character.getEquipment().get(EquipmentEnum.GLOVES).getName());
        assertEquals("A Golden Bow", this.character.getEquipment().get(EquipmentEnum.WEAPON).getName());
    }

    /**
     * Tests whether unmounting Equipment has the right behavior
     */
    @Test
    public void unmountingEquipment() {
        // Arrange
        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);

        this.character.mountEquipment(EquipmentEnum.HELMET, helmet);
        this.character.mountEquipment(EquipmentEnum.TORSO, torso);
        this.character.mountEquipment(EquipmentEnum.LEGS, legs);
        this.character.mountEquipment(EquipmentEnum.BOOTS, boots);
        this.character.mountEquipment(EquipmentEnum.GLOVES, gloves);
        this.character.mountEquipment(EquipmentEnum.SWORD, sword);

        // Act
        this.character.unMountEquipment(EquipmentEnum.BOOTS);
        this.character.unMountEquipment(EquipmentEnum.WEAPON);

        // Assert
        assertEquals(4, this.character.getEquipment().size());
        assertEquals("Bronze Helmet", this.character.getEquipment().get(EquipmentEnum.HELMET).getName());
        assertEquals("Bronze Torso", this.character.getEquipment().get(EquipmentEnum.TORSO).getName());
        assertEquals("Silver Legs", this.character.getEquipment().get(EquipmentEnum.LEGS).getName());
        assertEquals("Bronze Gloves", this.character.getEquipment().get(EquipmentEnum.GLOVES).getName());
    }

    /**
     * Tests whether hitpoints are lowered with the right amount when
     * the character gets hit.
     */
    @Test
    public void gettingHit() {
        // Act
        this.character.bearHit(12);

        // Assert
        assertEquals(50, this.character.getHitpoints());
        assertEquals(38, this.character.getCurrentHitpoints());
    }

    @Test
    public void maxAccuracyConstraint() {
        // Act
        this.character.getStats().setStrengthAccuracy(300);

        // Assert
        assertEquals(310, this.character.getStrengthAccuracy());
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN CALLED");
        this.character = null;
        this.stats = null;
    }
}
