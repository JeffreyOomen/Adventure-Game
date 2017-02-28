package nl.avans.ivh11.a2b.domain.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents the Stats
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stats
{
    @Id
    @GeneratedValue
    private Long id;

    private int strength;
    private int strengthAccuracy;
    private double strengthTotalXp;
    private double strengthXpLeft;

    private int magic;
    private int magicAccuracy;
    private double magicTotalXp;
    private double magicXpLeft;

    private int defense;
    private int defenseAccuracy;
    private double defenseTotalXp;
    private double defenseXpLeft;

    private int archery;
    private int archeryAccuracy;
    private double archeryTotalXp;
    private double archeryXpLeft;

    private int hitpoints;
    private int currentHitpoints;
    private double hitpointsTotalXp;
    private double hitpointsXpLeft;

    private int level;
    private double levelXpLeft;
    private final static int BASE_XP = 200;

    private final static double STRENGTH_MULTIPLIER  = 0.6;
    private final static double MAGIC_MULTIPLIER     = 0.6;
    private final static double ARCHERY_MULTIPLIER   = 0.6;
    private final static double DEFENSE_MULTIPLIER   = 0.2;
    private final static double HITPOINTS_MULTIPLIER = 0.2;
    private final static double LEVEL_MULTIPLIER     = 1.5; // determines how fast levels are acquired

    private final static String STRENGTH  = "STRENGTH";
    private final static String MAGIC     = "MAGIC";
    private final static String DEFENSE   = "DEFENSE";
    private final static String ARCHERY   = "ARCHERY";
    private final static String HITPOINTS = "HITPOINTS";

    /**
     * Sieves the xp over the stats, based on the Character Attack Style
     *
     * @param characterAttackStyle whether the Character has a sword, staff or bow
     * @param xp the earned xp to be sieved over the stats
     * @return true if xp is sieved successfully, false otherwise
     */
    public boolean sieveXp(final EquipmentEnum characterAttackStyle, final int xp)
    {
        double earnedXp;
        switch (characterAttackStyle) {
            case SWORD:
                earnedXp = (STRENGTH_MULTIPLIER * xp);
                if (this.isLevelUp(this.getStrengthXpLeft(), earnedXp)) {
                    this.strengthTotalXp = this.calcXpLeft(this.getStrengthTotalXp(), this.getStrengthXpLeft(), earnedXp);
                    this.strength++;
                }
                this.strengthXpLeft = this.strengthTotalXp - this.calcXpRemaining(this.getStrengthXpLeft(), earnedXp);
                break;
            case STAFF:
                earnedXp = (MAGIC_MULTIPLIER * xp);
                if (this.isLevelUp(this.getMagicXpLeft(), earnedXp)) {
                    this.magicTotalXp = this.calcXpLeft(this.getMagicTotalXp(), this.getMagicXpLeft(), earnedXp);
                    this.magic++;
                }
                this.magicXpLeft = this.magicTotalXp - this.calcXpRemaining(this.getMagicXpLeft(), earnedXp);
                break;
            case BOW:
                earnedXp = (ARCHERY_MULTIPLIER * xp);
                if (this.isLevelUp(this.getArcheryXpLeft(), (ARCHERY_MULTIPLIER * xp))) {
                    this.archeryTotalXp = this.calcXpLeft(this.getArcheryTotalXp(), this.getArcheryXpLeft(), earnedXp);
                    this.archery++;
                }
                this.archeryXpLeft = this.archeryTotalXp - this.calcXpRemaining(this.getArcheryXpLeft(), earnedXp);
                break;
            default:
                return false;
        }

        earnedXp = (DEFENSE_MULTIPLIER * xp);
        if (this.isLevelUp(this.getDefenseXpLeft(), (DEFENSE_MULTIPLIER * xp))) {
            this.defenseTotalXp = this.calcXpLeft(this.getDefenseTotalXp(), this.getDefenseXpLeft(), (DEFENSE_MULTIPLIER * xp));
            this.defense++;
        }
        this.defenseXpLeft = this.defenseTotalXp - this.calcXpRemaining(this.getDefenseXpLeft(), earnedXp);

        earnedXp = (HITPOINTS_MULTIPLIER * xp);
        if (this.isLevelUp(this.getHitpointsXpLeft(), (HITPOINTS_MULTIPLIER * xp))) {
            this.hitpointsTotalXp = this.calcXpLeft(this.getHitpointsTotalXp(), this.getHitpointsXpLeft(), (HITPOINTS_MULTIPLIER * xp));
            this.hitpoints++;
        }
        this.hitpointsXpLeft = this.hitpointsTotalXp - this.calcXpRemaining(this.getHitpointsXpLeft(), earnedXp);

        return true;
    }

    /**
     * Subtracts the argument from the current Hitpoints
     *
     * @param hit the number which is getting subtracted
     */
    public void bearHit(int hit)
    {
        this.setCurrentHitpoints(this.getCurrentHitpoints() - hit);
    }

    /**
     * Calculates how much XP is left till the next level
     *
     * @param totalXp total xp needed for the next level
     * @param currentXp remaining xp from total needed for the next level
     * @param earnedXp the amount of earned xp, for example from killing a monster
     * @return the newly calculated xp which is needed for the next level
     */
    private double calcXpLeft(final double totalXp, final double currentXp, final double earnedXp)
    {
        double calculatedXp = currentXp - earnedXp;
        if (currentXp <= earnedXp) {
            calculatedXp = LEVEL_MULTIPLIER * (BASE_XP + Math.sqrt(totalXp));
        }

        return calculatedXp;
    }

    /**
     * Calculates how much XP remains after leveling up while still having
     * some earned XP left, this then can be subtracted from the newly
     * calculated XP left.
     *
     * @param currentXp remaining xp from total needed for the next level
     * @param earnedXp the amount of earned xp, for example from killing a monster
     * @return the amount of XP remaining
     */
    private double calcXpRemaining(final double currentXp, final double earnedXp)
    {
        double remainingXp = 0;
        if (currentXp < earnedXp) {
            remainingXp = earnedXp - currentXp;
        }

        return remainingXp;
    }

    /**
     * Determines if the stat will level up
     *
     * @param currentXp remaining xp from total xp needed for the next level
     * @param earnedXp the amount of earned xp, for example for killing a monster
     * @return true is level up, false otherwise
     */
    private boolean isLevelUp(final double currentXp, final double earnedXp)
    {
        return currentXp <= earnedXp;
    }
}
