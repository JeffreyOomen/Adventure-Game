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
    public Builder setName(String name) {
        enemy.setName(name);
        return this;
    }

    @Override
    public Builder setDescription(String description) {
        enemy.setDescription(description);
        return this;
    }

    @Override
    public Builder setActionBehaviour(ActionBehavior actionBehaviour) {
        enemy.setActionBehavior(actionBehaviour);
        return this;
    }

    /**
     * setLoot
     * set an enemy drop. When loot is null a random drop is created
     * @param loot
     * @return
     */
    @Override
    public Builder setLoot(Usable loot) {
      if(loot != null) {
          enemy.setLoot(loot);
      } else {
//          enemy.setLoot(enemy.randomDrop());
      }
        return this;
    }

    @Override
    public Builder setStats(Stats stats) {
        enemy.setStats(stats);
        return this;
    }

    @Override
    public Enemy buildEnemy() {
       return enemy;
    }
}
