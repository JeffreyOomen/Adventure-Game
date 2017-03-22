package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilder 'contract'.
 */
public interface Builder {
    Builder newEnemy();
    Builder setName(String name);
    Builder setDescription(String description);
    Builder setActionBehaviour(ActionBehavior actionBehaviour);
    Builder setLoot(List<Usable> loot);
    Builder setStats(Stats stats);
    Enemy buildEnemy();
}
