package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.util.Stats;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static nl.avans.ivh11.a2b.domain.usable.UsableType.POTION_HEAL;

/**
 * EquipmentFactory concrete implementation of the UsableFactory
 * can create potion items.
 */
public class PotionFactory implements UsableFactory {

    /**
     * createUsable
     * creates a potions object
     * @param type
     * @param name
     * @param description
     * @return NotImplementedException exception
     */
    @Override
    public Usable createUsable(UsableType type, String name, String description) {
        Usable usable = null;

        switch(type) {
            case POTION_HEAL:
                usable = new HealPotion(type, name, description);
            break;
            case POTION_OVERLOAD:
                usable = new OverloadPotion(type, name, description);
        }
        return usable;

    }

    /**
     * createUsable
     * creates an Equipment object (not implemented in this factory)
     * @param type
     * @param name
     * @param description
     * @return NotImplementedException exception

     */
    @Override
    public Usable createUsable(UsableType type, String name, String description, Stats stats) {
        throw new NotImplementedException();
    }

}
