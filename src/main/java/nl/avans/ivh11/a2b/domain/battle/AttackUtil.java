package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.List;
import java.util.Map;

/**
 * This class provides common functionality for attacking other Opponents.
 * Created by jeffreyoomen on 22/03/2017.
 */
public class AttackUtil
{
    /**
     * Calculates the amount of damage which can be done by the attacker
     * on the defender.
     * @param attacker an Object of Opponent, which has the role of attacker
     * @param defender an Object of Opponent, which has the role of defender
     * @return the damage which can be done on the defender based on some variables.
     */
    protected int calculateDamage(final Opponent attacker, final Opponent defender, final double multiplier) {
        // get the attack style stats of the attacker
        Map<String, Integer> attackStyleStats = attacker.getAttackStyleStats();
        int attackStyleLevel = attackStyleStats.get("AttackStyleLevel");
        int attackStyleAccuracy = attackStyleStats.get("AttackStyleAccuracy");

        // calculate how much damage is done
        int damage = (int) (CustomRandom.getInstance().randomOpponentDamage(
                attackStyleLevel,
                attackStyleAccuracy,
                defender.getStats().getDefense(),
                defender.getStats().getDefenseAccuracy()
        ) * multiplier);

        // prevent hitting above the defender's hitpoints
        if (damage > defender.getCurrentHitpoints()) {
            damage = defender.getCurrentHitpoints();
        }

        return damage;
    }

    /**
     * Notify if the defender has been killed by adding a message
     * to the attack messages list
     * @param defender an Object of Opponent, which has the role of defender
     */
    protected List<String> addKillMessage(List<String> attackMessages, Opponent attacker, Opponent defender) {
        if (!defender.isAlive()) {
            attackMessages.add("<span class=\"message-danger\">" + defender.getName() + " has been killed</span>");
            attackMessages.add("<span class=\"message-info\">" + attacker.getName() + " has won the battle</span>");
        }

        return attackMessages;
    }
}
