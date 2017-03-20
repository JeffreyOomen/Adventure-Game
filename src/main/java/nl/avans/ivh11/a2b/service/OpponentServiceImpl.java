package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.usable.Equipment;
import nl.avans.ivh11.a2b.domain.usable.EquipmentFactory;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("opponentService")
@Repository
public class OpponentServiceImpl implements OpponentService
{
    private CharacterRepository characterRepository;
    private EnemyRepository enemyRepository;
    private EquipmentRepository equipmentRepository;

    /**
     * Constructor
     * @param characterRepository repository for CRUD on Character's
     * @param enemyRepository repository for CRUD on Enemy's
     * @param equipmentRepository repository for CRUD on Equipment
     */
    public OpponentServiceImpl(CharacterRepository characterRepository,
                               EnemyRepository enemyRepository,
                               EquipmentRepository equipmentRepository) {
        this.characterRepository = characterRepository;
        this.equipmentRepository = equipmentRepository;
        this.enemyRepository = enemyRepository;
        this.demoEquipment();
        this.demoOpponents();
    }

    @Transactional
    private void demoEquipment() {
        // Setup Equipment
        EquipmentFactory equipmentFactory = new EquipmentFactory();
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_HELMET, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BODY, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_LEGS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_BOOTS, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_GLOVES, 10));
        equipmentRepository.save((Equipment)equipmentFactory.createUsable(UsableType.EQUIPMENT_WEAPON_SWORD, 10));
    }

    /**
     * Setup Equipment, Character and Enemy for the demo
     */
    @Transactional
    private void demoOpponents() {
        // Setup non-decorated Character
        Character ch = new Dwarf("Jeffrey Oomen", new Stats());
        ch.mountEquipment(UsableType.EQUIPMENT_WEAPON_SWORD, equipmentRepository.findOne(6L));
        ch.getStats().setStrength(40);
        ch.getStats().setStrengthAccuracy(100);
        ch.setActionBehavior(new NormalAttack());
        characterRepository.save(ch);

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
        Enemy enemy1 = enemyDirector.createEnemy("Bram", "End boss", new SpecialAttack(), stats, lootList);
        Enemy enemy2 = enemyDirector.createEnemy("Gerrie", "Super boss", new SpecialAttack(), new Stats(), null);

        stats.setHitpoints(300);
        stats.setCurrentHitpoints(300);
        Enemy enemy3 = enemyDirector.createEnemy("Hans", "Weak boss", new SpecialAttack(), new Stats(), null);

        enemyRepository.save(enemy1);
        enemyRepository.save(enemy2);
        enemyRepository.save(enemy3);
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
     * Find all enemies
     * @return a List of Enemy's
     */
    @Transactional
    public List<Enemy> findAllEnemies() {
        return (List<Enemy>) enemyRepository.findAll();
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

    /**
     * Saves the state of a Character
     * @param character the Character to be saved
     */
    public void saveCharacter(Character character) {
        this.characterRepository.save(character);
    }
}
