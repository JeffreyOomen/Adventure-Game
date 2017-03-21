package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
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

    public Enemy createEnemy(String name, Media media, String description, ActionBehavior actionBehavior, Stats stats, List<Usable> loot) {
        this.builder.setEnemy(new Enemy()); // in this way we can create more Enemy objects with only one builder
        this.builder.setName(name);
        this.builder.setMedia(media);
        this.builder.setDescription(description);
        this.builder.setActionBehaviour(actionBehavior);
        this.builder.setStats(stats);
        this.builder.setLoot(loot);
        return this.builder.buildEnemy();
    }

}
