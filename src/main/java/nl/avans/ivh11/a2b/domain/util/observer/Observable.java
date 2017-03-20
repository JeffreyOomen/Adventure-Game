package nl.avans.ivh11.a2b.domain.util.observer;

import java.util.List;

/**
 * Observable interface
 */
public interface Observable
{
    /**
     * Attach an observer to the subject
     * @param observer
     */
    void attach(Observer observer);

    /**
     * Detach observer from the subject
     * @param observer
     */
    void detach(Observer observer);

    /**
     * Notify all observers
     * @param messages
     */
    void notifyObservers(List<String> messages);

    /**
     * Notify all observers
     * @param message
     */
    void notifyObservers(String message);
}
