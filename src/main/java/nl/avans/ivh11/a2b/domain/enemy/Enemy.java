package nl.avans.ivh11.a2b.domain.enemy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.*;
import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.*;
import java.util.*;

/**
 * Represents an Enemy
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Enemy extends Opponent
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @OneToMany
    private List<Usable> loot;

    public Enemy(Stats stats) {
        this.stats = stats;
    }

    /**
     * Performs an action against the Opponent
     * @param opponent the Enemy's Opponent
     */
    @Override
    public void performAction(Opponent opponent) {
        notifyObservers(this.actionBehavior.action(this, opponent));
    }

    @Override
    public UsableType getAttackStyle() {
        throw new NotImplementedException();
    }

    @Override
    public void receiveXp(int earnedXp) {
        throw new NotImplementedException();
    }

    @Override
    public Inventory getInventory() {
        throw new NotImplementedException();
    }

    /**
     * Gets the level and accuracy in a Map based on the current
     * Attack Style of the Character
     * @return a Map which contains the level and accuracy of the skill
     * which belongs to the current Attack Style
     */
    @Override
    public Map<String, Integer> getAttackStyleStats() {
        Map<String, Integer> map = new HashMap<>();
        // since attack styles for enemies are not supported, always give strength
        map.put("AttackStyleLevel", this.getStats().getStrength());
        map.put("AttackStyleAccuracy", this.getStats().getStrengthAccuracy());
        return map;
    }

    /**
     * Receive a random drop from a chosen factory
     * @return usable
     */
    public Usable randomDrop() {
        UsableFactory usableFactory;
        Usable usable;
        CustomRandom cr = CustomRandom.getInstance();

        // Based on random change give a potion or equipment as drop
        // 70% change of a potion drop 30% change of equipment drop.
        int determineDropChange = cr.randomBetweenZeroAnd(100);

        if (50 < 70) {
            usableFactory = new PotionFactory();
            UsableType usableType = getRandomDropUsableType("potion");
            usable = usableFactory.createUsable(UsableType.POTION_OVERLOAD);
        } else {
            // 30% drop change - create equipment
            usableFactory = new EquipmentFactory();
            UsableType usableType = getRandomDropUsableType("equipment");
            usable = usableFactory.createUsable(usableType, this.stats.getCombatLevel());
        }
        return usable;
    }

    /**
     * Used to randomly get a UsableType based on given subtype (equipment or potion). This method is used in @randomDrop to generate a usable.
     * @param type potion or equipment : String
     * @return an Usable
     */
    private UsableType getRandomDropUsableType(String type) {
        UsableType chosenDrop = null;
        List<UsableType> possibleDrops = Arrays.asList(UsableType.values());
        int size = UsableType.values().length;
        CustomRandom customRandom = CustomRandom.getInstance();

        while (chosenDrop == null) {
            UsableType randomType = possibleDrops.get(customRandom.randomBetweenZeroAnd(size));
            if (randomType.toString().toLowerCase().contains(type) && randomType != UsableType.EQUIPMENT_WEAPON) {
                chosenDrop = randomType;
            }
        }
        return chosenDrop;
    }

    /**

     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    public int getCurrentHitpoints() {
        return this.stats.getCurrentHitpoints();
    }
}
