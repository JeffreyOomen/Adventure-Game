package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.service.CharacterService;
import nl.avans.ivh11.a2b.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model uiModel) {
        User user = securityService.findLoggedInUser();
        Character character = user.getCharacter();
        uiModel.addAttribute("character", character);

        return "home";
    }

    /**
     * Ends a battle between a Character and an Enemy
     *
     * Brings the Character back to live
     * @return A view
     */
    @RequestMapping(value = "/regenerate", method = RequestMethod.POST)
    public String doRegenerateCharacter() {
        User user = securityService.findLoggedInUser();
        Character character = user.getCharacter();

        // Only regenerate when character is not alive
        if (!character.isAlive()) {
            character.regenerate();
            characterService.save(character);
        }

        return "redirect:/";
    }

}
