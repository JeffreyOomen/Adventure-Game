package nl.avans.ivh11.a2b.domain.character;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.HashMap;

/**
 * Represents a Player of the game.
 */
public abstract class Character
{
    private String name;

    private String description;

    private HashMap<EquipmentEnum, Equipment> equipment;

    private ActionBehavior actionBehavior;

    private CharacterState currentState;

    /**
     * Mounts the Character with an Equipment Piece
     *
     * @param equipmentType what kind of Equipment Piece
     * @param equipment an Equipment Object
     */
    public void mountEquipment(EquipmentEnum equipmentType, Equipment equipment)
    {
        this.equipment.put(equipmentType, equipment);
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
}
