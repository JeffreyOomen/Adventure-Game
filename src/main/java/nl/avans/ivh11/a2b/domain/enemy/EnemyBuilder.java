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

    public EnemyBuilder() {
        this.enemy = new Enemy();
    }

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
