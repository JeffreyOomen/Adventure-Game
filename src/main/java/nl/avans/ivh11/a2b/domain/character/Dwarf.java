package nl.avans.ivh11.a2b.domain.character;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.Entity;

/**
 * Represents a Character Race
 */
@Entity
@NoArgsConstructor
public class Dwarf extends Character
{
    private final static int STRENGTH_ACCURACY = 15;
    private final static int MAGIC_ACCURACY = 7;
    private final static int DEFENSE_ACCURACY = 5;
    private final static int ARCHERY_ACCURACY = 3;

    public Dwarf(String name, Stats stats) {
        super(name, stats);
        this.description = "A dwarf";
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
