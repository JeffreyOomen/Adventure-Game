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
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a Player of the game.
 */
@Entity
@Table(name = "Base_Character")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Character_Type")
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class Character extends Opponent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Character_Id" )
    protected Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Character_Equipment", joinColumns = @JoinColumn(name = "Character_Id"))
    @MapKeyColumn(name = "Equipment_Enum")
    @Column(name = "Equipment")
    protected Map<UsableType, Equipment> equipment;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Inventory inventory;

    protected UsableType attackStyle;

    @Lob
    protected CharacterState currentState;

    /**
     * Constructor
     * @param name the name of the Character
     * @param stats
     */
    public Character(String name, Stats stats, Media media) {
        this.name = name;
        this.stats = stats;
        this.media = media;
        this.inventory = new Inventory();
        this.equipment = new EnumMap<>(UsableType.class);
    }

    /**
     * Mounts the Character with an EquipmentRepository Piece
     * @param usableType what kind of EquipmentRepository Piece
     * @param equipment an EquipmentRepository Object
     */
    public void mountEquipment(UsableType usableType, Equipment equipment) {
        // First get old item for the given type
        Equipment currentMountEquipment = this.equipment.get(usableType);

        // Validate current mounted equipment found
        if(currentMountEquipment != null) {
            // unmount old equipment
            unMountEquipment(usableType);

            // Move item back to inventory
            this.inventory.addUsable(currentMountEquipment);
        }

        // Mount new equipment
        if (usableType == UsableType.EQUIPMENT_WEAPON_SWORD || usableType == UsableType.EQUIPMENT_WEAPON_STAFF || usableType == UsableType.EQUIPMENT_WEAPON_BOW) {
            this.equipment.put(UsableType.EQUIPMENT_WEAPON, equipment);
            this.attackStyle = usableType;
        } else {
            this.equipment.put(usableType, equipment);
        }

        // Remove item from inventory
        this.inventory.dropUsable(equipment);
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
    @Override
    public void performAction(Opponent opponent) {
        notifyObservers(this.actionBehavior.action(this, opponent));
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
     * Receive earned XP by killing an Opponent
     * @param earnedXp an Integer representing the XP earned
     */
    public void receiveXp(int earnedXp) {
        List<String> messages = this.stats.processXp(this.getAttackStyle(), earnedXp);
        notifyObservers(messages);
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

        return strengthAccuracy > 100 ? 100 : strengthAccuracy;
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

        return magicAccuracy > 100 ? 100 : magicAccuracy;

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

        return defenseAccuracy > 100 ? 100 : defenseAccuracy;
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

        return archeryAccuracy > 100 ? 100 : archeryAccuracy;
    }

    /**
     * Sets the Character's State
     * @param state the new Character's State
     */
    public void setState(CharacterState state) {
        this.currentState = state;
    }

    /**
     * Gets the level and accuracy in a Map based on the current
     * Attack Style of the Character
     * @return a Map which contains the level and accuracy of the skill
     * which belongs to the current Attack Style
     */
    public Map<String, Integer> getAttackStyleStats() {
        Map<String, Integer> map = new HashMap<>();

        switch (this.getAttackStyle()) {
            case EQUIPMENT_WEAPON_SWORD:
                map.put("AttackStyleLevel", this.getStrength());
                map.put("AttackStyleAccuracy", this.getStrengthAccuracy());
                break;
            case EQUIPMENT_WEAPON_STAFF:
                map.put("AttackStyleLevel", this.getMagic());
                map.put("AttackStyleAccuracy", this.getMagicAccuracy());
                break;
            case EQUIPMENT_WEAPON_BOW:
                map.put("AttackStyleLevel", this.getArchery());
                map.put("AttackStyleAccuracy", this.getArcheryAccuracy());
                break;
        }

        return map;
    }
}
