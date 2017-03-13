package nl.avans.ivh11.a2b.domain.usable;

/**
 * BandosEquipment implementation of Equipment
 * BandosEquipment is great equipment for a 'Warrior' and improves strength and defense. It decreases archery and magic.
 */
public class BandosEquipment extends Equipment {

    public BandosEquipment(UsableType type, int level) {
        super(type, level);
        setName("Bandos " + getEquipmentType(type));
        setDescription("Item from the Bandos collection. Very useful as a warrior.");
        setEquipmentAccuracies(1.5, 1.2, 1, 1);
    }

}
