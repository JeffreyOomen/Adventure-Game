package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.io.Serializable;

public interface ActionBehavior extends Serializable
{
    /**
     * Perform an action on the given Opponent
     * @param character the current Character
     * @param enemy the Character's enemy
     */
    public void action(Character character, Enemy enemy);
}
