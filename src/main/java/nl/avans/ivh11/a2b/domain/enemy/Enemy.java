package nl.avans.ivh11.a2b.domain.enemy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.*;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Represents an Enemy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
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
    private List<Observer> observers = new ArrayList<>();

    public Enemy(Stats stats) {
        this.stats = stats;
    }

    /**
     * Get the Observable's state
     *
     * @return String
     */
    public String getState() {
        return Integer.toString(stats.getCurrentHitpoints());
    }

    public void performAction(Opponent opponent) {
        String message = this.actionBehavior.action((nl.avans.ivh11.a2b.domain.character.Character) opponent, this);
        notifyObservers(message);
    }

    /**
     * isAlive
     * Determines enemy is alive.
     *
     * @return boolean
     */
    public boolean isAlive() {
        return this.stats.getCurrentHitpoints() > 0;

    }

    /**
     * takeDamage
     * takes damage done by enemy.
     * Collect a hit.
     *
     * @param hit
     * @return int damage done
     */
    public void takeDamage(int hit) {
        int damage = hit;

        // Hit greater then 0 and defender is alive
        if (hit > 0 && isAlive()) {
            if (hit > stats.getCurrentHitpoints()) {
                damage = stats.getCurrentHitpoints();
                // Enemy is dead - set current hp 0
                stats.setCurrentHitpoints(0);
            } else {
                stats.setCurrentHitpoints(stats.getCurrentHitpoints() - hit);
            }

            // Validate Enemy is alive or not
            if(!isAlive()) {
                notifyObservers(this.getName() + " has been killed!");
            }

        }
    }

    /**
     * randomDrop
     * receiver a random drop from a chosen factory.
     *
     * @return usable
     */
    public Usable randomDrop() {
        UsableFactory usableFactory = null;
        Usable usable = null;

        // Based on random change give a potion or equipment as drop
        // 70% change of a potion drop 30% change of equipment drop.
        int determineDropChange = new CustomRandom().randomBetweenZeroAnd(100);

        if (determineDropChange < 70) {
            usableFactory = new PotionFactory();
            UsableType usableType = getRandomDropUsableType("potion");
            usable = usableFactory.createUsable(usableType, this.stats.getLevel());
        } else {
            // 30% drop change - create equipment
            usableFactory = new EquipmentFactory();
            UsableType usableType = getRandomDropUsableType("equipment");
            usable = usableFactory.createUsable(usableType, this.stats.getLevel());
        }
        return usable;
    }

    /**
     * pickRandomUsableType
     * used to randomly get a UsableType based on given subtype (equipment or potion). This method is used in @randomDrop to generate a usable.
     *
     * @param type potion or equipment : String
     * @return
     */
    private UsableType getRandomDropUsableType(String type) {
        UsableType chosenDrop = null;
        List<UsableType> possibleDrops = Arrays.asList(UsableType.values());
        int size = UsableType.values().length;
        CustomRandom customRandom = new CustomRandom();

        while (chosenDrop == null) {
            UsableType randomType = possibleDrops.get(customRandom.randomBetweenZeroAnd(size));
            if (randomType.toString().toLowerCase().contains(type)) {
                chosenDrop = randomType;
            }
        }
        return chosenDrop;
    }

    /**
     * receiveXp
     * currently based on enemy hitpoints
     *
     * @return int
     */
    public void receiveXp(int xp) {
//        this.getStats().getHitpoints(); // TODO: bepalen hoe we dit doen
    }

    @Override
    public void attach(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        if (this.observers.contains(observer)) {
            this.observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : this.observers) {
            observer.update(message);
        }
    }


    /**
     * Adds the given hitpoints to the currentHitpoints
     *
     * @param hitPoints int
     */
    public void heal(int hitPoints) {
        int newHitpoints = this.stats.getCurrentHitpoints() + hitPoints;
        if (newHitpoints <= this.stats.getHitpoints()) {
            this.stats.setCurrentHitpoints(newHitpoints);
        } else {
            this.stats.setCurrentHitpoints(this.stats.getHitpoints());
        }
    }
}
