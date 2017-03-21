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
    private static final int STRENGTH_ACCURACY = 15;
    private static final int MAGIC_ACCURACY = 7;
    private static final int DEFENSE_ACCURACY = 5;
    private static final int ARCHERY_ACCURACY = 3;

    public Dwarf(String name, Stats stats) {
        super(name, stats);
        this.description = "A dwarf";
    }

    @Override
    public int getStrengthAccuracy() {
        return super.getStrengthAccuracy() + STRENGTH_ACCURACY;
    }

    @Override
    public int getMagicAccuracy() {
        return super.getMagicAccuracy() + MAGIC_ACCURACY;
    }

    @Override
    public int getDefenseAccuracy() {
        return super.getDefenseAccuracy() + DEFENSE_ACCURACY;
    }

    @Override
    public int getArcheryAccuracy() {
        return super.getArcheryAccuracy() + ARCHERY_ACCURACY;
    }
}
