package nl.avans.ivh11.a2b.service.enemy;


import nl.avans.ivh11.a2b.datastorage.character.EquipmentRepository;
import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.battle.ActionBehavior;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.Dwarf;
import nl.avans.ivh11.a2b.domain.character.Elf;
import nl.avans.ivh11.a2b.domain.character.decoration.CharacterDecorator;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Equipment;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/")
public class EnemyManager
{
    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    private EnemyRepository enemyRepository;

//    @Autowired
//    private EquipmentRepository equipmentRepository;

    public EnemyManager(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
        this.runDemo();
    }

    /**
     * Test
     */
    public void runDemo() {
        EnemyBuilder enemyBuilder = new EnemyBuilder();
        EnemyBuilderDirector enemyDirector = new EnemyBuilderDirector(enemyBuilder);
        Stats stats = new Stats();
        stats.setHitpoints(100);
        stats.setArchery(999);
        ArrayList<Usable> lootList = new ArrayList<>();
        Enemy enemy = enemyDirector.createEnemy("Bram", "End boss", new SpecialAttack(), stats, lootList);

        enemy = enemyRepository.save(enemy);

        // print new enemy
        System.out.println("=================================================================");
        System.out.println("====================="+ "New enemy added" + "===================");
        System.out.println("======= NAME: " + enemy.getName() + "======= DESCRIPTION: " + enemy.getDescription());
        System.out.println("======= LOOT: " + enemy.getLoot().toString()  + "======= ACTIONBEHAVIOUR: " + enemy.getActionBehavior().toString());
        System.out.println("=================================================================");
    }
}
