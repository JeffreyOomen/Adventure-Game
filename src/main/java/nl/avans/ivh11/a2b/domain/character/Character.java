package nl.avans.ivh11.a2b.domain.character;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.state.CharacterState;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.character.state.PoweredState;
import nl.avans.ivh11.a2b.domain.character.state.WeakenedState;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.*;
import nl.avans.ivh11.a2b.domain.usable.Equipment;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Player of the game.
 */
@Entity
@Table(name = "BASE_CHARACTER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CHARACTER_TYPE")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class Character extends Opponent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHARACTER_ID" )
    protected Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CHARACTER_EQUIPMENT", joinColumns = @JoinColumn(name = "CHARACTER_ID"))
    @MapKeyColumn(name = "EQUIPMENT_ENUM")
    @Column(name = "EQUIPMENT")
    protected Map<UsableType, Equipment> equipment;

    //@OneToOne
    @Transient
    protected Inventory inventory;

    @Transient
    protected UsableType attackStyle;

    @Lob
    protected CharacterState currentState;

    /**
     * Constructor
     * @param name the name of the Character
     * @param stats
     */
    public Character(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.equipment = new HashMap<>();
//        this.currentState = NormalState.getInstance();
    }

    /**
     * Mounts the Character with an EquipmentRepository Piece
     * @param usableType what kind of EquipmentRepository Piece
     * @param equipment an EquipmentRepository Object
     */
    public void mountEquipment(UsableType usableType, Equipment equipment) {
        // Make sure only one weapon can be equipped
        if (usableType == UsableType.EQUIPMENT_WEAPON_SWORD || usableType == UsableType.EQUIPMENT_WEAPON_STAFF || usableType == UsableType.EQUIPMENT_WEAPON_BOW) {
            this.equipment.put(UsableType.EQUIPMENT_WEAPON, equipment);
            this.attackStyle = usableType;
        } else {
            this.equipment.put(usableType, equipment);
        }
    }

    /**
     * Unmounts the Character with the specified EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     */
    public void unMountEquipment(UsableType equipmentType) {
        this.equipment.remove(equipmentType);
    }

    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    public void performAction(Opponent opponent) {
        String message = this.actionBehavior.action(this, opponent);
        notifyObservers(message);
    }

    /**
     * Gets the Strength Level
     * @return the Strength Level
     */
    public int getStrength() {
        int strength = this.currentState.getStrength();
        return strength + this.stats.getStrength();
    }

    /**
     * Gets the Magic Level
     * @return the Magic Level
     */
    public int getMagic() {
        int magic = this.currentState.getMagic();
        return magic + this.stats.getMagic();
    }

    /**
     * Gets the Defense Level
     * @return the Defense Level
     */
    public int getDefense() {
        int defense = this.currentState.getDefense();
        return defense + this.stats.getDefense();
    }

    /**
     * Gets the Archery Level
     * @return the Archery Level
     */
    public int getArchery() {
        int archery = this.currentState.getArchery();
        return archery + this.stats.getArchery();
    }

    /**
     * Gets the Hitpoints amount
     * @return the Hitpoints amount
     */
    public int getHitpoints() {
        return this.stats.getHitpoints();
    }

    /**
     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    public int getCurrentHitpoints()  {
        return this.stats.getCurrentHitpoints();
    }

    /**
     * Gets an instance of PoweredState
     * @return an instance of PoweredState
     */
    public CharacterState getPoweredState() {
        return PoweredState.getInstance();
    }

    /**
     * Gets an instance of NormalState
     * @return an instance of NormalState
     */
    public CharacterState getNormalState() {
        return NormalState.getInstance();
    }

    /**
     * Gets an instance of WeakenedState
     * @return an instance of WeakenedState
     */
    public CharacterState getWeakenedState() {
        return WeakenedState.getInstance();
    }

    /**
     * Adds the given hitpoints to the currentHitpoints
     * @param hitPoints int
     */
    public void heal(int hitPoints) {
        int newHitpoints = this.getCurrentHitpoints() + hitPoints;
        if(newHitpoints <= this.getHitpoints()) {
            this.stats.setCurrentHitpoints(newHitpoints);
        } else {
            this.stats.setCurrentHitpoints(this.stats.getHitpoints());
        }
    }

    /**
     * Receive an incoming XP bounty
     */
    public void receiveXp(int earnedXp) {
        this.stats.processXp(this.getAttackStyle(), earnedXp);
    }


    /**
     * Adds a Usable item to the Character's Inventory
     * @param usable an Object of Usable
     * @return true if added successfully, false otherwise (e.g. when full)
     */
//    public boolean addToInventory(Usable usable) {
//        return this.inventory.addUsable(usable);
//    }

    /**
     * Removes the specified Usable from the Character's Inventory
     * @param usable an Object of Usable
     * @return true if dropped successfully, false otherwise
     */
    public boolean dropFromInventory(Usable usable) {
        return this.inventory.drop(usable);
    }

    /**
     * Get the Character's Inventory
     * @return Inventory
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Gets the Map with Character Equipment
     * @return the Character Equipment
     */
    public Map<UsableType, Equipment> getEquipment() {
        return this.equipment;
    }

    /**
     * Gets the current Strength Accuracy
     * @return the current Strength Accuracy
     */
    public int getStrengthAccuracy() {
        int strengthAccuracy = this.stats.getStrengthAccuracy();

        if (!this.equipment.isEmpty()) {
            for (Map.Entry<UsableType, Equipment> entry : this.equipment.entrySet()) {
                strengthAccuracy += entry.getValue().getStrengthAccuracy();
            }
        }

        return strengthAccuracy;
    }

    /**
     * Gets the current Magic Accuracy
     * @return the current Magic Accuracy
     */
    public int getMagicAccuracy() {
        int magicAccuracy = this.stats.getMagicAccuracy();

        if (!this.equipment.isEmpty()) {
            for (Map.Entry<UsableType, Equipment> entry : this.equipment.entrySet()) {
                magicAccuracy += entry.getValue().getMagicAccuracy();
            }
        }

        return magicAccuracy;

    }

    /**
     * Gets the current Defense Accuracy
     * @return the current Defense Accuracy
     */
    public int getDefenseAccuracy() {
        int defenseAccuracy = this.stats.getDefenseAccuracy();

        if (!this.equipment.isEmpty()) {
            for (Map.Entry<UsableType, Equipment> entry : this.equipment.entrySet()) {
                defenseAccuracy += entry.getValue().getDefenseAccuracy();
            }
        }

        return defenseAccuracy;
    }

    /**
     * Gets the current Archery Accuracy
     * @return the current Archery Accuracy
     */
    public int getArcheryAccuracy() {
        int archeryAccuracy = this.stats.getArcheryAccuracy();

        if (!this.equipment.isEmpty()) {
            for (Map.Entry<UsableType, Equipment> entry : this.equipment.entrySet()) {
                archeryAccuracy += entry.getValue().getArcheryAccuracy();
            }
        }

        return archeryAccuracy;
    }

    /**
     * Sets the Character's State
     * @param state the new Character's State
     */
    public void setState(CharacterState state) {
        this.currentState = state;
    }
}
