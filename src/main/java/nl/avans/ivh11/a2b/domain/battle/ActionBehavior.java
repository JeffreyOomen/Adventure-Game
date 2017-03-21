package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.io.Serializable;

/**
 * Represents an action which can be performed by an Opponent
 */
@FunctionalInterface
public interface ActionBehavior extends Serializable
{
    /**
     * Perform an action on the given Opponent
     * @param attacker
     * @param defender
     */
    public String action(Opponent attacker, Opponent defender);
}
