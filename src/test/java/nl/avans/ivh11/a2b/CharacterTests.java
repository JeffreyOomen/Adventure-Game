package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.usable.BandosEquipment;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.character.Troll;
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
        this.character = new Troll("Mountain Troll", stats, null);
        this.character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
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
        assertEquals(10, this.character.getHitpoints());
        assertEquals(10, this.character.getCurrentHitpoints());
        assertEquals("A Troll", this.character.getDescription());
    }

    /**
     * Tests whether the Stats are calculated the right way when
     * the Character wear Equipment.
     */
    @Test
    public void statsWithEquipment() {
        // Arrange
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);

        // Act
        this.character.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.character.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.character.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);

        // Assert
        assertEquals(55, this.character.getStrengthAccuracy());
        assertEquals(32, this.character.getMagicAccuracy());
        assertEquals(38, this.character.getDefenseAccuracy());
        assertEquals(35, this.character.getArcheryAccuracy());
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
        assertEquals(89, character.getStats().getStrengthTotalXp(), 0);
        assertEquals(77, character.getStats().getStrengthXpLeft(), 0);
        assertEquals(4, character.getStats().getStrength(), 0);

        assertEquals(15, character.getStats().getMagicTotalXp(), 0);
        assertEquals(15, character.getStats().getMagicXpLeft(), 0);
        assertEquals(1, character.getStats().getMagic(), 0);

        assertEquals(29, character.getStats().getDefenseTotalXp(), 0);
        assertEquals(8, character.getStats().getDefenseXpLeft(), 0);
        assertEquals(2, character.getStats().getDefense(), 0);

        assertEquals(15, character.getStats().getArcheryTotalXp(), 0);
        assertEquals(15, character.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, character.getStats().getArchery(), 0);

        assertEquals(29, character.getStats().getHitpointsTotalXp(), 0);
        assertEquals(8, character.getStats().getHitpointsXpLeft(), 0);
        assertEquals(15, character.getStats().getHitpoints(), 0);
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
        assertEquals(385, character.getStats().getStrengthTotalXp(), 0);
        assertEquals(239, character.getStats().getStrengthXpLeft(), 0);
        assertEquals(7, character.getStats().getStrength(), 0);

        assertEquals(15, character.getStats().getMagicTotalXp(), 0);
        assertEquals(15, character.getStats().getMagicXpLeft(), 0);
        assertEquals(1, character.getStats().getMagic(), 0);

        assertEquals(148, character.getStats().getDefenseTotalXp(), 0);
        assertEquals(93, character.getStats().getDefenseXpLeft(), 0);
        assertEquals(5, character.getStats().getDefense(), 0);

        assertEquals(15, character.getStats().getArcheryTotalXp(), 0);
        assertEquals(15, character.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, character.getStats().getArchery(), 0);

        assertEquals(148, character.getStats().getHitpointsTotalXp(), 0);
        assertEquals(93, character.getStats().getHitpointsXpLeft(), 0);
        assertEquals(30, character.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether Equipment can be worn
     */
    @Test
    public void mountingEquipment() {
        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, 10);

        this.character.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.character.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.character.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.character.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.character.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.character.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, sword);

        // Assert
        assertEquals(6, this.character.getEquipment().size());
        assertEquals("Bandos helmet (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos boots (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_BOOTS).getName());
        assertEquals("Bandos gloves (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
        assertEquals("Bandos sword (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_WEAPON).getName());
    }

    /**
     * Tests whether only one Equipment piece per type is in the Equipment Map
     */
    @Test
    public void mountingMoreEquipment() {
        // Arrange
        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON, 10);

        this.character.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.character.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.character.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.character.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.character.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.character.mountEquipment(UsableType.EQUIPMENT_WEAPON, sword);

        Equipment torso_2 = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment bow = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_BOW, 10);

        // Act
        this.character.mountEquipment(UsableType.EQUIPMENT_BODY, torso_2);
        this.character.mountEquipment(UsableType.EQUIPMENT_WEAPON_BOW, bow);

        // Assert
        assertEquals(6, this.character.getEquipment().size());
        assertEquals("Bandos helmet (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos boots (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_BOOTS).getName());
        assertEquals("Bandos gloves (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
        assertEquals("Bandos bow (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_WEAPON).getName());
    }

    /**
     * Tests whether unmounting Equipment has the right behavior
     */
    @Test
    public void unmountingEquipment() {
        // Arrange
        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON, 10);

        this.character.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.character.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.character.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.character.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.character.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.character.mountEquipment(UsableType.EQUIPMENT_WEAPON, sword);

        // Act
        this.character.unMountEquipment(UsableType.EQUIPMENT_BOOTS);
        this.character.unMountEquipment(UsableType.EQUIPMENT_WEAPON);

        // Assert
        assertEquals(4, this.character.getEquipment().size());
        assertEquals("Bandos helmet (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos gloves (lvl 10)", this.character.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
    }

    /**
     * Tests whether hitpoints are lowered with the right amount when
     * the character gets hit.
     */
    @Test
    public void gettingHit() {
        // Act
        this.character.takeDamage(12);

        // Assert
        assertEquals(10, this.character.getHitpoints());
        assertEquals(0, this.character.getCurrentHitpoints());
    }

    @Test
    public void maxAccuracyConstraint() {
        // Act
        this.character.getStats().setStrengthAccuracy(300);

        // Assert
        assertEquals(310, this.character.getStrengthAccuracy());
    }

    @Test
    public void normalCharacterState() {
        // Arrange
        this.character.setState(this.character.getNormalState());

        // Assert
        assertEquals(1, this.character.getStrength());
        assertEquals(1, this.character.getMagic());
        assertEquals(1, this.character.getDefense());
        assertEquals(1, this.character.getArchery());
    }

    @Test
    public void weakenedCharacterState() {
        // Arrange
        this.character.setState(this.character.getWeakenedState());

        // Assert
        assertEquals(-9, this.character.getStrength());
        assertEquals(-9, this.character.getMagic());
        assertEquals(-14, this.character.getDefense());
        assertEquals(-9, this.character.getArchery());
    }

    @Test
    public void poweredCharacterState() {
        // Arrange
        this.character.setState(this.character.getPoweredState());

        // Assert
        assertEquals(11, this.character.getStrength());
        assertEquals(11, this.character.getMagic());
        assertEquals(16, this.character.getDefense());
        assertEquals(11, this.character.getArchery());
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN CALLED");
        this.character = null;
        this.stats = null;
    }
}
