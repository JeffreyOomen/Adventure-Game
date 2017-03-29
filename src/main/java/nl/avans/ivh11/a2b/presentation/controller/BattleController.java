package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.CustomRandom;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.service.UserService;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private SecurityService securityService;

    @Autowired
    private CharacterService characterService;

    private Opponent character;
    private Opponent enemy;

    /**
     * Sets up a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String setupBattle(Model uiModel) {
        User user = securityService.findLoggedInUser();
        this.character = user.getCharacter();

        if (this.enemy == null || !this.enemy.isAlive() || this.character.isAlive()) {
            this.enemy = this.battleService.setupBattle(character);
        }

        uiModel.addAttribute("character", this.character);
        uiModel.addAttribute("enemy", this.enemy);

        return "battle";
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
        String battleReport = this.battleService.battleReport();

        // Return view model as JSON
        return new BattleModel(
                character.getInventory().getUsables(),
                character.isAlive(),
                enemy.isAlive(),
                character.getStats(),
                enemy.getStats(),
                battleReport
        );
    }
}
