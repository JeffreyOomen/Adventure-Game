package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<String> action(Opponent attacker, Opponent defender) {
        List<String> battleMessages = new ArrayList<>();
        Character c = (Character) attacker;

        if (c.isAlive() && c.isAlive()) {

            Inventory inventory = c.getInventory();

            int healed = 0;

            battleMessages.add("No heal potion available...");
            if(inventory.getUsables().size() > 0) {
                int oldHp = c.getCurrentHitpoints();

                List<Usable> usables =  c.getInventory().getUsables();

                Usable usableToRemove = null;
                for(Usable u : usables) {
                    if(u.getType() == UsableType.POTION_HEAL) {
                        // heal potion found in inventory - use usable on character
                        u.use(c);
                        battleMessages.add(c.getName() + " healed!");

                        // Determine heal amount
                        healed = c.getCurrentHitpoints() - oldHp;
                        usableToRemove = u;
                    }
                }
                // Validate usableToRemove is found (placed out of the loop to avoid ConcurrentModificationException)
                if(usableToRemove != null) {
                    // Remove item from inventory
                    inventory.dropUsable(usableToRemove);
                    battleMessages.add(c.getName() + " healed with " + healed + " hp. </br>Removed used potion from inventory.");
                }
            }
            return battleMessages;
        }

        return null;
    }

}
