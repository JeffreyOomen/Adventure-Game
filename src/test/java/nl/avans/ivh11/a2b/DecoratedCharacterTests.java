package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.usable.BandosEquipment;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
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
    private Character decoratedCharacter;
    private Stats stats;

    @Before
    public void setup() {
        System.out.println("SETUP CALLED");
        this.stats = new Stats();
        this.decoratedCharacter = new Mage(new Troll("Mountain Troll", stats, null));
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
    }

    @Test
    public void statsWithoutEquipment() {
        // Assert
        assertEquals(10, this.decoratedCharacter.getStrengthAccuracy());
        assertEquals(22, this.decoratedCharacter.getMagicAccuracy());
        assertEquals(12, this.decoratedCharacter.getDefenseAccuracy());
        assertEquals(5, this.decoratedCharacter.getArcheryAccuracy());
        assertEquals(50, this.decoratedCharacter.getHitpoints());
        assertEquals(50, this.decoratedCharacter.getCurrentHitpoints());
        assertEquals("A Troll specialized in Magic", decoratedCharacter.getDescription());
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
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);

        // Assert
        assertEquals(55, this.decoratedCharacter.getStrengthAccuracy());
        assertEquals(52, this.decoratedCharacter.getMagicAccuracy());
        assertEquals(48, this.decoratedCharacter.getDefenseAccuracy());
        assertEquals(35, this.decoratedCharacter.getArcheryAccuracy());
    }

    /**
     * Tests whether a decorated Character still gets the right levels
     * and XP's when receiving some XP, too low for leveling.
     */
    @Test
    public void statsProcessingLowXp() {
        // Arrange
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_STAFF);

        // Act
        this.decoratedCharacter.receiveXp(180);

        // Assert
        assertEquals(200, this.decoratedCharacter.getStats().getStrengthTotalXp(), 0);
        assertEquals(200, this.decoratedCharacter.getStats().getStrengthXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getStrength(), 0);

        assertEquals(200, this.decoratedCharacter.getStats().getMagicTotalXp(), 0);
        assertEquals(92, this.decoratedCharacter.getStats().getMagicXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getMagic(), 0);

        assertEquals(200, this.decoratedCharacter.getStats().getDefenseTotalXp(), 0);
        assertEquals(164, this.decoratedCharacter.getStats().getDefenseXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getDefense(), 0);

        assertEquals(200, this.decoratedCharacter.getStats().getArcheryTotalXp(), 0);
        assertEquals(200, this.decoratedCharacter.getStats().getArcheryXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getArchery(), 0);

        assertEquals(200, this.decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
        assertEquals(164, this.decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
        assertEquals(50, this.decoratedCharacter.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether a decorated Character still gets the right levels and XP's
     * when receiving a high amount of XP causing multiple levels to go up.
     */
    @Test
    public void statsProcessingHighXp() {
        // Arrange
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_BOW);

        // Act
        this.decoratedCharacter.receiveXp(1200);

        // Assert
        assertEquals(200, this.decoratedCharacter.getStats().getStrengthTotalXp(), 0);
        assertEquals(200, this.decoratedCharacter.getStats().getStrengthXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getStrength(), 0);

        assertEquals(200, this.decoratedCharacter.getStats().getMagicTotalXp(), 0);
        assertEquals(200, this.decoratedCharacter.getStats().getMagicXpLeft(), 0);
        assertEquals(1, this.decoratedCharacter.getStats().getMagic(), 0);

        assertEquals(322, this.decoratedCharacter.getStats().getDefenseTotalXp(), 0);
        assertEquals(282, this.decoratedCharacter.getStats().getDefenseXpLeft(), 0);
        assertEquals(2, this.decoratedCharacter.getStats().getDefense(), 0);

        assertEquals(510, this.decoratedCharacter.getStats().getArcheryTotalXp(), 0);
        assertEquals(312, this.decoratedCharacter.getStats().getArcheryXpLeft(), 0);
        assertEquals(3, this.decoratedCharacter.getStats().getArchery(), 0);

        assertEquals(322, this.decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
        assertEquals(282, this.decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
        assertEquals(55, this.decoratedCharacter.getStats().getHitpoints(), 0);
    }

    /**
     * Tests whether Equipment can be worn
     */
    @Test
    public void mountingEquipment() {
        // Arrange
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_STAFF);

        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, 10);

        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, sword);

        // Assert
        assertEquals(6, this.decoratedCharacter.getEquipment().size());
        assertEquals("Bandos helmet", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos boots", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_BOOTS).getName());
        assertEquals("Bandos gloves", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
        assertEquals("Bandos sword", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_WEAPON).getName());
    }

    /**
     * Tests whether only one Equipment piece per type is in the Equipment Map
     */
    @Test
    public void mountingMoreEquipment() {
        // Arrange
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_STAFF);

        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, 10);

        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, sword);

        Equipment torso_2 = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment bow = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_BOW, 10);

        // Act
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BODY, torso_2);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_WEAPON_STAFF, bow);

        // Assert
        assertEquals(6, this.decoratedCharacter.getEquipment().size());
        assertEquals("Bandos helmet", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos boots", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_BOOTS).getName());
        assertEquals("Bandos gloves", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
        assertEquals("Bandos bow", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_WEAPON).getName());
    }

    /**
     * Tests whether unmounting Equipment has the right behavior
     */
    @Test
    public void unmountingEquipment() {
        // Arrange
        this.decoratedCharacter.setAttackStyle(UsableType.EQUIPMENT_WEAPON_STAFF);

        Equipment helmet = new BandosEquipment(UsableType.EQUIPMENT_HELMET, 10);
        Equipment torso = new BandosEquipment(UsableType.EQUIPMENT_BODY, 10);
        Equipment legs = new BandosEquipment(UsableType.EQUIPMENT_LEGS, 10);
        Equipment boots = new BandosEquipment(UsableType.EQUIPMENT_BOOTS, 10);
        Equipment gloves = new BandosEquipment(UsableType.EQUIPMENT_GLOVES, 10);
        Equipment sword = new BandosEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, 10);

        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_HELMET, helmet);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BODY, torso);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_LEGS, legs);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_BOOTS, boots);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_GLOVES, gloves);
        this.decoratedCharacter.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, sword);

        // Act
        this.decoratedCharacter.unMountEquipment(UsableType.EQUIPMENT_BOOTS);
        this.decoratedCharacter.unMountEquipment(UsableType.EQUIPMENT_WEAPON);

        // Assert
        assertEquals(4, this.decoratedCharacter.getEquipment().size());
        assertEquals("Bandos helmet", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_HELMET).getName());
        assertEquals("Bandos body", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_BODY).getName());
        assertEquals("Bandos legs", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_LEGS).getName());
        assertEquals("Bandos gloves", this.decoratedCharacter.getEquipment().get(UsableType.EQUIPMENT_GLOVES).getName());
    }

    /**
     * Tests whether hitpoints are lowered with the right amount when
     * the character gets hit.
     */
    @Test
    public void gettingHit() {
        // Act
        this.decoratedCharacter.takeDamage(12);

        // Assert
        assertEquals(50, this.decoratedCharacter.getHitpoints());
        assertEquals(38, this.decoratedCharacter.getCurrentHitpoints());
    }

    @Test
    public void maxAccuracyConstraint() {
        // Act
        this.decoratedCharacter.getStats().setStrengthAccuracy(300);

        // Assert
        assertEquals(310, this.decoratedCharacter.getStrengthAccuracy());
    }

    @Test
    public void normalCharacterState() {
        // Arrange
        this.decoratedCharacter.setState(this.decoratedCharacter.getNormalState());

        // Assert
        assertEquals(1, this.decoratedCharacter.getStrength());
        assertEquals(1, this.decoratedCharacter.getMagic());
        assertEquals(1, this.decoratedCharacter.getDefense());
        assertEquals(1, this.decoratedCharacter.getArchery());
    }

    @Test
    public void weakenedCharacterState() {
        // Arrange
        this.decoratedCharacter.setState(this.decoratedCharacter.getWeakenedState());

        // Assert
        assertEquals(-9, this.decoratedCharacter.getStrength());
        assertEquals(-9, this.decoratedCharacter.getMagic());
        assertEquals(-14, this.decoratedCharacter.getDefense());
        assertEquals(-9, this.decoratedCharacter.getArchery());
    }

    @Test
    public void poweredCharacterState() {
        // Arrange
        this.decoratedCharacter.setState(this.decoratedCharacter.getPoweredState());

        // Assert
        assertEquals(11, this.decoratedCharacter.getStrength());
        assertEquals(11, this.decoratedCharacter.getMagic());
        assertEquals(16, this.decoratedCharacter.getDefense());
        assertEquals(11, this.decoratedCharacter.getArchery());
    }

    @After
    public void tearDown() {
        System.out.println("TEARDOWN CALLED");
        this.decoratedCharacter = null;
        this.stats = null;
    }
}
