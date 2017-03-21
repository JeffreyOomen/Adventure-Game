package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.OpponentService;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
public class BattleController
{
    @Autowired
    private BattleService battleService;

    @Autowired
    private OpponentService opponentService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    private Character character;
    private Enemy enemy;

    /**
     * Starts a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String startBattle(Model uiModel) {
        String username =  securityService.findLoggedInUsername();
        User user = userService.findByUsername(username);
        this.character = user.getCharacter();
        this.enemy = opponentService.findEnemyById(1L);

        battleService.startBattle(this.character, this.enemy);

        uiModel.addAttribute("character", this.character);
        uiModel.addAttribute("enemy", this.enemy);

        return "battle";
    }

    /**
     * Ends a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/battle/end", method = RequestMethod.GET)
    public String endBattle(Model uiModel) {
        uiModel.addAttribute("character", this.character);
        uiModel.addAttribute("enemy", this.enemy);

        return "home";
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

        // Return view model as JSON
        return new BattleModel(
                character.isAlive(),
                enemy.isAlive(),
                character.getStats(),
                enemy.getStats(),
                battleReport
        );
    }
}
