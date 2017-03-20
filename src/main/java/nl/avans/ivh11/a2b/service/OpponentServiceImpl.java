package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.usable.*;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class OpponentServiceImpl implements OpponentService
{
    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private EquipmentRepository equipmentRepository;
    private MediaRepository mediaRepository;

    /**
     * Constructor
     * @param characterRepository repository for CRUD on Character's
     * @param enemyRepository repository for CRUD on Enemy's
     * @param equipmentRepository repository for CRUD on Equipment
     */
    public OpponentServiceImpl(CharacterRepository characterRepository,
                               EnemyRepository enemyRepository,
                               EquipmentRepository equipmentRepository,
                               MediaRepository mediaRepository) {
        this.characterRepository = characterRepository;
        this.equipmentRepository = equipmentRepository;
        this.enemyRepository = enemyRepository;
        this.mediaRepository = mediaRepository;
        this.demoOpponents();
    }

    /**
     * Setup Equipment, Character and Enemy for the demo
     */
    @Transactional
    private void demoOpponents() {
        // Setup Equipment
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BOOTS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_GLOVES, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));

        // Setup non-decorated Character
        Character ch = new Dwarf("Jeffrey Oomen", new Stats(), null);
        ch.getStats().setStrength(40);
        ch.getStats().setStrengthAccuracy(100);
        ch.setActionBehavior(new NormalAttack());
        characterRepository.save(ch);

        Inventory inv = ch.getInventory();

        ch.getInventory().addUsable(equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10)));

        inv =  ch.getInventory();

        // Add some items to inventory

        // Setup Enemy
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        EnemyBuilderDirector enemyDirector = new EnemyBuilderDirector(enemyBuilder);
        Stats stats = new Stats();
        stats.setHitpoints(1000);
        stats.setCurrentHitpoints(1000);
        stats.setArchery(999);
        stats.setDefense(5);
        stats.setDefenseAccuracy(10);
        ArrayList<Usable> lootList = new ArrayList<>();
        // Find media image
        Media media = mediaRepository.findOne(2L);
        Enemy enemy = enemyDirector.createEnemy("Bram", media, "End boss", new SpecialAttack(), stats, lootList);

        enemy = enemyRepository.save(enemy);
    }

    /**
     * Gets the Character by id
     * @param id the id of the Character to be found
     * @return the Character with the specified id
     */
    @Transactional(readOnly = true)
    public Character findCharacterById(long id) {
        return characterRepository.findOne(id);
    }

    /**
     * Gets the Enemy by id
     * @param id the id of the Enemy to be found
     * @return the Enemy with the specified id
     */
    @Transactional(readOnly = true)
    public Enemy findEnemyById(long id) {
        return enemyRepository.findOne(id);
    }
}
