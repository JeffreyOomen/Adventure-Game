package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.service.OpponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CharacterController
{
    @Autowired
    private OpponentService opponentService;

    /**
     * Ends a battle between a Character and an Enemy
     * @param uiModel the model which contains battle information
     * @return A view
     */
    @RequestMapping(value = "/regenerate", method = RequestMethod.GET)
    public String regenerateCharacter(Model uiModel) {
        return "character/regenerate";
    }

    /**
     * Ends a battle between a Character and an Enemy
     * @param response
     * @return A view
     */
    @RequestMapping(value = "/doRegenerate", method = RequestMethod.GET)
    public String doRegenerateCharacter(HttpServletResponse response) {
        // Initialize and assign character and enemy
        Character character = opponentService.findCharacterById(1L);

        // only regenerate when character is not alive
        if (!character.isAlive()) {
            character.regenerate();
            opponentService.saveCharacter(character);
        }

        return "redirect:/start";
    }
}
