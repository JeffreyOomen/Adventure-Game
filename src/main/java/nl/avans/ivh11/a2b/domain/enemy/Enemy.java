package nl.avans.ivh11.a2b.domain.enemy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 */
//@Entity // TODO: currently not persistent
@Getter
@Setter
@NoArgsConstructor
public class Enemy {

    @Id
    @GeneratedValue
    private long id;

    private String name;

    private String description;

    //    @OneToOne(cascade = CascadeType.ALL)
    @Transient
    private Stats stats;

    private ActionBehavior actionBehavior;

    //    @OneToMany(cascade = CascadeType.ALL)
    @Transient
    private Usable loot;

    public boolean performAction(Opponent opponent) {
        this.actionBehavior.action(opponent);
        return true; // TODO: currently action returns void so this is a temporary fix.
    }

    /**
     * isAlive
     * Determines enemy is alive.
     *
     * @return boolean
     */
    public boolean isAlive() {
        return this.stats.getHitpoints() > 0 ? true : false;
    }

    /**
     * takeDamage
     * takes damage done by enemy.
     * Collect a hit.
     *
     * @param hit
     * @return boolean
     */
    public boolean takeDamage(int hit) {
        boolean damageDone = false;
        if (hit > 0) {
            stats.setHitpoints(stats.getHitpoints() - hit);
            damageDone = true;
        }
        return damageDone;
    }

    /**
     * @return
     */
    public Usable randomDrop() {
        UsableFactory usableFactory = null;
        Usable usable = null;
        String type = null;
        String name = null;
        String description = null;
        Stats stats = new Stats();

        // Based on random change give a potion or equipment as drop
        // 70% change of a potion drop 30% change of equipment drop.
        int determineDropChange = new Random().nextInt(100);

        if (determineDropChange > 30) {
            // 70% drop change - create potion
//            usableFactory = new PotionFactory();
            UsableType usableType = pickRandomUsableType("potion");
//            usable = usableFactory.createUsable(usableType, "potion name", "potion desc");
        } else {
            // 30% drop change - create equipment
            usableFactory = new EquipmentFactory();
            UsableType usableType = pickRandomUsableType("equipment");
            usable = usableFactory.createUsable(usableType, stats.getLevel());
        }
        return usable;
    }

    /**
     * receiveXp
     * currently based on enemy hitpoints
     *
     * @return int
     */
    public int receiveXp() {
        return this.getStats().getHitpoints();
    }


    /**
     * pickRandomUsableType
     * used to randomly get a UsableType based on given subtype (equipment or potion). This method is used in @randomDrop to generate a usable.
     *
     * @param type potion or equipment : String
     * @return
     */
    private UsableType pickRandomUsableType(String type) {
        UsableType chosenDrop = null;
        List<UsableType> possibleDrops = Arrays.asList(UsableType.values());
        int size = UsableType.values().length;
        Random random = new Random();

        while (chosenDrop == null) {
            UsableType randomType = possibleDrops.get(random.nextInt(size));
            if (randomType.toString().toLowerCase().contains(type)) {
                chosenDrop = randomType;
            }
        }
        return chosenDrop;
    }

}
