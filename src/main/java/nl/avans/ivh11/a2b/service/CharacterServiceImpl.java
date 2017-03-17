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

        // Setup non-decorated Character
        Character c = new Troll("Bramboo", new Stats());
        c.getStats().setStrength(40);
        c.getStats().setStrengthAccuracy(100);
        c.setActionBehavior(new NormalAttack());
        characterRepository.save(c);


        // Setup Equipment
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        PotionFactory potionFactory = new PotionFactory();
        // Necessary to save equipment item for id
        Usable usable1 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10));
        Usable usable2 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10));
        Usable usable3 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));
        Usable usable4 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));
        Usable usable5 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));
        Usable usable6 =  equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));

        this.addInventoryItem(c, usable1);
        this.addInventoryItem(c, usable2);
        this.addInventoryItem(c, usable3);
        this.addInventoryItem(c, usable4);
        this.addInventoryItem(c, usable5);
        this.addInventoryItem(c, usable6);

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
    public boolean dropInventoryItem(Character character, int index) {
        Inventory inventory = character.getInventory();
        Usable usable = inventory.getUsable(index);
        return inventory.dropUsable(usable);
    }

    @Override
    public void useInventoryItem(Character character, int index) {
        Inventory inventory = character.getInventory();
        Usable usable = inventory.getUsable(index);
        usable.use(character);
    }

    @Override
    public boolean addInventoryItem(Character character, Usable usable) {
        return character.getInventory().addUsable(usable);
    }

}