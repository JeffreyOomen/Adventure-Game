package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
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
public class Stats
{
    @Id
    @GeneratedValue
    private Long id;

    private int strength = 1;
    private int strengthAccuracy = 0;
    private double strengthTotalXp;
    private double strengthXpLeft;

    private int magic = 1;
    private int magicAccuracy = 0;
    private double magicTotalXp;
    private double magicXpLeft;

    private int defense = 1;
    private int defenseAccuracy = 0;
    private double defenseTotalXp;
    private double defenseXpLeft;

    private int archery = 1;
    private int archeryAccuracy = 0;
    private double archeryTotalXp;
    private double archeryXpLeft;

    private int hitpoints;
    private int currentHitpoints;
    private double hitpointsTotalXp;
    private double hitpointsXpLeft;

    private int level;
    private double levelXpLeft;

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
     * Constructor
     */
    public Stats() {
        this.strengthTotalXp  = 200;
        this.strengthXpLeft   = 200;

        this.magicTotalXp     = 200;
        this.magicXpLeft      = 200;

        this.defenseTotalXp   = 200;
        this.defenseXpLeft    = 200;

        this.archeryTotalXp   = 200;
        this.archeryXpLeft    = 200;

        this.hitpoints        = 50;
        this.currentHitpoints = 50;
        this.hitpointsTotalXp = 200;
        this.hitpointsXpLeft  = 200;
    }

    /**
     * Sieves the xp over the stats, based on the Character Attack Style
     * @param characterAttackStyle whether the Character has a sword, staff or bow
     * @param xp the earned xp to be sieved over the stats
     * @return true if xp is sieved successfully, false otherwise
     */
    public boolean sieveXp(final EquipmentEnum characterAttackStyle, final int xp) {

        switch (characterAttackStyle) {
            case SWORD:
                this.gainStrengthXp(STRENGTH_MULTIPLIER * xp);
                break;
            case STAFF:
                this.gainMagicXp(MAGIC_MULTIPLIER * xp);
                break;
            case BOW:
               this.gainArcheryXp(ARCHERY_MULTIPLIER * xp);
                break;
            default:
                return false;
        }

        this.gainDefenseXp(DEFENSE_MULTIPLIER * xp);
        this.gainHitpointsXp(HITPOINTS_MULTIPLIER * xp);

        return true;
    }

    /**
     * Subtracts the argument from the current Hitpoints
     * @param hit the number which is getting subtracted
     */
    public void bearHit(final int hit) {
        this.setCurrentHitpoints(this.getCurrentHitpoints() - hit);
    }

    /**
     * Handles gaining Strength XP, and possibly even levels
     * @param earnedXp the Strength XP gained
     */
    private void gainStrengthXp(double earnedXp) {
        while (earnedXp > 0) {
            if (earnedXp >= this.strengthXpLeft) {
                earnedXp -= this.strengthXpLeft;
                this.strengthTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.strengthTotalXp + Math.sqrt(this.strengthTotalXp)));
                this.strengthXpLeft = strengthTotalXp;
                this.strength++;
                continue;
            } else {
                this.strengthXpLeft -= earnedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Magic XP, and possibly even levels
     * @param earnedXp the Magic XP gained
     */
    private void gainMagicXp(double earnedXp) {
        while (earnedXp > 0) {
            if (earnedXp >= this.magicXpLeft) {
                earnedXp -= this.magicXpLeft;
                this.magicTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.magicTotalXp + Math.sqrt(this.magicTotalXp)));
                this.magicXpLeft = magicTotalXp;
                this.magic++;
                continue;
            } else {
                this.magicXpLeft -= earnedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Defense XP, and possibly even levels
     * @param earnedXp the Defense XP gained
     */
    private void gainDefenseXp(double earnedXp) {
        while (earnedXp > 0) {
            if (earnedXp >= this.defenseXpLeft) {
                earnedXp -= this.defenseXpLeft;
                this.defenseTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.defenseTotalXp + Math.sqrt(this.defenseTotalXp)));
                this.defenseXpLeft = defenseTotalXp;
                this.defense++;
                continue;
            } else {
                this.defenseXpLeft -= earnedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Archery XP, and possibly even levels
     * @param earnedXp the Archery XP gained
     */
    private void gainArcheryXp(double earnedXp) {
        while (earnedXp > 0) {
            if (earnedXp >= this.archeryXpLeft) {
                earnedXp -= this.archeryXpLeft;
                this.archeryTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.archeryTotalXp + Math.sqrt(this.archeryTotalXp)));
                this.archeryXpLeft = archeryTotalXp;
                this.archery++;
                continue;
            } else {
                this.archeryXpLeft -= earnedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Hitpoints XP, and possibly even levels
     * @param earnedXp the Hitpoints XP gained
     */
    private void gainHitpointsXp(double earnedXp) {
        while (earnedXp > 0) {
            if (earnedXp >= this.hitpointsXpLeft) {
                earnedXp -= this.hitpointsXpLeft;
                this.hitpointsTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.hitpointsTotalXp + Math.sqrt(this.hitpointsTotalXp)));
                this.hitpointsXpLeft = hitpointsTotalXp;
                this.hitpoints += 5;
                continue;
            } else {
                this.hitpointsXpLeft -= earnedXp;
                break;
            }
        }
    }

    // CharacterRepository make sure accuracy can only have a max value of say 75
}
