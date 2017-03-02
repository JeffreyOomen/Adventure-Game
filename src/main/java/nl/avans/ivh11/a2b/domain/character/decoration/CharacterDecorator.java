package nl.avans.ivh11.a2b.domain.character.decoration;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;

import javax.persistence.*;
import java.util.Map;

/**
 * With this class hierarchy, a Character can be decorated
 * with specialization in an attack style
 */
@Entity
@NoArgsConstructor
public abstract class CharacterDecorator extends Character
{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DECORATED_CHARACTER_ID")
    Character character; // this object will be decorated

    /**
     * Constructor
     * @param character an Object of Character
     */
    public CharacterDecorator(Character character) {
        this.character = character;
    }

    /**
     * Mounts the Character with an EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     * @param equipment an EquipmentRepository Object
     */
    public void mountEquipment(EquipmentEnum equipmentType, Equipment equipment) {
        this.character.mountEquipment(equipmentType, equipment);
    }

    /**
     * Unmounts the Character with the specified EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     */
    public void unMountEquipment(EquipmentEnum equipmentType) {
        this.character.unMountEquipment(equipmentType);
    }

    /**
     * Gets the Hitpoints amount
     * @return the Hitpoints amount
     */
    public int getHitpoints() {
        return this.character.getHitpoints();
    }

    /**
     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    public int getCurrentHitpoints() {
        return this.character.getCurrentHitpoints();
    }

    /**
     * Receive an incoming XP bounty
     */
    public void receiveXp(int earnedXp) {
        this.character.receiveXp(earnedXp);
    }

    /**
     * Sets the Attack Style
     * @param attackStyle the Attack Style to be used by the character
     */
    public void setAttackStyle(EquipmentEnum attackStyle) {
        this.character.setAttackStyle(attackStyle);
    }

    /**
     * Gets the Stats of the Character
     * @return the Stats of the Character
     */
    public Stats getStats() {
        return this.character.getStats();
    }

    /**
     * Gets the composed Character
     * @return the composer Character
     */
    public Character getCharacter() {
        return this.character;
    }

    /**
     * Gets the Map with Character Equipment
     * @return the Character Equipment
     */
    public Map<EquipmentEnum, Equipment> getEquipment() {
        return this.character.getEquipment();
    }
}
