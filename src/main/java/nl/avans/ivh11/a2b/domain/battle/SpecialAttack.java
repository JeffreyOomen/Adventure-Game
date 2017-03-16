package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;

/**
 * Attack the Opponent with a special attack
 */
public class SpecialAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a special attack
     *
     * @param attacker
     * @param defender
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

            if (!defender.isAlive()) {
                return "You have WON, " + attacker.getName() + " !!!";
            } else {
                return attacker.getName() + " attacked " + defender.getName() + " with " + damage + " damage!";
            }
        }

        return "Your opponent " + defender.getName() + " already died...";
    }
}
