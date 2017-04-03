package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
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
     */
    @Override
    public Builder newEnemy() {
        this.enemy = new Enemy();
        return this;
    }

    @Override
    public Builder setName(String name) {
        this.enemy.setName(name);
        return this;
    }

    @Override
    public Builder setMedia(Media media) {
        this.enemy.setMedia(media);
        return this;
    }

    @Override
    public Builder setDescription(String description) {
        this.enemy.setDescription(description);
        return this;
    }

    @Override
    public Builder setActionBehaviour(ActionBehavior actionBehaviour) {
        this.enemy.setActionBehavior(actionBehaviour);
        return this;
    }

    @Override
    public Enemy buildEnemy() {
        return this.enemy;
    }
}
