package nl.avans.ivh11.a2b.domain.usable;


import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * SaradominEquipment implementation of Equipment
 * SaradominEquipment is great equipment for a 'Mage' and improves mage. It decreases strength, defense and magic.
 */
@Entity
@NoArgsConstructor
public class SaradominEquipment extends Equipment
{
    public SaradominEquipment(UsableType type, int level) {
        super(type, level);
        setName("Saradomin " + getEquipmentType(type)  + " (lvl " + level + ")");
        setDescription("Item from the Saradomin collection. Very useful as a warrior.");
        imageUrl = "saradomin" + getEquipmentType(type) + ".png";
        setEquipmentAccuracies(0, 0.4, 0, 3);
    }

}
