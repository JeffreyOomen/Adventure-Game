package nl.avans.ivh11.a2b.domain.enemy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enemy {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    private int level;
    private Stats stats;
    private ActionBehavior actionBehavior;
    private List<Usable> loot; // TODO: use usable as List<Type>

    public boolean performAction(Opponent opponent) {
        // TODO: toevoegen
        return true;
    }

    /**
     * isAlive
     * Determines enemy is alive.
     * @return boolean
     */
    public boolean isAlive() {
        return this.stats.getHitpoints() > 0? true : false;
    }

    /**
     * takeDamage
     * takes damage done by enemy.
     * Collect a hit.
     * @param hit
     * @return boolean
     */
    public boolean takeDamage (int hit) {
        boolean damageDone = false;
        if(hit > 0) {
            stats.setHitpoints(stats.getHitpoints() - hit);
            damageDone = true;
        }
        return damageDone;
    }

    public List<Object> randomDrop() {
        return  new ArrayList<>(); // TODO: bepalen hoe drops teruggeven.
    }

//    public Exception receiveXp() {
//
//    }

}
