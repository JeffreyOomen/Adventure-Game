package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.state.PoweredState;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.BattleService;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BattleController
{
    private final BattleService battleService;

    private final SecurityService securityService;

    private Character character;
    private Opponent enemy;

    @Autowired
    public BattleController(BattleService battleService, SecurityService securityService) {
        this.battleService = battleService;
        this.securityService = securityService;
    }

    /**
     * Sets up a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    public String setupBattle(Model uiModel) {
        User user = securityService.findLoggedInUser();
        this.character = user.getCharacter();

        if (!this.battleService.hasOngoingBattle()) {
            this.enemy = this.battleService.setupBattle(character);
        }

        uiModel.addAttribute("character", this.character);
        uiModel.addAttribute("inventoryUsables", this.character.getInventory().getUsables().values());

        uiModel.addAttribute("hasOneOrMoreHealPotions", !this.character.getInventory().CheckUsableExists(UsableType.POTION_HEAL));
        uiModel.addAttribute("isInPoweredState", !this.character.getCurrentState().getName().equals(PoweredState.getInstance().getName()));
        uiModel.addAttribute("enemy", this.battleService.getBattle().getEnemy());

        battleReport();

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
     * Returns the state fragment view, which is used in the battle view.
     * When the state changes, the state can be reloaded asynchronous.
     * @param uiModel the Model in which data can be passed from the model to the view.
     * @return the state fragment view
     */
    @RequestMapping(value= "/battle/stateFragment", method = RequestMethod.GET)
    public String stateFragment(Model uiModel) {
        User user = securityService.findLoggedInUser();
        Character character = user.getCharacter();

        uiModel.addAttribute("character", character);

        return "fragments/state :: state";
    }

    /**
     * Reports about the events happened in the battle after every move
     * by the Character.
     * @return an model containing battle information
     */
    private BattleModel battleReport() {
//        String battleReport = this.battleService.battleReport();

        // Return view model as JSON
        return new BattleModel(
                character.getInventory().getUsables(),
                character.isAlive(),
                this.battleService.getBattle().getEnemy().isAlive(),
                character.getInventory().CheckUsableExists(UsableType.POTION_HEAL),
                character.getCurrentState().getName().equals(PoweredState.getInstance().getName()),
                character.getStats(),
                this.battleService.getBattle().getEnemy().getStats(),
                this.battleService.battleReport()
        );
    }
}
