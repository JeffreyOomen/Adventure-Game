package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Heal the current Character
 */
public class Heal implements ActionBehavior
{
    /**
     * Heal Character
     * @param character the current Character
     * @param enemy the Character's enemy
     */
    public void action(Character character, Opponent enemy) {
        character.heal(10); //TODO get from potion
    }
}
