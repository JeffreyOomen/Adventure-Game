package nl.avans.ivh11.a2b.domain.character.state;

/**
 * Represents the Weak State
 */
public class WeakenedState extends CharacterState
{
    private final static int STRENGTH_LEVEL = -10;
    private final static int MAGIC_LEVEL = -10;
    private final static int DEFENSE_LEVEL = -15;
    private final static int ARCHERY_LEVEL = -10;

    private volatile static WeakenedState uniqueInstance;

    // prevent instantiating through constructor
    private WeakenedState() {}

    /**
     * Creates a Singleton instance to prevent multiple objects
     * flying around the system.
     * @return in Singleton instance of this class
     */
    public static WeakenedState getInstance() {
        if (WeakenedState.uniqueInstance == null) {
            synchronized (PoweredState.class) {
                if (WeakenedState.uniqueInstance == null) {
                    WeakenedState.uniqueInstance = new WeakenedState();
                }
            }
        }
        return WeakenedState.uniqueInstance;
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
