package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilderDirector class
 * creates a Enemy using the builder.
 */
public class EnemyBuilderDirector {
    private Builder builder;

    public EnemyBuilderDirector(final Builder builder) {
        this.builder = builder;
    }

    /**
     * createEnemy
     * build a new enemy where loot need to be set manually
     * @param name
     * @param description
     * @param actionBehavior
     * @param stats
     * @param loot
     * @return Enemy object
     */
    public Enemy createEnemy(String name, String description, ActionBehavior actionBehavior, Stats stats, Usable loot) {
        return builder.setName(name).setDescription(description).setActionBehaviour(actionBehavior).setStats(stats).setLoot(loot).buildEnemy();
    }

    /**
     * createEnemy
     * build a new enemy with a random drop
     * @param name
     * @param description
     * @param actionBehavior
     * @param stats
     * @return Enemy object
     */
    public Enemy createEnemy(String name, String description, ActionBehavior actionBehavior, Stats stats) {
        return builder.setName(name).setDescription(description).setActionBehaviour(actionBehavior).setStats(stats).setLoot(null).buildEnemy();
    }

}
