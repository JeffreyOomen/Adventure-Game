package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<String> action(Opponent attacker, Opponent defender) {
        List<String> battleMessages = new ArrayList<>();
        if (attacker.isAlive() && defender.isAlive()) {
            // calculate how much damage is done
            int damage = (int) (CustomRandom.getInstance().randomOpponentDamage(
                    // Attacker determine strength
                    attacker.getStats().getStrength(),
                    attacker.getStats().getStrengthAccuracy(),
                    // Defender determine defense
                    defender.getStats().getDefense(),
                    defender.getStats().getDefenseAccuracy()
            ) * 1.20);

            // prevent hitting above the defender's hitpoints
            if (damage > defender.getHitpoints()) {
                damage = defender.getHitpoints();
            }

            // add messages to the list to report the player about the actions taking place
            battleMessages.add(attacker.getName() + " did a special attack on " + defender.getName() + " with "  + damage + " damage");
            defender.takeDamage(damage);

            // notify that the defender has been killed
            if (!defender.isAlive()) {
                battleMessages.add("<span class=\"message-danger\">" + defender.getName() + " has been killed!</span>");
            }

            return battleMessages;
        }

        return battleMessages;
    }
}
