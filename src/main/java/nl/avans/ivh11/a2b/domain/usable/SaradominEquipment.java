package nl.avans.ivh11.a2b.domain.usable;


/**
 * SaradominEquipment implementation of Equipment
 * SaradominEquipment is great equipment for a 'Mage' and improves mage. It decreases strength, defense and magic.
 */
public class SaradominEquipment extends Equipment {

    public SaradominEquipment(UsableType type, int level) {
        super(type, level);
        setName("Saradomin " + getEquipmentType(type));
        setDescription("Item from the Saradomin collection. Very useful as a warrior.");
        setEnchantedStats(0, 0.4, 0, 3);
    }

}
