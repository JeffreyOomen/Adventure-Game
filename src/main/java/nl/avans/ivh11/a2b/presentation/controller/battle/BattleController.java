package nl.avans.ivh11.a2b.presentation.controller.battle;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.domain.battle.ActionCommand;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.presentation.model.BattleModel;
import nl.avans.ivh11.a2b.service.battle.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/battle")
public class BattleController {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private BattleService battleService;

    private Character character;
    private Enemy enemy;

    public BattleController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String startBattle(Model uiModel) {

        // Initialize and assign character and enemy
        character = battleService.findCharacter(1L);
        enemy = battleService.findEnemy(1L);

        // Start new battle
        battleService.startBattle(character, enemy);

        uiModel.addAttribute("character", character);
        uiModel.addAttribute("enemy", enemy);

        System.out.println("battle started");

        System.out.println("Battle is started between: " + character.getName() + " and " + enemy.getName());

        // Return view
        return "battle";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public BattleModel battleAction() {
        // do action
        battleService.battleMove();

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
