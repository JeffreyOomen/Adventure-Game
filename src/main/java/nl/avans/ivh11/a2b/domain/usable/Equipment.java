package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
//import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.CharacterStub; // TODO: stub used temporary

import javax.persistence.*;

/**
 * Equipment is the supertype of all the Equipment collections that can be collected e.g. Bandos.
 */
@Entity
@DiscriminatorValue("Equipment")
@Getter
@Setter
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
    public void use(CharacterStub character) {
        character.mountEquipment2(this.type, this); // TODO: change CharacterStub for normal Character (Stub contains new used UsableType enum instead of old)
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
     * setEnchantedStats
     * improves or decreases accuracy of this equipment.
     * @param strength
     * @param defense
     * @param archery
     * @param magic
     */
    protected void setEnchantedStats(double strength, double defense, double archery, double magic) {

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
