package nl.avans.ivh11.a2b.mock;

import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.battle.AttackUtil;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Attack the Opponent with a normal attack
 */
public class NormalAttackMock extends AttackUtil implements ActionBehavior {
    /**
     * Attack the enemy with a normal attack
     * @param attacker the Opponent which attacks the other Opponent
     * @param defender the Opponent which is being attacked by the other Opponent
     */
    @Override
    public List<String> action(Opponent attacker, Opponent defender) {
        List<String> attackMessages = new ArrayList<>();

        // only perform action when both attacker and defender are alive
        if (attacker.isAlive() && defender.isAlive()) {
            int damage = attacker.getStats().getStrength() - defender.getStats().getDefense();

            if (defender.getCurrentHitpoints() <= damage) {
                damage = defender.getCurrentHitpoints();
            } else if (damage < 0) {
                damage = 0;
            }
            // add messages to the list to report the player about the actions taking place
            attackMessages.add(attacker.getName() + " attacked " + defender.getName() + " with " + damage + " damage");
            defender.takeDamage(damage);

            // add an optional kill message
            attackMessages = this.addKillMessage(attackMessages, attacker, defender);
        }

        return attackMessages;
    }
}
