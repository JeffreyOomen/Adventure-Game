package nl.avans.ivh11.a2b.domain.character.decoration;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.state.CharacterState;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.character.state.PoweredState;
import nl.avans.ivh11.a2b.domain.character.state.WeakenedState;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import javax.persistence.*;
import java.util.List;
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
    @Override
    public void mountEquipment(UsableType equipmentType, Equipment equipment) {
        this.character.mountEquipment(equipmentType, equipment);
    }

    /**
     * Unmounts the Character with the specified EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     */
    @Override
    public void unMountEquipment(UsableType equipmentType) {
        this.character.unMountEquipment(equipmentType);
    }

    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    @Override
    public void performAction(Opponent opponent) {
        this.character.performAction(opponent);
    }

    /**
     * Gets the Strength Level
     * @return the Strength Level
     */
    @Override
    public int getStrength() {
        return this.character.getStrength();
    }

    /**
     * Gets the Magic Level
     * @return the Magic Level
     */
    @Override
    public int getMagic() {
        return this.character.getMagic();
    }

    /**
     * Gets the Defense Level
     * @return the Defense Level
     */
    @Override
    public int getDefense() {
        return this.character.getDefense();
    }

    /**
     * Gets the Archery Level
     * @return the Archery Level
     */
    @Override
    public int getArchery() {
        return this.character.getArchery();
    }

    /**
     * Gets the Hitpoints amount
     * @return the Hitpoints amount
     */
    @Override
    public int getHitpoints() {
        return this.character.getHitpoints();
    }

    /**
     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    @Override
    public int getCurrentHitpoints() {
        return this.character.getCurrentHitpoints();
    }

    /**
     * Gets an instance of PoweredState
     * @return an instance of PoweredState
     */
    @Override
    public CharacterState getPoweredState() {
        return this.character.getPoweredState();
    }

    /**
     * Gets an instance of NormalState
     * @return an instance of NormalState
     */
    @Override
    public CharacterState getNormalState() {
        return this.character.getNormalState();
    }

    /**
     * Gets an instance of WeakenedState
     * @return an instance of WeakenedState
     */
    @Override
    public CharacterState getWeakenedState() {
        return this.character.getWeakenedState();
    }

    /**
     * Sets the current Character state
     */
    @Override
    public void setState(CharacterState state) {
        this.character.setState(state);
    }

    /**
     * Determines if the Character is still alive
     * @return true if the Character is alive, false otherwise
     */
    @Override
    public boolean isAlive() {
        return this.character.isAlive();
    }

    /**
     * Bears an incoming hit from an Opponent
     */
    @Override
    public void takeDamage(int hit) {
        this.character.takeDamage(hit);
    }

    /**
     * Receive an incoming XP bounty
     */
    @Override
    public void receiveXp(int earnedXp) {
        this.character.receiveXp(earnedXp);
    }

    /**
     * Adds a Usable item to the Character's Inventory
     * @param usable an Object of Usable
     * @return true if added successfully, false otherwise (e.g. when full)
     */
//    public boolean addToInventory(Usable usable) {
//        return this.character.addToInventory(usable);
//    }

    /**
     * Removes the specified Usable from the Character's Inventory
     * @param usable an Object of Usable
     * @return true if dropped successfully, false otherwise
     */
//    public boolean dropFromInventory(Usable usable) {
//        return this.character.dropFromInventory(usable);
//    }

    /**
     * Gets the Map with Character Equipment
     * @return the Character Equipment
     */
    @Override
    public Map<UsableType, Equipment> getEquipment() {
        return this.character.getEquipment();
    }

    /**
     * Sets the Attack Style
     * @param attackStyle the Attack Style to be used by the character
     */
    @Override
    public void setAttackStyle(UsableType attackStyle) {
        this.character.setAttackStyle(attackStyle);
    }

    /**
     * Get the AttackStyle
     * @return UsableType
     */
    @Override
    public UsableType getAttackStyle() {
        return this.character.getAttackStyle();
    }

    /**
     * Gets the Stats of the Character
     * @return the Stats of the Character
     */
    @Override
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

    @Override
    public void setActionBehavior(ActionBehavior actionBehavior) {
        this.character.setActionBehavior(actionBehavior);
    }

    @Override
    public String getName() {
        return this.character.getName();
    }

    /**
     * Get the Character's Inventory
     * @return Inventory
     */
    public Inventory getInventory() {
        return this.character.getInventory();
    }

    /**
     * Regenerate the Opponent, this means that the hitpoints will be reset
     * to the maximum.
     */
    @Override
    public void regenerate() {
        this.character.regenerate();
    }

    /**
     * Attach an Observer
     * @param observer
     */
    @Override
    public void attach(Observer observer) {
        this.character.attach(observer);
    }

    /**
     * Detach an Observer
     * @param observer
     */
    @Override
    public void detach(Observer observer) {
        this.character.detach(observer);
    }

    /**
     * Notify all attached Observers and
     * push message
     * @param messages
     */
    @Override
    public void notifyObservers(List<String> messages) {
        this.character.notifyObservers(messages);
    }
}
