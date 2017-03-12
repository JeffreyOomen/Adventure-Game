package nl.avans.ivh11.a2b.domain.usable;


/**
 * ZamorakEquipment implementation of Equipment
 * ZamorakEquipment is great equipment for a 'Mage' and improves mage. It decreases strength, defense and magic.
 */
public class ZamorakEquipment extends Equipment {

    public ZamorakEquipment(UsableType type, int level) {
        super(type, level);
        setName("Zamorak " + getEquipmentType(type));
        setDescription("Item from the Zamorak collection. Very useful as a warrior.");
        setEnchantedStats(0, -0.8, 0, 6);
    }

}
