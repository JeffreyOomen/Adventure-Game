package nl.avans.ivh11.a2b.domain.character;

/**
 * Represents a Character Race
 */
public class Elf extends Character
{
    private final static int STRENGTH_ACCURACY = 0;
    private final static int MAGIC_ACCURACY = 3;
    private final static int DEFENSE_ACCURACY = 1;
    private final static int ARCHERY_ACCURACY = 22;

    public Elf(Stats stats)  {
        this.stats = stats;
    }

    @Override
    public int getStrengthAccuracy() {
        return this.stats.getStrengthAccuracy() + STRENGTH_ACCURACY;
    }

    @Override
    public int getMagicAccuracy() {
        return this.stats.getMagicAccuracy() + MAGIC_ACCURACY;
    }

    @Override
    public int getDefenseAccuracy() {
        return this.stats.getDefenseAccuracy() + DEFENSE_ACCURACY;
    }

    @Override
    public int getArcheryAccuracy() {
        return this.stats.getArcheryAccuracy() + ARCHERY_ACCURACY;
    }
}