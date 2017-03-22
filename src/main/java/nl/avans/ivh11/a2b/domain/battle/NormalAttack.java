package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> action(Opponent attacker, Opponent defender) {
        if (attacker.isAlive() && defender.isAlive()) {
            List<String> battleMessages = new ArrayList<>();
            int damage = CustomRandom.getInstance().randomOpponentDamage(
                    // Attacker determine strength
                    attacker.getStats().getStrength(),
                    attacker.getStats().getStrengthAccuracy(),
                    // Defender determine defense
                    defender.getStats().getDefense(),
                    defender.getStats().getDefenseAccuracy()
            );
            battleMessages.add(attacker.getName() + " attacked " + defender.getName() + " with "  + damage + " damage");

            String damageMessage = defender.takeDamage(damage);
            if (damageMessage != null) {
                battleMessages.add(damageMessage);
            }

            return battleMessages;
        }

        return null;
    }
}
