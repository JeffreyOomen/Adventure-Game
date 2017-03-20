package nl.avans.ivh11.a2b.presentation.controller;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.service.SecurityService;
import nl.avans.ivh11.a2b.service.UserService;
import nl.avans.ivh11.a2b.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Authentication Controller
 * Handles registration and login
 */
@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        System.out.println("Register has errors: " + bindingResult.hasErrors());
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/battle";
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
//
//    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
//    public String welcome(Model model) {
//        return "welcome";
//    }
}
