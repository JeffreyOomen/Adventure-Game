package nl.avans.ivh11.a2b.domain.character.state;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Represents the Normal State
 */
@Entity
@NoArgsConstructor
public class NormalState extends CharacterState
{
    private final static int STRENGTH_LEVEL = 0;
    private final static int MAGIC_LEVEL = 0;
    private final static int DEFENSE_LEVEL = 0;
    private final static int ARCHERY_LEVEL = 0;

    private volatile static NormalState uniqueInstance;

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
