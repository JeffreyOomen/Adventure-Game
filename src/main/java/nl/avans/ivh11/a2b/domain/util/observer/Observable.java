package nl.avans.ivh11.a2b.domain.util.observer;

/**
 * Created by matthijs on 5-3-17.
 */
public interface Observable {
    public void attach(Observer observer);

    public void detach(Observer observer);

    public void notifyObservers();
}
