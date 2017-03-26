package nl.avans.ivh11.a2b.domain.usable;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * ZamorakEquipment implementation of Equipment
 * ZamorakEquipment is great equipment for a 'Mage' and improves mage. It decreases strength, defense and magic.
 */
@Entity
@NoArgsConstructor
public class ZamorakEquipment extends Equipment {

    public ZamorakEquipment(UsableType type, int level) {
        super(type, level);
        setName("Zamorak " + getEquipmentType(type) + " (lvl " + level + ")");
        setDescription("Item from the Zamorak collection. Very useful as a warrior.");
        imageUrl = "zamorak" + getEquipmentType(type) + ".png";
        setEquipmentAccuracies(0, -0.8, 0, 6);
    }

}
