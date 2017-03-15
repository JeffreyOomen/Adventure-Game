package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.io.Serializable;

/**
 * Represents an action which can be performed by an Opponent
 */
public interface ActionBehavior extends Serializable
{
    /**
     * Perform an action on the given Opponent
     * @param character the current Character
     * @param enemy the Character's enemy
     * @return The action result
     */
    public String action(Character character, Opponent enemy);
}
