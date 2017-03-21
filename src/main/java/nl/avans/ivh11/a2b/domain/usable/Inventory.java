package nl.avans.ivh11.a2b.domain.usable;

import java.util.ArrayList;
import java.util.List;

import static nl.avans.ivh11.a2b.domain.usable.UsableType.POTION_HEAL;

public class Inventory
{
    public List<HealPotion> getHealPotions() {
        List<HealPotion> healPotions = new ArrayList<>();
        healPotions.add(new HealPotion(POTION_HEAL, 10));
        return healPotions;
    }

    public boolean drop(Usable usable) {
        //TODO implement
        return true;
    }
}
