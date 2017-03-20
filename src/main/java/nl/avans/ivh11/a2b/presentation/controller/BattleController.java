package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class BattleController
{
    @Autowired
    private BattleService battleService;

    @Autowired
    private OpponentService opponentService;

    private Character character;
    private Enemy enemy;

    private List<Enemy> possibleEnemies;

    @PostConstruct
    public void init() {
        System.out.println("Init method called");
        possibleEnemies = this.getEnemies();
    }

    /**
     * Starts a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startBattle(Model uiModel) {
        // Initialize and assign character
        this.character = opponentService.findCharacterById(1L);

        if (this.enemy == null || !this.enemy.isAlive() || this.character.isAlive()) {
            // Get random enemy from the list
            this.enemy = this.possibleEnemies.get(CustomRandom.getInstance().randomEnemy(this.possibleEnemies.size()));

            // make sure a new battle always starts against an enemy with full hp
            this.enemy.regenerate();

            // Start new battle
            battleService.startBattle(this.character, this.enemy);

            uiModel.addAttribute("character", this.character);
            uiModel.addAttribute("enemy", this.enemy);

            return "battle";
        }

        return "character/regenerate";
    }

    /**
     * Ends a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    public String endBattle(Model uiModel) {
        if (!this.enemy.isAlive()) { // character can only quit when enemy is beaten
            uiModel.addAttribute("character", this.character);
            uiModel.addAttribute("enemy", this.enemy);

            // remove enemy from the possible enemy list
            this.possibleEnemies.remove(this.enemy);

            // give the character xp
            this.character.receiveXp(this.enemy.getHitpoints());
            this.opponentService.saveCharacter(this.character);

            return "home";
        }

        return "redirect:/start";
    }

    /**
     * Executes a battle event with a normal attack for the Character
     */
    @RequestMapping(value = "/battle/normalAttack", method = RequestMethod.POST)
    @ResponseBody
    public BattleModel attack() {
        this.battleService.attack();
        return this.battleReport();
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/battle/specialAttack", method = RequestMethod.POST)
    @ResponseBody
    public BattleModel specialAttack() {
        this.battleService.specialAttack();
        return this.battleReport();
    }

    /**
     * Executes a battle event with a heal for the Character
     */
    @RequestMapping(value = "/battle/heal", method = RequestMethod.POST)
    @ResponseBody
    public BattleModel heal() {
        this.battleService.heal();
        return this.battleReport();
    }

    /**
     * Reports about the events happened in the battle after every move
     * by the Character.
     * @return an model containing battle information
     */
    private BattleModel battleReport() {
        String battleReport = "";
        battleReport += battleService.getBattle().getNextMessage();
        battleReport += "<br/><br/>";
        battleReport += battleService.getBattle().getNextMessage();

        List<String> messages = battleService.getBattle().getMessages();

        for (String message: messages) {
            battleReport += "<br/><br/>";
            battleReport += message;
        }

        // Return view model as JSON
        return new BattleModel(
                character.isAlive(),
                enemy.isAlive(),
                character.getStats(),
                enemy.getStats(),
                battleReport
        );
    }

    /**
     * Gets all enemies
     * @return a List with all enemies
     */
    private List<Enemy> getEnemies() {
        return opponentService.findAllEnemies();
    }
}
