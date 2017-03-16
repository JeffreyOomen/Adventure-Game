package nl.avans.ivh11.a2b.service;


import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.Troll;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("characterService")
@Repository
@Transactional
public class CharacterServiceImpl implements CharacterService
{
    private CharacterRepository characterRepository;
//    private EnemyRepository enemyRepository;
    private EquipmentRepository equipmentRepository;

    private Character character; // current player


    /**
     * CharacterServiceImpl Constructor
     * @param characterRepo
     */
    @Autowired
    public CharacterServiceImpl(CharacterRepository characterRepo, EquipmentRepository equipmentRepo) {
        this.characterRepository = characterRepo;
        this.equipmentRepository = equipmentRepo;

        // Start demo
        demoCharacter();
    }

    /**
     * Setup Demo, Character and Enemy for the demo
     */
    @Transactional
    private void demoCharacter() {
        System.out.println("character service impl aangeroepen");
        // Setup Equipment
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        Usable usable1 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BOOTS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_GLOVES, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));




        // Setup non-decorated Character
        Character c = new Troll("Bramboo", new Stats());
        c.getStats().setStrength(40);
        c.getStats().setStrengthAccuracy(100);
        c.setActionBehavior(new NormalAttack());
        characterRepository.save(c);

        Inventory inventory = this.getInventory(c);

        characterRepository.save(c);

        this.addInventoryItem(c, usable1);

        // Testing inventory
//        addInventoryItem(ch, usable1);



    }

    @Override
    @Transactional
    public Character findById(long id) {
        return this.characterRepository.findOne(id);
    }

    @Override
    @Transactional
    public Character save(Character character) {
        return this.characterRepository.save(character);
    }

    @Override
    public Inventory getInventory(Character character) {
        return character.getInventory();
    }

    @Override
    @Transactional
    public boolean dropInventoryItem(Character character, int index) {
        Inventory inventory = character.getInventory();
        Usable usable = inventory.getUsable(index);
        return inventory.dropUsable(usable);
    }

    @Override
    @Transactional
    public void useInventoryItem(Character character, int index) {
        Inventory inventory = character.getInventory();
        Usable usable = inventory.getUsable(index);
        usable.use(character);
    }

    @Override
    @Transactional
    public boolean addInventoryItem(Character character, Usable usable) {
        boolean isSaved =  character.getInventory().addUsable(usable);
        // TODO: probleem het werkt alleen nu als ik explciet de save aanroep
        this.characterRepository.save(character);
        return isSaved;
    }


}