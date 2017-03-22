package nl.avans.ivh11.a2b.domain.character.state;

/**
 * Represents the Normal State
 */
public class NormalState extends CharacterState
{
    private static final int STRENGTH_LEVEL = 0;
    private static final int MAGIC_LEVEL = 0;
    private static final int DEFENSE_LEVEL = 0;
    private static final int ARCHERY_LEVEL = 0;

    private static volatile NormalState uniqueInstance;

    // prevent instantiating through constructor
    private NormalState() {}

    /**
     * Creates a Singleton instance to prevent multiple objects
     * flying around the system.
     * @return in Singleton instance of this class
     */
    public static NormalState getInstance() {
        if (NormalState.uniqueInstance == null) {
            synchronized (NormalState.class) {
                if (NormalState.uniqueInstance == null) {
                    NormalState.uniqueInstance = new NormalState();
                }
            }
        }
        return NormalState.uniqueInstance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getStrength() {
        return STRENGTH_LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getMagic() {
        return MAGIC_LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDefense() {
        return DEFENSE_LEVEL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getArchery() {
        return ARCHERY_LEVEL;
    }
}
