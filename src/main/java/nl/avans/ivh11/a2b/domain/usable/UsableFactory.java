package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.util.Stats;

/**
 * UsableFactory 'contract' that every Factory need to implement.
 */
public interface UsableFactory {

    /**
     * createUsable
     * used to create a potion
     * @param type
     * @param name
     * @param description
     * @return
     */
    Usable createUsable(UsableType type, String name, String description);

    /**
     * createUsable
     * used to create an equipment
     * @param type
     * @param name
     * @param description
     * @param stats necessary to give the item stats
     * @return
     */
    Usable createUsable(UsableType type, String name, String description, Stats stats);

}