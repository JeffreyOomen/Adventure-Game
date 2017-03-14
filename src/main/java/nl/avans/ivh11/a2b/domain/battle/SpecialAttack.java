package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Random;

/**
 * Attack the Opponent with a special attack
 */
public class SpecialAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a special attack
     * @param character the current Character
     * @param enemy the Character's enemy
     * @return The action result
     */
    public String action(Character character, Opponent enemy) {
        Double damage = Random.getInstance().randomDamage(
                character.getStrength(),
                character.getStrengthAccuracy(),
                character.getDefense(),
                character.getDefenseAccuracy()
        ) * 1.20;
        enemy.takeDamage(damage.intValue());
        return "Attacked enemy with " + damage + " damage";
    }
}
