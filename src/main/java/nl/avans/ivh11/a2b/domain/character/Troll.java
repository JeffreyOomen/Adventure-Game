package nl.avans.ivh11.a2b.domain.character;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.Entity;

/**
 * Represents a Character Race
 */
@Entity
@NoArgsConstructor
public class Troll extends Character
{
    private static final int STRENGTH_ACCURACY = 10;
    private static final int MAGIC_ACCURACY = 2;
    private static final int DEFENSE_ACCURACY = 2;
    private static final int ARCHERY_ACCURACY = 5;

    public Troll(String name, Stats stats, Media media) {
        super(name, stats, media);
        this.description = "A Troll";
    }

    @Override
    public int getStrengthAccuracy() {
        if(super.getStrengthAccuracy() + STRENGTH_ACCURACY > 100) {
            return 100;
        }
        return super.getStrengthAccuracy() + STRENGTH_ACCURACY;
    }

    @Override
    public int getMagicAccuracy() {
        if(super.getMagicAccuracy() + MAGIC_ACCURACY > 100) {
            return 100;
        }
        return super.getMagicAccuracy() + MAGIC_ACCURACY;
    }

    @Override
    public int getDefenseAccuracy() {
        if(super.getDefenseAccuracy() + DEFENSE_ACCURACY > 100) {
            return 100;
        }
        return super.getDefenseAccuracy() + DEFENSE_ACCURACY;
    }

    @Override
    public int getArcheryAccuracy() {
        if(super.getArcheryAccuracy() + ARCHERY_ACCURACY > 100) {
            return 100;
        }
        return super.getArcheryAccuracy() + ARCHERY_ACCURACY;
    }
}
