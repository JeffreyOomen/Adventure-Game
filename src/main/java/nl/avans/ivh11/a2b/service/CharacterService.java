package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.usable.Inventory;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.usable.Usable;

public interface CharacterService
{
    Character findById(long id);

    Character save(Character character);

    // Inventory methods
    Inventory getInventory(Character character);

    void dropInventoryItem(Character character, Long key);

    void useInventoryItem(Character character, Long key);

    boolean addInventoryItem(Character character, Usable usable);
}
