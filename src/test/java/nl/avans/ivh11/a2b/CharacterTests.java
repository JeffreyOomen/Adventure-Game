package nl.avans.ivh11.a2b;

import nl.avans.ivh11.a2b.domain.character.Character;
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
        assertEquals(200, this.stats.getStrengthTotalXp(), 0);
        assertEquals(92, this.stats.getStrengthXpLeft(), 0);
        assertEquals(1, this.stats.getStrength(), 0);

        assertEquals(200, this.stats.getMagicTotalXp(), 0);
        assertEquals(200, this.stats.getMagicXpLeft(), 0);
        assertEquals(1, this.stats.getMagic(), 0);

        assertEquals(200, this.stats.getDefenseTotalXp(), 0);
        assertEquals(164, this.stats.getDefenseXpLeft(), 0);
        assertEquals(1, this.stats.getDefense(), 0);

        assertEquals(200, this.stats.getArcheryTotalXp(), 0);
        assertEquals(200, this.stats.getArcheryXpLeft(), 0);
        assertEquals(1, this.stats.getArchery(), 0);

        assertEquals(200, this.stats.getHitpointsTotalXp(), 0);
        assertEquals(164, this.stats.getHitpointsXpLeft(), 0);
        assertEquals(50, this.stats.getHitpoints(), 0);
    }

    /**
     * Tests whether the right levels and XP's are calculated
     * when the XP is high enough to level some skills.
     */
    @Test
    public void testStatsReceiveXpLeveling() {
        this.character.receiveXp(1200);
        assertEquals(510, this.stats.getStrengthTotalXp(), 0);
        assertEquals(312, this.stats.getStrengthXpLeft(), 0);
        assertEquals(3, this.stats.getStrength(), 0);

        assertEquals(200, this.stats.getMagicTotalXp(), 0);
        assertEquals(200, this.stats.getMagicXpLeft(), 0);
        assertEquals(1, this.stats.getMagic(), 0);

        assertEquals(322, this.stats.getDefenseTotalXp(), 0);
        assertEquals(282, this.stats.getDefenseXpLeft(), 0);
        assertEquals(2, this.stats.getDefense(), 0);

        assertEquals(200, this.stats.getArcheryTotalXp(), 0);
        assertEquals(200, this.stats.getArcheryXpLeft(), 0);
        assertEquals(1, this.stats.getArchery(), 0);

        assertEquals(322, this.stats.getHitpointsTotalXp(), 0);
        assertEquals(282, this.stats.getHitpointsXpLeft(), 0);
        assertEquals(55, this.stats.getHitpoints(), 0);
    }
}
