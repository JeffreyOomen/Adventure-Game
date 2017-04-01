package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.observer.Observable;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@MappedSuperclass
@Getter
@Setter
public abstract class Opponent implements Observable
{
    protected String name;

    protected String description;

    @Lob
    protected ActionBehavior actionBehavior;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STATS_ID")
    protected Stats stats = new Stats();

    @ManyToOne(cascade = CascadeType.MERGE)
    protected Media media;

    @Transient
    private List<Observer> observers = new ArrayList<>();

    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    public abstract void performAction(Opponent opponent);

    /**
     * Gets the attacking style of the opponent (e.g. melee, magic, archery)
     * @return the attack style in the form of an UsableType
     */
    public abstract UsableType getAttackStyle();

    /**
     * Receive earned XP by killing an Opponent
     * @param earnedXp an Integer representing the XP earned
     */
    public abstract void receiveXp(int earnedXp);

    /**
     * Get the Character's Inventory
     * @return Inventory
     */
    public abstract Inventory getInventory();

    /**
     * Gets the level and accuracy in a Map based on the current
     * Attack Style of the Character
     * @return a Map which contains the level and accuracy of the skill
     * which belongs to the current Attack Style
     */
    public abstract Map<String, Integer> getAttackStyleStats();

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
     * @return the maximum hitpoints of the Opponent
     */
    public int getHitpoints() {
        return this.stats.getHitpoints();
    }

    /**
     * Gets the current hitpoints of the Opponent
     * @return the current hitpoints left of the Opponent
     */
    public int getCurrentHitpoints() {
        return this.stats.getCurrentHitpoints();
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
