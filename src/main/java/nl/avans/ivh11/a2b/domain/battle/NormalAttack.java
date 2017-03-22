package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        List<String> battleMessages = new ArrayList<>();
        if (attacker.isAlive() && defender.isAlive()) {
            // get the attack style stats of the attacker
            Map<String, Integer> attackStyleStats = attacker.getAttackStyleStats();
            int attackStyleLevel = attackStyleStats.get("AttackStyleLevel");
            int attackStyleAccuracy = attackStyleStats.get("AttackStyleAccuracy");

            // calculate how much damage is done
            int damage = CustomRandom.getInstance().randomOpponentDamage(
                    attackStyleLevel,
                    attackStyleAccuracy,
                    defender.getStats().getDefense(),
                    defender.getStats().getDefenseAccuracy()
            );

            // prevent hitting above the defender's hitpoints
            if (damage > defender.getCurrentHitpoints()) {
                damage = defender.getCurrentHitpoints();
            }

            // add messages to the list to report the player about the actions taking place
            battleMessages.add(attacker.getName() + " attacked " + defender.getName() + " with "  + damage + " damage");
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
