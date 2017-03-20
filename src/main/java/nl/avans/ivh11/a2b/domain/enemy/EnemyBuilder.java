package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilder class
 * is used to 'build' a Enemy object.
 */
public class EnemyBuilder implements Builder {
    private Enemy enemy;

    /**
     * With this method, more than one Enemy object can be made with
     * the same EnemyBuilder
     * @param enemy an newly created enemy object
     */
    public void setEnemy(Enemy enemy) { this.enemy = enemy; }

    @Override
    public void setName(String name) {
        this.enemy.setName(name);
    }

    @Override
    public void setDescription(String description) {
        this.enemy.setDescription(description);
    }

    @Override
    public void setActionBehaviour(ActionBehavior actionBehaviour) {
        this.enemy.setActionBehavior(actionBehaviour);
    }

    @Override
    public void setLoot(List<Usable> loot) {
        this.enemy.setLoot(loot);
    }

    @Override
    public void setStats(Stats stats) {
        this.enemy.setStats(stats);
    }

    @Override
    public Enemy buildEnemy() {
        return this.enemy;
    }
}
