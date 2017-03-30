package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.datastorage.character.CharacterRepository;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.CharacterFactory;
import nl.avans.ivh11.a2b.presentation.model.RegisterModel;
import nl.avans.ivh11.a2b.service.RegistrationValidator;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.transaction.Transactional;

/**
 * Authentication Controller
 * Handles registration and login
 */
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RegistrationValidator registrationValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("registerForm", new RegisterModel());

        return "auth/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("registerForm") RegisterModel registerForm, BindingResult bindingResult, Model model) {
        registrationValidator.validate(registerForm, bindingResult);

        System.out.println("Register has errors: " + bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        User user = new User();
        user.setUsername(registerForm.getUsername());
        user.setPlainPassword(registerForm.getPassword());
        Character character = CharacterFactory.createCharacter(registerForm.getCharacterName(), registerForm.getCharacterRace(), registerForm.getCharacterSpecialization());
        user.setCharacter(character);
        userService.save(user);

        securityService.autologin(registerForm.getUsername(), registerForm.getPasswordConfirm());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Your username or password is invalid.");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }

        return "auth/login";
    }
}
