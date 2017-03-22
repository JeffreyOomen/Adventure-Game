package nl.avans.ivh11.a2b.domain.character.state;

import javax.persistence.Entity;

/**
 * Represents the Powered State
 */
@Entity
public class PoweredState extends CharacterState
{
    private static final int STRENGTH_LEVEL = 10;
    private static final int MAGIC_LEVEL = 10;
    private static final int DEFENSE_LEVEL = 15;
    private static final int ARCHERY_LEVEL = 10;

    private static volatile PoweredState uniqueInstance;

    // prevent instantiating through constructor
    private PoweredState() {}

    /**
     * Creates a Singleton instance to prevent multiple objects
     * flying around the system.
     * @return in Singleton instance of this class
     */
    public static PoweredState getInstance() {
        if (PoweredState.uniqueInstance == null) {
            synchronized (PoweredState.class) {
                if (PoweredState.uniqueInstance == null) {
                    PoweredState.uniqueInstance = new PoweredState();
                }
            }
        }
        return PoweredState.uniqueInstance;
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
