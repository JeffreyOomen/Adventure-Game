package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.util.Stats;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * EquipmentFactory concrete implementation of the UsableFactory
 * can create equipment items.
 */
public class EquipmentFactory implements UsableFactory {

    /**
     * createUsable
     * creates a potions object (not implemented in this factory)
     * @param type
     * @param name
     * @param description
     * @return NotImplementedException exception
     */
    @Override
    public Usable createUsable(UsableType type, String name, String description) {
        throw new NotImplementedException();
    }

    /**
     * createUsable
     * creates an Equipment object
     * @param type
     * @param name
     * @param description
     * @param stats necessary to give the item stats
     * @return
     */
    @Override
    public Usable createUsable(UsableType type, String name, String description, Stats stats) {
        Usable usable = null;

        // Validate given type exists in UsableType
        for(UsableType usableType : UsableType.values()) {
            if(usableType.equals(type)) {
                // type exists in UsableType - create new Equipment
                usable = new Equipment(name, description, type, stats);
            }
        }
        return usable;
    }

}
