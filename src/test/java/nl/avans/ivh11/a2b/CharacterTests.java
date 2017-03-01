package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Mage;
import nl.avans.ivh11.a2b.domain.character.Stats;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
    public void testCharacterInstantiating() {
        assertEquals(10, this.character.getStrengthAccuracy());
        assertEquals(2, this.character.getMagicAccuracy());
        assertEquals(2, this.character.getDefenseAccuracy());
        assertEquals(5, this.character.getArcheryAccuracy());
        assertEquals(50, this.character.getHitpoints());
        assertEquals(50, this.character.getCurrentHitpoints());
        assertEquals("A Troll", this.character.getDescription());
    }

    /**
     * Tests whether the right levels and XP's are calculated
     * when the XP is low, so that no level up's are present.
     */
    @Test
    public void testStatsReceiveXpNotLeveling() {
        this.character.receiveXp(180);
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
    public void testStatsReceiveXpLeveling() {
        this.character.receiveXp(1200);
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

    // ############################################################################################################
    // ###################################### Decorated Character Tests ######$$###################################
    // ############################################################################################################

    @Test
    public void testDecoratedCharacterInstantiating() {
        Character decoratedCharacter = new Mage(this.character);

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
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.STAFF);

        decoratedCharacter.receiveXp(180);
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
        Character decoratedCharacter = new Mage(this.character);
        decoratedCharacter.setAttackStyle(EquipmentEnum.BOW);

        this.character.receiveXp(1200);
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
}
