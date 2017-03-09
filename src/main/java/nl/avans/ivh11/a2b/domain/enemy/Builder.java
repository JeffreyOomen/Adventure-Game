package nl.avans.ivh11.a2b.domain.enemy;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;

/**
 * EnemyBuilder 'contract'.
 */
public interface Builder {
    void setName(String name);
    void setDescription(String description);
    void setActionBehaviour(ActionBehavior actionBehaviour);
    void setLoot(List<Usable> loot);
    void setStats(Stats stats);
    Enemy buildEnemy();
}
