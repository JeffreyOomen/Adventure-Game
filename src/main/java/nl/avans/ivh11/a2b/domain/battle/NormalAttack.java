package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Random;

/**
 * Attack the Opponent with a normal attack
 */
public class NormalAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a normal attack
     * @param character the current Character
     * @param enemy the Character's enemy
     */
    public void action(Character character, Opponent enemy) {
        int damage = Random.getInstance().randomDamage(
                character.getStrength(),
                character.getStrengthAccuracy(),
                character.getDefense(),
                character.getDefenseAccuracy()
        );
        enemy.takeDamage(damage);
    }
}
