package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class Heal implements ActionBehavior
{
    /**
     * Heal Character
     * @param character the current Character
     * @param enemy the Character's enemy
     */
    public void action(Character character, Enemy enemy) {
        character.heal(10); //TODO get from potion
    }
}
