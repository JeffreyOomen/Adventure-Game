package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.List;

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

        if (character.isAlive() && enemy.isAlive()) {
            Character c = ((Character) character);

            Inventory inventory = c.getInventory();

            String message = "No Heal potions";
            if(inventory.getInventory().size() > 0) {

                List<Usable> usables =  c.getInventory().getInventory();

                Usable usableToRemove = null;
                for(Usable u : usables) {
                    if(u.getType() == UsableType.POTION_HEAL) {
                        // heal potion found in inventory - use usable on character
                        u.use((Character) character);
                        message = c.getName() + " healed!";
                        usableToRemove = u;
                    }
                }
                // Validate usableToRemove is found (placed out of the loop to avoid ConcurrentModificationException)
                if(usableToRemove != null) {
                    // Remove item from inventory
                    inventory.dropUsable(usableToRemove);
                    message = c.getName() + " has been healed! Removed potion from inventory.";
                }
            }
            return message;
        }
        return "Your opponent " + enemy.getName() + " already died...";
    }

}
