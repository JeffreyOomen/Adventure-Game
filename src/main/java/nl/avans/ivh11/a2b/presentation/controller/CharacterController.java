package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CharacterController
{
    @Autowired
    private OpponentService opponentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model uiModel) {
        uiModel.addAttribute("character", opponentService.findCharacterById(1L));

        return "home";
    }

    /**
     * Returns the view where the Character can be brought back to live
     * @return A view
     */
    @RequestMapping(value = "/regenerate", method = RequestMethod.GET)
    public String regenerateCharacter() {
        return "character/regenerate";
    }

    /**
     * Brings the Character back to live
     * @return A view
     */
    @RequestMapping(value = "/regenerate", method = RequestMethod.POST)
    public String doRegenerateCharacter() {
        // Initialize and assign character and enemy
        Character character = opponentService.findCharacterById(1L);

        // Only regenerate when character is not alive
        if (!character.isAlive()) {
            character.regenerate();
            opponentService.saveCharacter(character);
        }

        return "redirect:/";
    }
}
