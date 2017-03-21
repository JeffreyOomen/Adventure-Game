package nl.avans.ivh11.a2b.domain.battle;

/**
 * Interface class to execute battle command
 */
@FunctionalInterface
public interface Command {
    /**
     * Execute battle command
     */
    void execute();
}
