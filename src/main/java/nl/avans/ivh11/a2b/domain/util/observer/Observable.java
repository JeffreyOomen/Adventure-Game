package nl.avans.ivh11.a2b.domain.util.observer;

/**
 * Observable interface
 */
public interface Observable
{
    /**
     * Attach an observer to the subject
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * Detach observer from the subject
     * @param observer
     */
    public void detach(Observer observer);

    /**
     * Notify all observers
     */
    public void notifyObservers(String message);
}
