//package nl.avans.ivh11.a2b;
//
//import nl.avans.ivh11.a2b.domain.character.Character;
//import nl.avans.ivh11.a2b.domain.character.Troll;
//import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
//import nl.avans.ivh11.a2b.domain.util.Equipment;
//import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
//import nl.avans.ivh11.a2b.domain.util.Stats;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//// ############################################################################################################
//// ###################################### Decorated Character Tests ###########################################
//// ############################################################################################################
//public class DecoratedCharacterTests
//{
//    private Character decoratedCharacter;
//    private Stats stats;
//
//    @Before
//    public void setup() {
//        System.out.println("SETUP CALLED");
//        this.stats = new Stats();
//        this.decoratedCharacter = new Mage(new Troll("Mountain Troll", stats));
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.SWORD);
//    }
//
//    @Test
//    public void statsWithoutEquipment() {
//        // Assert
//        assertEquals(10, this.decoratedCharacter.getStrengthAccuracy());
//        assertEquals(22, this.decoratedCharacter.getMagicAccuracy());
//        assertEquals(12, this.decoratedCharacter.getDefenseAccuracy());
//        assertEquals(5, this.decoratedCharacter.getArcheryAccuracy());
//        assertEquals(50, this.decoratedCharacter.getHitpoints());
//        assertEquals(50, this.decoratedCharacter.getCurrentHitpoints());
//        assertEquals("A Troll specialized in Magic", decoratedCharacter.getDescription());
//    }
//
//    /**
//     * Tests whether the Stats are calculated the right way when
//     * the Character wear Equipment.
//     */
//    @Test
//    public void statsWithEquipment() {
//        // Arrange
//        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
//        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
//        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
//
//        Stats torsoStats = new Stats();
//        torsoStats.setStrengthAccuracy(10);
//        torsoStats.setDefenseAccuracy(20);
//        torso.setStats(torsoStats);
//
//        Stats legsStats = new Stats();
//        legsStats.setMagicAccuracy(5);
//        legsStats.setDefenseAccuracy(1);
//        legs.setStats(legsStats);
//
//        Stats bootsStats = new Stats();
//        bootsStats.setArcheryAccuracy(15);
//        bootsStats.setDefenseAccuracy(3);
//        boots.setStats(bootsStats);
//
//        // Act
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
//
//        // Assert
//        assertEquals(20, this.decoratedCharacter.getStrengthAccuracy());
//        assertEquals(27, this.decoratedCharacter.getMagicAccuracy());
//        assertEquals(36, this.decoratedCharacter.getDefenseAccuracy());
//        assertEquals(20, this.decoratedCharacter.getArcheryAccuracy());
//    }
//
//    /**
//     * Tests whether a decorated Character still gets the right levels
//     * and XP's when receiving some XP, too low for leveling.
//     */
//    @Test
//    public void statsProcessingLowXp() {
//        // Arrange
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);
//
//        // Act
//        this.decoratedCharacter.receiveXp(180);
//
//        // Assert
//        assertEquals(200, this.decoratedCharacter.getStats().getStrengthTotalXp(), 0);
//        assertEquals(200, this.decoratedCharacter.getStats().getStrengthXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getStrength(), 0);
//
//        assertEquals(200, this.decoratedCharacter.getStats().getMagicTotalXp(), 0);
//        assertEquals(92, this.decoratedCharacter.getStats().getMagicXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getMagic(), 0);
//
//        assertEquals(200, this.decoratedCharacter.getStats().getDefenseTotalXp(), 0);
//        assertEquals(164, this.decoratedCharacter.getStats().getDefenseXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getDefense(), 0);
//
//        assertEquals(200, this.decoratedCharacter.getStats().getArcheryTotalXp(), 0);
//        assertEquals(200, this.decoratedCharacter.getStats().getArcheryXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getArchery(), 0);
//
//        assertEquals(200, this.decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
//        assertEquals(164, this.decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
//        assertEquals(50, this.decoratedCharacter.getStats().getHitpoints(), 0);
//    }
//
//    /**
//     * Tests whether a decorated Character still gets the right levels and XP's
//     * when receiving a high amount of XP causing multiple levels to go up.
//     */
//    @Test
//    public void statsProcessingHighXp() {
//        // Arrange
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.BOW);
//
//        // Act
//        this.decoratedCharacter.receiveXp(1200);
//
//        // Assert
//        assertEquals(200, this.decoratedCharacter.getStats().getStrengthTotalXp(), 0);
//        assertEquals(200, this.decoratedCharacter.getStats().getStrengthXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getStrength(), 0);
//
//        assertEquals(200, this.decoratedCharacter.getStats().getMagicTotalXp(), 0);
//        assertEquals(200, this.decoratedCharacter.getStats().getMagicXpLeft(), 0);
//        assertEquals(1, this.decoratedCharacter.getStats().getMagic(), 0);
//
//        assertEquals(322, this.decoratedCharacter.getStats().getDefenseTotalXp(), 0);
//        assertEquals(282, this.decoratedCharacter.getStats().getDefenseXpLeft(), 0);
//        assertEquals(2, this.decoratedCharacter.getStats().getDefense(), 0);
//
//        assertEquals(510, this.decoratedCharacter.getStats().getArcheryTotalXp(), 0);
//        assertEquals(312, this.decoratedCharacter.getStats().getArcheryXpLeft(), 0);
//        assertEquals(3, this.decoratedCharacter.getStats().getArchery(), 0);
//
//        assertEquals(322, this.decoratedCharacter.getStats().getHitpointsTotalXp(), 0);
//        assertEquals(282, this.decoratedCharacter.getStats().getHitpointsXpLeft(), 0);
//        assertEquals(55, this.decoratedCharacter.getStats().getHitpoints(), 0);
//    }
//
//    /**
//     * Tests whether Equipment can be worn
//     */
//    @Test
//    public void mountingEquipment() {
//        // Arrange
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);
//
//        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
//        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
//        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
//        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
//        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
//        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);
//
//        // Act
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);
//
//        // Assert
//        assertEquals(6, this.decoratedCharacter.getEquipment().size());
//        assertEquals("Bronze Helmet", this.decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
//        assertEquals("Bronze Torso", this.decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
//        assertEquals("Silver Legs", this.decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
//        assertEquals("Silver Boots", this.decoratedCharacter.getEquipment().get(EquipmentEnum.BOOTS).getName());
//        assertEquals("Bronze Gloves", this.decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
//        assertEquals("A Golden Sword", this.decoratedCharacter.getEquipment().get(EquipmentEnum.WEAPON).getName());
//    }
//
//    /**
//     * Tests whether only one Equipment piece per type is in the Equipment Map
//     */
//    @Test
//    public void mountingMoreEquipment() {
//        // Arrange
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);
//
//        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
//        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
//        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
//        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
//        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
//        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);
//
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);
//
//        Equipment torso_2 = new Equipment("Golden Torso", EquipmentEnum.TORSO);
//        Equipment bow = new Equipment("A Golden Bow", EquipmentEnum.BOW);
//
//        // Act
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso_2);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.STAFF, bow);
//
//        // Assert
//        assertEquals(6, this.decoratedCharacter.getEquipment().size());
//        assertEquals("Bronze Helmet", this.decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
//        assertEquals("Golden Torso", this.decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
//        assertEquals("Silver Legs", this.decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
//        assertEquals("Silver Boots", this.decoratedCharacter.getEquipment().get(EquipmentEnum.BOOTS).getName());
//        assertEquals("Bronze Gloves", this.decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
//        assertEquals("A Golden Bow", this.decoratedCharacter.getEquipment().get(EquipmentEnum.WEAPON).getName());
//    }
//
//    /**
//     * Tests whether unmounting Equipment has the right behavior
//     */
//    @Test
//    public void unmountingEquipment() {
//        // Arrange
//        this.decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);
//
//        Equipment helmet = new Equipment("Bronze Helmet", EquipmentEnum.HELMET);
//        Equipment torso = new Equipment("Bronze Torso", EquipmentEnum.TORSO);
//        Equipment legs = new Equipment("Silver Legs", EquipmentEnum.LEGS);
//        Equipment boots = new Equipment("Silver Boots", EquipmentEnum.BOOTS);
//        Equipment gloves = new Equipment("Bronze Gloves", EquipmentEnum.GLOVES);
//        Equipment sword = new Equipment("A Golden Sword", EquipmentEnum.SWORD);
//
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.HELMET, helmet);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.TORSO, torso);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.LEGS, legs);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.BOOTS, boots);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.GLOVES, gloves);
//        this.decoratedCharacter.mountEquipment(EquipmentEnum.SWORD, sword);
//
//        // Act
//        this.decoratedCharacter.unMountEquipment(EquipmentEnum.BOOTS);
//        this.decoratedCharacter.unMountEquipment(EquipmentEnum.WEAPON);
//
//        // Assert
//        assertEquals(4, this.decoratedCharacter.getEquipment().size());
//        assertEquals("Bronze Helmet", this.decoratedCharacter.getEquipment().get(EquipmentEnum.HELMET).getName());
//        assertEquals("Bronze Torso", this.decoratedCharacter.getEquipment().get(EquipmentEnum.TORSO).getName());
//        assertEquals("Silver Legs", this.decoratedCharacter.getEquipment().get(EquipmentEnum.LEGS).getName());
//        assertEquals("Bronze Gloves", this.decoratedCharacter.getEquipment().get(EquipmentEnum.GLOVES).getName());
//    }
//
//    /**
//     * Tests whether hitpoints are lowered with the right amount when
//     * the character gets hit.
//     */
//    @Test
//    public void gettingHit() {
//        // Act
//        this.decoratedCharacter.takeDamage(12);
//
//        // Assert
//        assertEquals(50, this.decoratedCharacter.getHitpoints());
//        assertEquals(38, this.decoratedCharacter.getCurrentHitpoints());
//    }
//
//    @Test
//    public void maxAccuracyConstraint() {
//        // Act
//        this.decoratedCharacter.getStats().setStrengthAccuracy(300);
//
//        // Assert
//        assertEquals(310, this.decoratedCharacter.getStrengthAccuracy());
//    }
//
//    @Test
//    public void normalCharacterState() {
//        // Arrange
//        this.decoratedCharacter.setState(this.decoratedCharacter.getNormalState());
//
//        // Assert
//        assertEquals(1, this.decoratedCharacter.getStrength());
//        assertEquals(1, this.decoratedCharacter.getMagic());
//        assertEquals(1, this.decoratedCharacter.getDefense());
//        assertEquals(1, this.decoratedCharacter.getArchery());
//    }
//
//    @Test
//    public void weakenedCharacterState() {
//        // Arrange
//        this.decoratedCharacter.setState(this.decoratedCharacter.getWeakenedState());
//
//        // Assert
//        assertEquals(-9, this.decoratedCharacter.getStrength());
//        assertEquals(-9, this.decoratedCharacter.getMagic());
//        assertEquals(-14, this.decoratedCharacter.getDefense());
//        assertEquals(-9, this.decoratedCharacter.getArchery());
//    }
//
//    @Test
//    public void poweredCharacterState() {
//        // Arrange
//        this.decoratedCharacter.setState(this.decoratedCharacter.getPoweredState());
//
//        // Assert
//        assertEquals(11, this.decoratedCharacter.getStrength());
//        assertEquals(11, this.decoratedCharacter.getMagic());
//        assertEquals(16, this.decoratedCharacter.getDefense());
//        assertEquals(11, this.decoratedCharacter.getArchery());
//    }
//
//    @After
//    public void tearDown() {
//        System.out.println("TEARDOWN CALLED");
//        this.decoratedCharacter = null;
//        this.stats = null;
//    }
//}
