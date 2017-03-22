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
     * Take damage as result van an enemy attack
     * @param hit int damage to take
     */
    public void takeDamage(int hit) {
        if (this.isAlive()) {
            if (hit >= this.stats.getCurrentHitpoints()) {
                this.stats.setCurrentHitpoints(0);
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
     * Regenerate the Opponent, this means that the hitpoints will be reset
     * to the maximum.
     */
    public void regenerate() {
        this.stats.setCurrentHitpoints(this.stats.getHitpoints());
    }

    /**
     * Adds the given hitpoints to the current Hitpoints
     * @param hitPoints int
     */
    public void heal(int hitPoints) {
        int newHitpoints = this.stats.getCurrentHitpoints() + hitPoints;
        if(newHitpoints <= this.stats.getHitpoints()) {
            this.stats.setCurrentHitpoints(newHitpoints);
        } else {
            this.stats.setCurrentHitpoints(this.stats.getHitpoints());
        }
    }

    /**
     * Gets the maximum hitpoints of the Opponent
     * @return
     */
    public int getHitpoints() {
        return this.stats.getHitpoints();
    }

    /*
     * ################ Observer Pattern ################
     */

    /**
     * Attach an Observer
     * @param observer an object of Observer which listens to any Observables
     */
    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Detach an Observer
     * @param observer an object of Observer which listens to any Observables
     */
    @Override
    public void detach(Observer observer) {
        if(this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    /**
     * Notify all attached Observers and push messages
     * @param messages messages which need to be logged to the user
     */
    @Override
    public void notifyObservers(List<String> messages) {
        for (Observer observer : this.observers) {
            observer.update(messages);
        }
    }
}
