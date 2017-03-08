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

    public Enemy createEnemy(String name, String description, ActionBehavior actionBehavior, int level, Stats stats, List<Usable> loot) {
        this.builder.setName(name);
        this.builder.setDescription(description);
        this.builder.setActionBehaviour(actionBehavior);
        this.builder.setLevel(level);
        this.builder.setLevel(level);
        this.builder.setLoot(loot);
        return this.builder.buildEnemy();
    }

}
