package nl.avans.ivh11.a2b.service;


import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.datastorage.usable.UsableRepository;
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
    private UsableRepository usableRepository;
    private MediaRepository mediaRepository;

    private MediaService mediaService;

    /**
     * CharacterServiceImpl Constructor
     * @param characterRepo
     */
    @Autowired
    public CharacterServiceImpl(
        CharacterRepository characterRepo,
        EquipmentRepository equipmentRepo,
        MediaRepository mediaRepository,
        MediaService mediaService,
        UsableRepository usableRepository
    )
    {
        this.characterRepository = characterRepo;
        this.mediaRepository = mediaRepository;
        this.mediaService = mediaService;
        this.usableRepository = usableRepository;

        // Start demo
        demoCharacter();
    }

    /**
     * Setup Demo, Character and Enemy for the demo
     */
    @Transactional
    private void demoCharacter() {

        // TODO: create a startup service for this later
        // Persist all Media items (images)

        Media media = mediaService.findById(1L);

        // Setup non-decorated Character
        Character c = new Troll("Kees Kroket", new Stats(), media);
        c.getStats().setStrength(40);
        c.getStats().setStrengthAccuracy(100);
        c.setActionBehavior(new NormalAttack());
        characterRepository.save(c);


        // Initialize factories
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        PotionFactory potionFactory = new PotionFactory();

        List<Usable> usableList = new ArrayList<>();

        // Necessary to save equipment item for id
        usableList.add(usableRepository.save(equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10)));
        usableList.add(usableRepository.save(equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 20)));
        usableList.add(usableRepository.save(equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 30)));
        usableList.add(usableRepository.save(equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 20)));
        usableList.add(usableRepository.save(equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_STAFF, 10)));

        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_HEAL, 10)));
        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_HEAL, 10)));
        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_HEAL, 10)));
        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_HEAL, 10)));
        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_OVERLOAD, 10)));
        usableList.add(usableRepository.save(potionFactory.createUsable(UsableType.POTION_OVERLOAD, 10)));


        // Assign created usable to inventory character
        for(Usable u : usableList) {
            c.getInventory().addUsable(u);
        }

        Equipment equipmentHelmet = (Equipment) equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10);
        Equipment equipmentBody = (Equipment) equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10);
        Equipment equipmentLegs = (Equipment) equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 10);
        usableRepository.save(equipmentHelmet);
        usableRepository.save(equipmentBody);
        usableRepository.save(equipmentLegs);

        // Test using gear
        c.mountEquipment(UsableType.EQUIPMENT_HELMET, equipmentHelmet);
        c.mountEquipment(UsableType.EQUIPMENT_BODY, equipmentBody);
        c.mountEquipment(UsableType.EQUIPMENT_LEGS, equipmentLegs);

        characterRepository.save(c);

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

        // Update character
        characterRepository.save(character);
    }

    @Override
    public boolean addInventoryItem(Character character, Usable usable) {
        return character.getInventory().addUsable(usable);
    }

}