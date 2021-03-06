package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilder 'contract'.
 */
public interface Builder {
    Builder newEnemy();
    Builder setName(String name);
    Builder setMedia(Media media);
    Builder setDescription(String description);
    Builder setActionBehaviour(ActionBehavior actionBehaviour);
    Enemy buildEnemy();
}
