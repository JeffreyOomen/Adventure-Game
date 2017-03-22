package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Attack the Opponent with a normal attack
 */
public class NormalAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a normal attack
     * @param attacker the Opponent which attacks the other Opponent
     * @param defender the Opponent which is being attacked by the other Opponent
     */
    @Override
    public String action(Opponent attacker, Opponent defender) {
        if (attacker.isAlive() && defender.isAlive()) {
            int damage = CustomRandom.getInstance().randomDamage(
                    // Attacker determine strength
                    attacker.getStats().getStrength(),
                    attacker.getStats().getStrengthAccuracy(),
                    // Defender determine defense
                    defender.getStats().getDefense(),
                    defender.getStats().getDefenseAccuracy()
            );
            defender.takeDamage(damage);

            return attacker.getName() + " attacked " + defender.getName() + " with " + damage + " damage!";
        }

        if (!defender.isAlive()) {
            return attacker.getName() + " has won the battle";
        } else {
            return attacker.getName() + " has lost the battle";
        }
    }
}
