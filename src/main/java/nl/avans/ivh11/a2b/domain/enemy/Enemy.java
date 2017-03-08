package nl.avans.ivh11.a2b.domain.enemy;

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
    @OneToOne(cascade = CascadeType.ALL)
    private Stats stats;
    private ActionBehavior actionBehavior;
    @OneToMany()
    private List<Usable> loot;

    public boolean performAction(Opponent opponent) {
        this.actionBehavior.action(opponent);
        return true; // TODO: currently action returns void so this is a temporary fix.
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

    /**
     * receiveXp
     * currently based on enemy hitpoints
     * @return int
     */
    public int receiveXp() {
        return this.getStats().getHitpoints();
    }

}
