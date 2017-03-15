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
     * @param character the current Character
     * @param enemy the Character's enemy
     * @return The action result
     */
    public String action(Opponent character, Opponent enemy) {
        // Cast
        Character c = ((Character) character);

        String message = "No Heal potions";
        if(c.getInventory().getHealPotions().size() > 0) {
            int hitPoints = 10;
            character.heal(hitPoints);
            Usable potion = c.getInventory().getHealPotions().get(0);
            c.getInventory().drop(potion);
            message = c.getName() + " healed with " + hitPoints + " hp";
        }
        return message;
    }

}
