package nl.avans.ivh11.a2b.domain.character;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.character.state.CharacterState;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.character.state.PoweredState;
import nl.avans.ivh11.a2b.domain.character.state.WeakenedState;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;

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
public abstract class Character implements Opponent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHARACTER_ID")
    protected Long id;

    protected String name;

    protected String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CHARACTER_EQUIPMENT", joinColumns = @JoinColumn(name = "CHARACTER_ID"))
    @MapKeyColumn(name = "EQUIPMENT_ENUM")
    @Column(name = "EQUIPMENT")
    protected Map<EquipmentEnum, Equipment> equipment;

    //@OneToOne
    @Transient
    protected Inventory inventory;

    @Transient
    protected EquipmentEnum attackStyle;

    @Lob
    protected ActionBehavior actionBehavior;

    //@OneToOne
    @Transient
    protected CharacterState currentState;

    //@OneToOne
    @Transient
    protected Stats stats;

    /**
     * Constructor
     * @param name the name of the Character
     * @param stats
     */
    public Character(String name, Stats stats) {
        this.name = name;
        this.stats = stats;
        this.equipment = new HashMap<>();
    }

    /**
     * Mounts the Character with an EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     * @param equipment an EquipmentRepository Object
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
     * Unmounts the Character with the specified EquipmentRepository Piece
     * @param equipmentType what kind of EquipmentRepository Piece
     */
    public void unMountEquipment(EquipmentEnum equipmentType) {
        this.equipment.remove(equipmentType);
    }

    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    public void performAction(Opponent opponent) {
        this.actionBehavior.action(opponent);
    }

    /**
     * Gets the Strength Level
     * @return the Strength Level
     */
    public int getStrength() {
        return this.stats.getStrength();
    }

    /**
     * Gets the Magic Level
     * @return the Magic Level
     */
    public int getMagic() {
        return this.stats.getMagic();
    }

    /**
     * Gets the Defense Level
     * @return the Defense Level
     */
    public int getDefense() {
        return this.stats.getDefense();
    }

    /**
     * Gets the Archery Level
     * @return the Archery Level
     */
    public int getArchery() {
        return this.stats.getArchery();
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
     * Set the current ActionBehavior
     * @param actionBehavior ActionBehavior to set
     */
    public void setActionBehavior(ActionBehavior actionBehavior) {
        this.actionBehavior = actionBehavior;
    }

    /**
     * Gets an instance of PoweredState
     * @return an instance of PoweredState
     */
    public CharacterState getPoweredState() {
        return new PoweredState();
    }

    /**
     * Gets an instance of NormalState
     * @return an instance of NormalState
     */
    public CharacterState getNormalState() {
        return new NormalState();
    }

    /**
     * Gets an instance of WeakenedState
     * @return an instance of WeakenedState
     */
    public CharacterState getWeakenedState() {
        return new WeakenedState();
    }

    /**
     * Sets the current Character state
     */
    public void setState(CharacterState state) {
        this.currentState = state;
    }

    /**
     * Determines if the Character is still alive
     * @return true if the Character is alive, false otherwise
     */
    public boolean isAlive() {
        return this.stats.getCurrentHitpoints() > 0;
    }

    /**
     * Take damage as result van an enemy attack
     * @param hit int damage to take
     */
    public void takeDamage(int hit) {
        this.stats.setCurrentHitpoints(this.getCurrentHitpoints() - hit);
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
//    public boolean dropFromInventory(Usable usable) {
//        return this.inventory.drop();
//    }

    /**
     * Gets the Map with Character Equipment
     * @return the Character Equipment
     */
    public Map<EquipmentEnum, Equipment> getEquipment() {
        return this.equipment;
    }

    /**
     * Gets the current Strength Accuracy
     * @return the current Strength Accuracy
     */
    public int getStrengthAccuracy() {
        int strengthAccuracy = this.stats.getStrengthAccuracy();

        if (!this.equipment.isEmpty()) {
            for (Map.Entry<EquipmentEnum, Equipment> entry : this.equipment.entrySet()) {
                strengthAccuracy += entry.getValue().getStats().getStrengthAccuracy();
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
            for (Map.Entry<EquipmentEnum, Equipment> entry : this.equipment.entrySet()) {
                magicAccuracy += entry.getValue().getStats().getMagicAccuracy();
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
            for (Map.Entry<EquipmentEnum, Equipment> entry : this.equipment.entrySet()) {
                defenseAccuracy += entry.getValue().getStats().getDefenseAccuracy();
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
            for (Map.Entry<EquipmentEnum, Equipment> entry : this.equipment.entrySet()) {
                archeryAccuracy += entry.getValue().getStats().getArcheryAccuracy();
            }
        }

        return archeryAccuracy;
    }
}
