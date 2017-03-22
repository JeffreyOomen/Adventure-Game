package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.usable.UsableType;

import javax.persistence.Column;
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
    @Column(name = "STATS_ID")
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

    private static final double STRENGTH_MULTIPLIER  = 0.6;
    private static final double MAGIC_MULTIPLIER     = 0.6;
    private static final double ARCHERY_MULTIPLIER   = 0.6;
    private static final double DEFENSE_MULTIPLIER   = 0.2;
    private static final double HITPOINTS_MULTIPLIER = 0.2;
    private static final double LEVEL_MULTIPLIER     = 1.5; // determines how fast levels are acquired

    private static final String STRENGTH  = "STRENGTH";
    private static final String MAGIC     = "MAGIC";
    private static final String DEFENSE   = "DEFENSE";
    private static final String ARCHERY   = "ARCHERY";
    private static final String HITPOINTS = "HITPOINTS";

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
    public boolean processXp(final UsableType characterAttackStyle, final int xp) {

        switch (characterAttackStyle) {
            case EQUIPMENT_WEAPON_SWORD:
                this.processStrengthXp(STRENGTH_MULTIPLIER * xp);
                break;
            case EQUIPMENT_WEAPON_STAFF:
                this.processMagicXp(MAGIC_MULTIPLIER * xp);
                break;
            case EQUIPMENT_WEAPON_BOW:
               this.processArcheryXp(ARCHERY_MULTIPLIER * xp);
                break;
            default:
                return false;
        }

        this.processDefenseXp(DEFENSE_MULTIPLIER * xp);
        this.processHitpointsXp(HITPOINTS_MULTIPLIER * xp);

        return true;
    }

    /**
     * Handles gaining Strength XP, and possibly even levels
     * @param earnedXp the Strength XP gained
     */
    private void processStrengthXp(double earnedXp) {
        double gainedXp = earnedXp;
        while (gainedXp > 0) {
            if (gainedXp >= this.strengthXpLeft) {
                gainedXp -= this.strengthXpLeft;
                this.strengthTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.strengthTotalXp + Math.sqrt(this.strengthTotalXp)));
                this.strengthXpLeft = strengthTotalXp;
                this.strength++;
                continue;
            } else {
                this.strengthXpLeft -= gainedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Magic XP, and possibly even levels
     * @param earnedXp the Magic XP gained
     */
    private void processMagicXp(double earnedXp) {
        double gainedXp = earnedXp;
        while (gainedXp > 0) {
            if (gainedXp >= this.magicXpLeft) {
                gainedXp -= this.magicXpLeft;
                this.magicTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.magicTotalXp + Math.sqrt(this.magicTotalXp)));
                this.magicXpLeft = magicTotalXp;
                this.magic++;
                continue;
            } else {
                this.magicXpLeft -= gainedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Defense XP, and possibly even levels
     * @param earnedXp the Defense XP gained
     */
    private void processDefenseXp(double earnedXp) {
        double gainedXp = earnedXp;
        while (gainedXp > 0) {
            if (gainedXp >= this.defenseXpLeft) {
                gainedXp -= this.defenseXpLeft;
                this.defenseTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.defenseTotalXp + Math.sqrt(this.defenseTotalXp)));
                this.defenseXpLeft = defenseTotalXp;
                this.defense++;
                continue;
            } else {
                this.defenseXpLeft -= gainedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Archery XP, and possibly even levels
     * @param earnedXp the Archery XP gained
     */
    private void processArcheryXp(double earnedXp) {
        double gainedXp = earnedXp;
        while (gainedXp > 0) {
            if (gainedXp >= this.archeryXpLeft) {
                gainedXp -= this.archeryXpLeft;
                this.archeryTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.archeryTotalXp + Math.sqrt(this.archeryTotalXp)));
                this.archeryXpLeft = archeryTotalXp;
                this.archery++;
                continue;
            } else {
                this.archeryXpLeft -= gainedXp;
                break;
            }
        }
    }

    /**
     * Handles gaining Hitpoints XP, and possibly even levels
     * @param earnedXp the Hitpoints XP gained
     */
    private void processHitpointsXp(double earnedXp) {
        double gainedXp = earnedXp;
        while (gainedXp > 0) {
            if (gainedXp >= this.hitpointsXpLeft) {
                gainedXp -= this.hitpointsXpLeft;
                this.hitpointsTotalXp = Math.ceil(LEVEL_MULTIPLIER * (this.hitpointsTotalXp + Math.sqrt(this.hitpointsTotalXp)));
                this.hitpointsXpLeft = hitpointsTotalXp;
                this.hitpoints += 5;
                continue;
            } else {
                this.hitpointsXpLeft -= gainedXp;
                break;
            }
        }
    }
}
