package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.io.Serializable;
import java.util.List;

/**
 * Represents an action which can be performed by an Opponent.
 */
@FunctionalInterface
public interface ActionBehavior extends Serializable
{
    /**
     * Lets the attacker perform an action on the defender
     * @param attacker an Opponent with the role of attacker
     * @param defender an Opponent with the role of defender
     */
    List<String> action(Opponent attacker, Opponent defender);
}
