package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Equipment")
@Getter
@Setter
public abstract class Equipment extends Usable {

    protected UsableType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STATS_ID")
    protected Stats stats;

    public Equipment(UsableType type, int level) {
        super();
        this.name = name;
        this.description = description;
        this.type = type;
        this.stats = new Stats();
        stats.setLevel(level);
    }

    @Override
    public void use(Character character) {
//        character.setState(character.mountEquipment(this.type, this)); // TODO: change later, because mountEquipment currently contains another Enum type and not UsableType.
    }

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

    protected abstract void setEnchantedStats(double strength, double defense, double archery, double magic);

}
