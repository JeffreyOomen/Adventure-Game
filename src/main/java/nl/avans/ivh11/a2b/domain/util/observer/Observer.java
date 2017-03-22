package nl.avans.ivh11.a2b.domain.util.observer;

import java.util.List;

/**
 * Observer interface
 */
public interface Observer
{
    /**
     * Notify observer of subject state change
     */
    void update(List<String> messages);

    /**
     * Notify observer of subject state change
     */
    void update(String message);
}
