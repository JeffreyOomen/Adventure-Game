package nl.avans.ivh11.a2b.domain.usable;

import java.util.Random;

/**
 * EquipmentFactory concrete implementation of the UsableFactory
 * can create equipment items.
 */
public class EquipmentFactory implements UsableFactory {

    /**
     * createUsable
     * creates a potions object (not implemented in this factory)
     * @param type
     * @return
     */
    @Override
    public Usable createUsable(UsableType type, int level) {
        Usable usable = null;
        String equipmentSetName = getRandomEquipmentCollectionName();
        equipmentSetName = "Saradomin";

        switch(equipmentSetName) {
            case "Armadyl":
                usable = new BandosEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Bandos":
                usable = new BandosEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Saradomin":
                usable = new BandosEquipment(type, getEquipmentCollectionLevel(level));
                break;
            case "Zamorak":
                usable = new BandosEquipment(type, getEquipmentCollectionLevel(level));
                break;
        }
        return usable;
    }

    private String getRandomEquipmentCollectionName() {
        String[] possibleDropNames = new String[] {"Saradomin", "Zamorak", "Bandos", "Armadyl"};
        return possibleDropNames[new Random().nextInt(possibleDropNames.length)];
    }

    private int getEquipmentCollectionLevel(int enemyLevel) {
        int equipmentCollectionLevel = 5;
        if(enemyLevel > 5 && enemyLevel <= 10) {
            equipmentCollectionLevel = 10;
        } else if(enemyLevel > 10 && enemyLevel <= 15) {
            equipmentCollectionLevel = 15;
        } else {
            equipmentCollectionLevel = 20;
        }
        return equipmentCollectionLevel;
    }

}
