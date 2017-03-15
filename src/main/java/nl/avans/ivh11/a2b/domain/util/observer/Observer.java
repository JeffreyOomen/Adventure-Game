package nl.avans.ivh11.a2b.domain.util.observer;

/**
 * Observer interface
 */
public interface Observer
{
    /**
     * Notify observer of subject state change
     */
    public void update(String message);
}
