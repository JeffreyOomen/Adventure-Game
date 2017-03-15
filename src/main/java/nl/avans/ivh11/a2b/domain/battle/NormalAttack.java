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
     * @param attacker
     * @param defender
     */
    public void action(Opponent attacker, Opponent defender) {
        int damage = Random.getInstance().randomDamage(
                // Attacker determine strength
                attacker.getStats().getStrength(),
                attacker.getStats().getStrengthAccuracy(),
                // Defender determine defense
                defender.getStats().getDefense(),
                defender.getStats().getDefenseAccuracy()
        );
        System.out.println("\nDamage normall attack: " + damage);
        defender.takeDamage(damage);
    }
}
