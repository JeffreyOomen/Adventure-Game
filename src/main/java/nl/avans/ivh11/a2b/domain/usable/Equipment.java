package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.*;

/**
 * Equipment is the supertype of all the Equipment collections that can be collected e.g. Bandos.
 */
@Entity
@DiscriminatorValue("Equipment")
@Getter
@Setter
@NoArgsConstructor
public abstract class Equipment extends Usable {

    protected UsableType type;
    @OneToOne(cascade = CascadeType.ALL)

    @Transient
    private int level;

    // These accuracies are used to set the accuracy of the equipment
    private int strengthAccuracy;
    private int defenseAccuracy;
    private int magicAccuracy;
    private int archeryAccuracy;

    public Equipment(UsableType type, int level) {
        super();
        this.name = null;
        this.description = null;
        this.type = type;
        this.level = level;
    }

    /**
     * use
     * let a character mount equipment
     * @param character
     */
    @Override
    public void use(Character character) {
        character.mountEquipment(this.type, this); // TODO: change CharacterStub for normal Character (Stub contains new used UsableType enum instead of old)
    }

    /**
     * getEquipmentType
     * based on the type used get the equipment name as a String. Is used to make a equipment name and description.
     * @param type UsableType enum
     * @return
     */
    public String getEquipmentType(UsableType type) {

        String equipmentType = null;
        switch(type) {
            case EQUIPMENT_BODY:
                equipmentType = "body";
                break;
            case EQUIPMENT_BOOTS:
                equipmentType = "boots";
                break;
            case EQUIPMENT_HELMET:
                equipmentType = "helmet";
                break;
            case EQUIPMENT_WEAPON_BOW:
                equipmentType = "sword";
                break;
            case EQUIPMENT_WEAPON_SWORD:
                equipmentType = "sword";
                break;
            case EQUIPMENT_WEAPON_STAFF:
                equipmentType = "staff";
                break;
            case EQUIPMENT_WEAPON:
                equipmentType = "weapon";
                break;
            case EQUIPMENT_LEGS:
                equipmentType = "legs";
                break;
            case EQUIPMENT_GLOVES:
                equipmentType = "gloves";
                break;
        }
        return equipmentType;
    }

    /**
     * setEquipmentAccuracies
     * necessary to set the accuracies of an equipment item. Is used to set these accuracies in Stats for a Character.
     * @param strength
     * @param defense
     * @param archery
     * @param magic
     */
    protected void setEquipmentAccuracies(double strength, double defense, double archery, double magic) {

        // Multiple character level with given amounts (can be positive or negative)
        int enchantedStrength = (int) Math.round(this.level * strength);
        int enchantedDefense = (int) Math.round(this.level * defense);
        int enchantedArchery = (int) Math.round(this.level * archery);
        int enchantedMagic = (int) Math.round(this.level * magic);

        this.setStrengthAccuracy(enchantedStrength);
        this.setDefenseAccuracy(enchantedDefense);
        this.setMagicAccuracy(enchantedArchery);
        this.setArcheryAccuracy(enchantedMagic);

    }

}
