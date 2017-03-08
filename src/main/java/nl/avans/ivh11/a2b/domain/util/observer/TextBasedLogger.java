package nl.avans.ivh11.a2b.domain.util.observer;

/**
 * Created by matthijs on 5-3-17.
 */
public class TextBasedLogger implements Observer
{
    private Observable observable;

    public TextBasedLogger(Observable observable) {
        this.observable = observable;
        observable.attach(this);
    }

    @Override
    public void update() {
        System.out.println("New state: " + observable.getState());
    }
}
