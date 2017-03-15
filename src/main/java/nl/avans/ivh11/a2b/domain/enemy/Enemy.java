package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents an Enemy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enemy implements Opponent {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    private Stats stats;
    private ActionBehavior actionBehavior;
    @OneToMany()
    private List<Usable> loot;

    @Transient
    private List<Observer> observers;

    public Enemy(Stats stats) {
        this.stats = stats;
        this.observers = new ArrayList<>();
    }

    /**
     * Get the Observable's state
     * @return String
     */
    public String getState() {
        return Integer.toString(stats.getCurrentHitpoints());
    }

    public void performAction(Opponent opponent) {
        this.actionBehavior.action(opponent, this);
    }

    /**
     * isAlive
     * Determines enemy is alive.
     * @return boolean
     */
    public boolean isAlive() {
        return this.stats.getCurrentHitpoints() > 0 ;
    }

    /**
     * takeDamage
     * takes damage done by enemy.
     * Collect a hit.
     * @param hit
     * @return int damage done
     */
    public int takeDamage (int hit) {
        int damage = hit;

        // Hit greater then 0 and defender is alive
        if(hit > 0 && isAlive()) {
            if(hit > stats.getCurrentHitpoints()) {
                damage = stats.getCurrentHitpoints();
                // Enemy is dead - set current hp 0
                stats.setCurrentHitpoints(0);
            } else {
                stats.setCurrentHitpoints(stats.getCurrentHitpoints() - hit);
            }
        }
        return damage;
    }

    public List<Object> randomDrop() {
        return  new ArrayList<>(); // TODO: bepalen hoe drops teruggeven.
    }

    /**
     * receiveXp
     * currently based on enemy hitpoints
     * @return int
     */
    public void receiveXp(int xp) {
        //TODO xp verhogen
//        this.getStats(). this.getStats().getHitpoints();
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
            observer.update();
        }
    }


    /**
     * Adds the given hitpoints to the currentHitpoints
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
}