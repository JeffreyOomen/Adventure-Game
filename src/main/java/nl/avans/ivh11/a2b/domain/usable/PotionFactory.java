package nl.avans.ivh11.a2b.domain.usable;//package nl.avans.ivh11.a2b.domain.usable;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * PotionFactory concrete implementation of the UsableFactory
 * can create potion items.
 */
public class PotionFactory implements UsableFactory {

    /**
     * Not implemented for this PotionFactory used in EquipmentFactory.
     * @param type
     * @return
     */
    @Override
    public Usable createUsable(UsableType type, int level) {
       throw new NotImplementedException();
    }

    /**
     * createUsable
     * creates a new Potion
     * @param type UsableType
     * @return usable
     */
    @Override
    public Usable createUsable(UsableType type) {
        Usable usable = null;
        if (type == UsableType.POTION_HEAL) {
            usable = new HealPotion(type);
        } else if (type == UsableType.POTION_OVERLOAD) {
            usable = new OverloadPotion(type);
        }
        return usable;
    }
}
