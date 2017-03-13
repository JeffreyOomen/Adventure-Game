package nl.avans.ivh11.a2b.presentation.controller.battle;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/battle")
public class BattleController {

    private CharacterRepository characterRepository;

    @Autowired
    public BattleController(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @GetMapping
    public String battleAction() {
        Character character = characterRepository.findOne(1L);
        character.setActionBehavior(new NormalAttack());
        Enemy enemy = new Enemy(new Stats());


        return "battle";
    }
}
