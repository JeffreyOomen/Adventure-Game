package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class SpecialAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a special attack
     * @param character the current Character
     * @param enemy the Character's enemy
     */
    public void action(Character character, Enemy enemy) {
        enemy.takeDamage(20); //TODO Get from Random
    }
}
