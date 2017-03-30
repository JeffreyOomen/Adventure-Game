package nl.avans.ivh11.a2b.domain.usable;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Random;

/**
 * EquipmentFactory concrete implementation of the UsableFactory
 * can create Equipment items.
 */
public class EquipmentFactory implements UsableFactory {

    /**
     * createUsable
     * creates a Equipment object
     * @param type UsableType
     * @return Equipment
     */
    @Override
    public Usable createUsable(UsableType type, int level) {
        Usable usable;
        String equipmentSetName = getRandomEquipmentCollectionName();   // Randomly get a equipment collection name

        // Determine which collection item to return
        switch(equipmentSetName) {
            case "Armadyl":
                usable = new ArmadylEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Bandos":
                usable = new BandosEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Saradomin":
                usable = new SaradominEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Zamorak":
                usable = new ZamorakEquipment(type, getEquipmentCollectionLevel(level));
                break;
            default:
                usable = null;
        }
        return usable;
    }

    /**
     * Not implemented for this EquipmentFactory used in PotionFactory.
     * @param type
     * @return
     */
    @Override
    public Usable createUsable(UsableType type) {
        throw new NotImplementedException();
    }

    /**
     * getRandomEquipmentCollectionName
     * randomly generate a equipment collection name. Is Used in the createUsable (Factory Method) to generate a Equipment item.
     * @return name of the equipment collection
     */
    private String getRandomEquipmentCollectionName() {
        String[] possibleDropNames = new String[] {"Saradomin", "Zamorak", "Bandos", "Armadyl"};
        return possibleDropNames[new Random().nextInt(possibleDropNames.length)];
    }

    /**
     * getEquipmentCollectionLevel
     * is used to determine the equipment collection level based on level Enemy.
     * Based on the Enemy level this method can create collection levels where the remainder is '0' for i % 10.
     * @param enemyLevel int
     * @return equipment collection level
     */
    private int getEquipmentCollectionLevel(int enemyLevel) {
        int equipmentCollectionLevel = 5;
        for(int i = enemyLevel;  i > 10; i--) {
            // i % 10 remainder value = 0
            if((i % 10) == 0) {
                equipmentCollectionLevel = i;
            }
        }
        return equipmentCollectionLevel;
    }

}
