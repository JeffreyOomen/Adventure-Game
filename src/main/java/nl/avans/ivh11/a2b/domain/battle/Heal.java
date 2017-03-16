package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Heal the current Character
 */
public class Heal implements ActionBehavior
{
    /**
     * Heal Character
     * @param attacker the current Character
     * @param defender the Character's enemy
     * @return The action result
     */
    public String action(Opponent attacker, Opponent defender) {

        if (attacker.isAlive() && defender.isAlive()) {
            Character c = ((Character) attacker);

            String message = "No Heal potions";
            if(c.getInventory().getHealPotions().size() > 0) {
                int hitPoints = 10;
                attacker.heal(hitPoints);
                Usable potion = c.getInventory().getHealPotions().get(0);
                c.getInventory().drop(potion);
                message = c.getName() + " healed with " + hitPoints + " hp";
            }
            return message;
        }

        return "Your opponent " + defender.getName() + " already died...";
    }

}
