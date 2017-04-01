package nl.avans.ivh11.a2b.domain.usable;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * BandosEquipment implementation of Equipment
 * BandosEquipment is great equipment for a 'Warrior' and improves strength and defense. It decreases archery and magic.
 */
@Entity
@NoArgsConstructor
public class BandosEquipment extends Equipment
{
    public BandosEquipment(UsableType type, int level) {
        super(type, level);
        setName("Bandos " + getEquipmentType(type)  + " (lvl " + level + ")");
        setDescription("Item from the Bandos collection. Very useful as a warrior.");
        imageUrl = "bandos" + getEquipmentType(type) + ".png";
        setEquipmentAccuracies(1.5, 1.2, 1, 1);
    }

}
