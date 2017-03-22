package nl.avans.ivh11.a2b.domain.usable;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * ArmadylEquipment implementation of Equipment
 * ArmadylEquipment is great equipment for a 'Archer' and improves archery. It decreases strength, archery and magic.
 */
@Entity
@NoArgsConstructor
public class ArmadylEquipment extends Equipment {

    public ArmadylEquipment(UsableType type, int level) {
        super(type, level);
        setName("Armadyl " + getEquipmentType(type)  + " (lvl " + level + ")");
        setDescription("Item from the Armadyl collection. Very useful as a warrior.");
        setEquipmentAccuracies(0.4, 1.2, 4, 0);
    }

}