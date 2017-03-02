package nl.avans.ivh11.a2b.domain.character;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.Entity;

/**
 * Represents a Character Race
 */
@Entity
@NoArgsConstructor
public class Troll extends Character
{
    private final static int STRENGTH_ACCURACY = 10;
    private final static int MAGIC_ACCURACY = 2;
    private final static int DEFENSE_ACCURACY = 2;
    private final static int ARCHERY_ACCURACY = 5;

    public Troll(String name, Stats stats) {
        super(name, stats);
        this.description = "A Troll";
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
