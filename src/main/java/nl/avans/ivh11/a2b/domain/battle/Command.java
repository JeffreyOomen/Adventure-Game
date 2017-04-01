package nl.avans.ivh11.a2b.domain.battle;

/**
 * Interface class to execute battle actions
 */
@FunctionalInterface
public interface Command
{
    /**
     * This method executes an Action as a Command
     */
    void execute();
}
