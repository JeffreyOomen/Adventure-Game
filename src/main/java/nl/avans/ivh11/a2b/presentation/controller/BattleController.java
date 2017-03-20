package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class BattleController
{
    @Autowired
    private BattleService battleService;

    @Autowired
    private OpponentService opponentService;

    @Autowired
    private CharacterService characterService;

    private Character character;
    private Enemy enemy;

    /**
     * Starts a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String startBattle(Model uiModel) {
        // Initialize and assign character and enemy
        this.character = characterService.findById(2L);
        this.enemy = opponentService.findEnemyById(1L);

        // Start new battle
        battleService.startBattle(character, enemy);

        uiModel.addAttribute("character", character);
        uiModel.addAttribute("enemy", enemy);

        return "battle";
    }

    /**
     * Executes a battle event with a normal attack for the Character
     */
    @RequestMapping(value = "/battle/normal_attack", method = RequestMethod.POST)
    @ResponseBody
    public BattleModel attack() {
        System.out.println("Controller attack method called");
        this.battleService.attack();
        return this.battleReport();
    }

    /**
     * Executes a battle event with a special attack for the Character
     */
    @RequestMapping(value = "/battle/special_attack", method = RequestMethod.POST)
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

        // Return view model as JSON
        return new BattleModel(
                character.getInventory().getInventory(),
                character.isAlive(),
                enemy.isAlive(),
                character.getStats(),
                enemy.getStats(),
                battleReport
        );
    }
}
