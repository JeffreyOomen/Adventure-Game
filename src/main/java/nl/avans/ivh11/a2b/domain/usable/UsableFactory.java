package nl.avans.ivh11.a2b.domain.usable;

/**
 * UsableFactory 'contract' that every Factory need to implement.
 */
@FunctionalInterface
public interface UsableFactory {

    /**
     * createUsable
     * used to create a potion
     * @param type
     * @return
     */
    Usable createUsable(UsableType type, int level);

}