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
    public String action(Character character, Opponent enemy) {
        String message = "No Heal potions";
        if(character.getInventory().getHealPotions().size() > 0) {
            int hitPoints = 10;
            character.heal(hitPoints);
            Usable potion = character.getInventory().getHealPotions().get(0);
            character.getInventory().drop(potion);
            message = character.getName() + " healed with " + hitPoints + " hp";
        }
        return message;
    }
}
