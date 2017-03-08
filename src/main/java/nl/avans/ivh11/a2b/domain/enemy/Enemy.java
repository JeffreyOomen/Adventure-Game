package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthijs on 8-3-17.
 */
public class Enemy implements Opponent {
    @Transient
    private List<Observer> observers;

    public Enemy() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        if(this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : this.observers) {
            observer.update(this);
        }
    }
}
