package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Attack the Opponent with a normal attack
 */
public class NormalAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a normal attack
     * @param character the current Character
     * @param enemy the Character's enemy
     * @return The action result
     */
    public String action(Character character, Opponent enemy) {
        int damage = CustomRandom.getInstance().randomDamage(
                character.getStrength(),
                character.getStrengthAccuracy(),
                character.getDefense(),
                character.getDefenseAccuracy()
        );
        enemy.takeDamage(damage);
        return character.getName() + " attacked enemy with " + damage + " damage";
    }
}
