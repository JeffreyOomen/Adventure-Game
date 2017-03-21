package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;

/**
 * Attack the Opponent with a special attack
 */
public class SpecialAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a special attack
     * @param attacker the Opponent which attacks the other Opponent
     * @param defender the Opponent which is being attacked by the other Opponent
     */
    public String action(Opponent attacker, Opponent defender) {

        if (attacker.isAlive() && defender.isAlive()) {
            int damage = (int) (CustomRandom.getInstance().randomDamage(
                    // Attacker determine strength
                    attacker.getStats().getStrength(),
                    attacker.getStats().getStrengthAccuracy(),
                    // Defender determine defense
                    defender.getStats().getDefense(),
                    defender.getStats().getDefenseAccuracy()
            ) * 1.20);
            defender.takeDamage(damage);

            return attacker.getName() + " did a special attack on " + defender.getName() + " with " + damage + " damage!";
        }

        return "Nothing happened...";
    }
}
