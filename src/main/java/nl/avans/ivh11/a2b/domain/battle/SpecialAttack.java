package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Attack the Opponent with a special attack
 */
public class SpecialAttack extends AttackUtil implements ActionBehavior {
    /**
     * Attack the enemy with a special attack
     *
     * @param attacker the Opponent which attacks the other Opponent
     * @param defender the Opponent which is being attacked by the other Opponent
     */
    @Override
    public List<String> action(Opponent attacker, Opponent defender) {
        List<String> attackMessages = new ArrayList<>();

        // only perform action when both attacker and defender are alive
        if (attacker.isAlive() && defender.isAlive()) {

            if (attacker instanceof Character) {
                Character c = (Character) attacker;
                Inventory inventory = attacker.getInventory();

                attackMessages.add("No overload potions available...");
                if (inventory.getUsables().size() > 0) {

                    Map<Long, Usable> usables = c.getInventory().getUsables();

                    Usable usableToRemove = null;
                    for (Usable u : usables.values()) {
                        if (u.getType() == UsableType.POTION_OVERLOAD) {

                            // overload potion found in inventory - use usable on character
                            u.use(c);

                            attackMessages.add(c.getName() + " used a overload potion!");

                            usableToRemove = u;
                        }
                    }
                    // Validate usableToRemove is found (placed out of the loop to avoid ConcurrentModificationException)
                    if (usableToRemove != null) {
                        // Remove item from inventory
                        inventory.dropUsable(usableToRemove);
                        attackMessages.add("Removed overload potion from inventory.");
                    }
                }
            }

            int damage = this.calculateDamage(attacker, defender, 1.2);

            // add messages to the list to report the player about the actions taking place
            attackMessages.add(attacker.getName() + " did a special attack on " + defender.getName() + " with " + damage + " damage");
            defender.takeDamage(damage);

            // add an optional kill message
            attackMessages = this.addKillMessage(attackMessages, attacker, defender);
        }
        
        return attackMessages;
    }
}
