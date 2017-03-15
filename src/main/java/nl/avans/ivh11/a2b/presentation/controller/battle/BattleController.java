package nl.avans.ivh11.a2b.presentation.controller.battle;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.domain.battle.ActionCommand;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.service.battle.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/start")
    public String startBattle() {

        // Initialize and assign character and enemy
        character = battleService.findCharacter(1L);
        enemy = battleService.findEnemy(1L);

        // Start new battle
        battleService.startBattle();

        System.out.println("battle started");

        System.out.println("Battle is started between: " + character.getName() + " and " + enemy.getName());
        return "battle";
//        return "Battle is started between: " + character.getName() + " and " + enemy.getName();

    }

    @RequestMapping("/fight")
    public String battleAction() {
        character.setActionBehavior(new NormalAttack());
        String msg = battleService.battleAction(character, enemy);
        System.out.println(msg);
        // return view
        return "battle";
    }
}
