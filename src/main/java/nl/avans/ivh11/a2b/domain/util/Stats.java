package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.usable.UsableType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "Stats_Id")
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

    private int combatLevel = 1;

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

    private static final int AMOUNT_OF_LEVELS = 4;

    @Transient
    List<String> messages; // used to store logging messages

    /**
     * Constructor
     */
    public Stats() {
        this.strengthTotalXp  = 15;
        this.strengthXpLeft   = 15;

        this.magicTotalXp     = 15;
        this.magicXpLeft      = 15;

        this.defenseTotalXp   = 15;
        this.defenseXpLeft    = 15;

        this.archeryTotalXp   = 15;
        this.archeryXpLeft    = 15;

        this.hitpoints        = 10;
        this.currentHitpoints = 10;
        this.hitpointsTotalXp = 15;
        this.hitpointsXpLeft  = 15;
    }

    /**
     * Sieves the xp over the stats, based on the Character Attack Style
     * @param characterAttackStyle whether the Character has a sword, staff or bow
     * @param xp the earned xp to be sieved over the stats
     * @return true if xp is sieved successfully, false otherwise
     */
    public List<String> processXp(final UsableType characterAttackStyle, final int xp) {
        this.messages = new ArrayList<>();
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
                this.messages.add("XP Calculation Failed...");
                return messages;
        }

        this.processDefenseXp(DEFENSE_MULTIPLIER * xp);
        this.processHitpointsXp(HITPOINTS_MULTIPLIER * xp);

        return this.messages;
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
                this.processCombatLevel();
                this.messages.add("<span class=\"message-success\">Congratulations! Strength level up: " + this.strength + "</span>");
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
                this.processCombatLevel();
                this.messages.add("<span class=\"message-success\">Congratulations! Magic level up: " + this.magic + "</span>");
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
                this.processCombatLevel();
                this.messages.add("<span class=\"message-success\">Congratulations! Defense level up: " + this.defense + "</span>");
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
                this.processCombatLevel();
                this.messages.add("<span class=\"message-success\">Congratulations! Archery level up: " + this.archery + "</span>");
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
                this.messages.add("<span class=\"message-success\">Congratulations! Achieved more hitpoints: " + this.hitpoints + "</span>");
                continue;
            } else {
                this.hitpointsXpLeft -= gainedXp;
                break;
            }
        }
    }

    /**
     * Handles leveling up the combat level
     */
    private void processCombatLevel() {
        int totalLevels = 0;

        totalLevels += this.strength;
        totalLevels += this.defense;
        totalLevels += this.archery;
        totalLevels += this.magic;

        this.combatLevel = totalLevels / AMOUNT_OF_LEVELS;
    }

    /**
     * Set strengthAccuracy.
     * Can't be higher than 100%
     *
     * @param strengthAccuracy
     */
    public void setStrengthAccuracy(int strengthAccuracy) {
        if(strengthAccuracy > 100) {
            strengthAccuracy = 100;
        }
        this.strengthAccuracy = strengthAccuracy;
    }


    /**
     * Set magicAccuracy.
     * Can't be higher than 100%
     *
     * @param magicAccuracy
     */
    public void setMagicAccuracy(int magicAccuracy) {
        if(magicAccuracy > 100) {
            magicAccuracy = 100;
        }
        this.magicAccuracy = magicAccuracy;
    }


    /**
     * Set defenseAccuracy.
     * Can't be higher than 100%
     *
     * @param defenseAccuracy
     */
    public void setDefenseAccuracy(int defenseAccuracy) {
        if(defenseAccuracy > 100) {
            defenseAccuracy = 100;
        }
        this.defenseAccuracy = defenseAccuracy;
    }


    /**
     * Set archeryAccuracy.
     * Can't be higher than 100%
     *
     * @param archeryAccuracy
     */
    public void setArcheryAccuracy(int archeryAccuracy) {
        if(archeryAccuracy > 100) {
            archeryAccuracy = 100;
        }
        this.archeryAccuracy = archeryAccuracy;
    }
}
