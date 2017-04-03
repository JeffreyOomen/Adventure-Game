package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.battle.SpecialAttack;
import nl.avans.ivh11.a2b.domain.character.CharacterFactory;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilder;
import nl.avans.ivh11.a2b.domain.enemy.EnemyBuilderDirector;
import nl.avans.ivh11.a2b.domain.util.Media;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * StartupServiceImpl
 * makes sure all necessary items are persisted in the database before the game is started.
 */
@Component
public class StartupServiceImpl implements StartupService {

    private MediaService mediaService;
    private EnemyRepository enemyRepository;
    private UserService userService;

    @Autowired
    public StartupServiceImpl(MediaService mediaService, EnemyRepository enemyRepository, UserService userService) {
        this.mediaService = mediaService;
        this.enemyRepository = enemyRepository;
        this.userService = userService;
    }

    @Override
    public void initializeGame() {
        // Important order, first all images are needed before initializing enemies
        this.initializeImages();
        this.initializeEnemies();
        this.initializeDemoUser();
    }

    /**
     * Initialize enemies
     */
    private void initializeEnemies() {
        // Find media image

        // Initialize an enemy builder for creating enemies
        EnemyBuilderDirector enemyBuilderDirector = new EnemyBuilderDirector(new EnemyBuilder());

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(enemyBuilderDirector.createEnemy("Bandit", mediaService.findByName("bandit"), "Very strong bandit that lives int the forest.", new SpecialAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Bat", mediaService.findByName("bat"), "Nasty bat.", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Dragon", mediaService.findByName("dragon"), "Dragon.", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Ghost", mediaService.findByName("ghost"), "Scary Ghost", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Giant", mediaService.findByName("giant"), "Big robust Giant", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Goblin", mediaService.findByName("goblin"), "Little but strong Goblin", new SpecialAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Mummy", mediaService.findByName("mummy"), "Mummy from Egypt", new NormalAttack()));
        enemies.add(enemyBuilderDirector.createEnemy("Spider", mediaService.findByName("spider"), "Dark spider", new NormalAttack()));

        // Persist all enemies in the database
        for(Enemy e : enemies) {
            enemyRepository.save(e);
        }

    }

    /**
     * initializeImages
     * Persist all possible Media items that can be used through the game.
     */
    private void initializeImages() {
        ArrayList<Media> mediaList = new ArrayList<>();

        // Enemies
        mediaList.add(new Media("../images/rpg-bandit.png", "bandit"));
        mediaList.add(new Media("../images/rpg-bat.png", "bat"));
        mediaList.add(new Media("../images/rpg-dragon.png", "dragon"));
        mediaList.add(new Media("../images/rpg-ghost.png", "ghost"));
        mediaList.add(new Media("../images/rpg-giant.png", "giant"));
        mediaList.add(new Media("../images/rpg-goblin.png", "goblin"));
        mediaList.add(new Media("../images/rpg-mummy.png", "mummy"));
        mediaList.add(new Media("../images/rpg-spider.png", "spider"));

        // Character races
        mediaList.add(new Media("./images/rpg-dwarf.png", "dwarf"));
        mediaList.add(new Media("../images/rpg-elf.png", "elf"));
        mediaList.add(new Media("../images/rpg-warrior.png", "warrior"));
        mediaList.add(new Media("../images/rpg-troll.png", "troll"));

        // Loot
        // TODO: implementeren i.p.v harcoded
        mediaList.add(new Media("../images/rpg-potion.png", "potion"));
        mediaList.add(new Media("../images/rpg-sword.png", "sword"));

        // Save all media items
        for(Media m : mediaList) {
            this.mediaService.save(m);
        }

    }

    /**
     * Create demo user with username test and password password1
     */
    private void initializeDemoUser() {
        User user = new User();
        user.setUsername("test");
        user.setPlainPassword("password1");
        user.setCharacter(CharacterFactory.createCharacter("Character1", "dwarf", "archer"));
        this.userService.create(user);
    }

}
