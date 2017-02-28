package nl.avans.ivh11.a2b.domain.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import javax.persistence.Entity;
import java.util.HashMap;

/**
 * Represents a Player of the game.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public abstract class Character
{
    private String name;

    private String description;

    private HashMap<EquipmentEnum, Equipment> equipment;

    private EquipmentEnum attackStyle;

    private ActionBehavior actionBehavior;

    private CharacterState currentState;

    private Stats stats;

    /**
     * Mounts the Character with an Equipment Piece
     *
     * @param equipmentType what kind of Equipment Piece
     * @param equipment an Equipment Object
     */
    public void mountEquipment(EquipmentEnum equipmentType, Equipment equipment) {
        // Make sure only one weapon can be equipped
        if (equipmentType == EquipmentEnum.SWORD || equipmentType == EquipmentEnum.STAFF || equipmentType == EquipmentEnum.BOW) {
            this.equipment.put(EquipmentEnum.WEAPON, equipment);
            this.attackStyle = equipmentType;
        } else {
            this.equipment.put(equipmentType, equipment);
        }
    }

    /**
     * Unmounts the Character with the specified Equipment Piece
     *
     * @param equipmentType what kind of Equipment Piece
     * @param equipment an Equipment Object
     */
    public void unMountEquipment(EquipmentEnum equipmentType, Equipment equipment)
    {
        this.equipment.remove(equipmentType, equipment);
    }

    /**
     * Performs an action against the Opponent
     *
     * @param opponent the Character's Opponent
     */
    public void performAction(Opponent opponent)
    {
        // TODO
    }

    /**
     * Gets the Strength Level
     * @return the Strength Level
     */
    public int getStrength()
    {
        return this.stats.getStrength();
    }

    /**
     * Gets the Magic Level
     * @return the Magic Level
     */
    public int getMagic()
    {
        return this.stats.getMagic();
    }

    /**
     * Gets the Defense Level
     * @return the Defense Level
     */
    public int getDefense()
    {
        return this.stats.getDefense();
    }

    /**
     * Gets the Archery Level
     * @return the Archery Level
     */
    public int getArchery()
    {
        return this.stats.getArchery();
    }

    /**
     * Gets the Hitpoints amount
     * @return the Hitpoints amount
     */
    public int getHitpoints()
    {
        return this.stats.getHitpoints();
    }

    /**
     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    public int getCurrentHitpoints()
    {
        return this.stats.getCurrentHitpoints();
    }

    /**
     * Gets an instance of PoweredState
     * @return an instance of PoweredState
     */
    public CharacterState getPoweredState()
    {
        return new PoweredState();
    }

    /**
     * Gets an instance of NormalState
     * @return an instance of NormalState
     */
    public CharacterState getNormalState()
    {
        return new NormalState();
    }

    /**
     * Gets an instance of WeakenedState
     * @return an instance of WeakenedState
     */
    public CharacterState getWeakenedState()
    {
        return new WeakenedState();
    }

    /**
     * Sets the current Character State
     */
    public void setState(CharacterState state)
    {
        this.currentState = state;
    }

    /**
     * Determines if the Character is still alive
     * @return true if the Character is alive, false otherwise
     */
    public boolean isAlive()
    {
        return this.stats.getCurrentHitpoints() > 0;
    }

    /**
     * Bears an incoming hit from an Opponent
     */
    public void bearHit(int hit)
    {
        this.stats.bearHit(hit);
    }

    /**
     * Receive an incoming XP bounty
     */
    public void receiveXp(int earnedXp)
    {
        this.stats.sieveXp(this.getAttackStyle(), earnedXp);
    }
}
