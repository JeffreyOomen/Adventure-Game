package nl.avans.ivh11.a2b.domain.util.observer;

/**
 * Created by matthijs on 5-3-17.
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
    public void notifyObservers();

    /**
     * Get the Observable's state
     * @return String
     */
    public String getState();
}
