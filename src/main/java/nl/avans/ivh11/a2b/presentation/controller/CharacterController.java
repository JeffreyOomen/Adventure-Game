package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.service.OpponentService;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize("isAuthenticated()")
public class CharacterController
{
    @Autowired
    private OpponentService opponentService;

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
            opponentService.saveCharacter(character);
        }

        return "redirect:/";
    }
}
