package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.util.observer.Observable;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
public abstract class Opponent implements Observable
{
    protected String name;

    protected String description;

    @ManyToOne(cascade = CascadeType.MERGE)
    protected Media media;

    @Lob
    protected ActionBehavior actionBehavior;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STATS_ID")
    protected Stats stats;

    @Transient
    private List<Observer> observers = new ArrayList<>();

    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    public abstract void performAction(Opponent opponent);

    /**
     * Adds the given hitpoints to the currentHitpoints
     * @param hitPoints int
     */
    public abstract void heal(int hitPoints);

    /**
     * Receive an incoming XP bounty
     */
    public abstract void receiveXp(int xp);

    /**
     * Take damage as result van an enemy attack
     * @param hit int damage to take
     */
    public void takeDamage(int hit) {
        if (this.isAlive()) {
            if (hit >= this.stats.getCurrentHitpoints()) {
                this.stats.setCurrentHitpoints(0);
                notifyObservers(this.getName() + " has been killed!");
            } else {
                this.stats.setCurrentHitpoints(this.stats.getCurrentHitpoints() - hit);
            }
        }
    }

    /**
     * Determines if the Opponent is still alive
     * @return true if the Opponent is alive, false otherwise
     */
    public boolean isAlive() {
        return this.stats.getCurrentHitpoints() > 0;
    }

    /**
     * Attach an Observer
     * @param observer
     */
    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Detach an Observer
     * @param observer
     */
    @Override
    public void detach(Observer observer) {
        if(this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    /**
     * Notify all attached Observers and
     * push message
     * @param message
     */
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : this.observers) {
            observer.update(message);
        }
    }
}
