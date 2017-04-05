package nl.avans.ivh11.a2b.service;


import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("characterService")
@Repository
@Transactional
public class CharacterServiceImpl implements CharacterService
{
    private CharacterRepository characterRepository;

    /**
     * CharacterServiceImpl Constructor
     * @param characterRepo
     */
    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepo) {
        this.characterRepository = characterRepo;
    }

    @Override
    public Character findById(long id) {
        return this.characterRepository.findOne(id);
    }

    @Override
    public Character save(Character character) {
        return this.characterRepository.save(character);
    }

    @Override
    public Inventory getInventory(Character character) {
        return character.getInventory();
    }

    @Override
    public void dropInventoryItem(Character character, Long index) {
        Inventory inventory = character.getInventory();
        Usable usable = inventory.getUsable(index);
        inventory.dropUsable(usable);
    }

    @Override
    public void useInventoryItem(Character character, Long usableId) {
        Inventory inventory = character.getInventory();

        Usable usable = inventory.getUsable(usableId);
        usable.use(character);

        if(usable.getType() == UsableType.POTION_OVERLOAD) {
            inventory.dropUsable(usable);
        }

        // Update character
        characterRepository.save(character);
    }

    @Override
    public boolean addInventoryItem(Character character, Usable usable) {
        return character.getInventory().addUsable(usable);
    }

}