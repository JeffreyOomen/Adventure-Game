package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.presentation.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validates the RegisterModel
 */
@Component
public class RegistrationValidator implements Validator {

    @Autowired
    private UserService userService;

    /**
     * Returns whether this validator supports the given class
     * @param aClass Class to check
     * @return boolean
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    /**
     * Validate the model
     * @param o Object model to validate. Should be supported
     * @param errors Error
     */
    @Override
    public void validate(Object o, Errors errors) {
        RegisterModel registerModel = (RegisterModel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (registerModel.getUsername().length() < 4 || registerModel.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.registerForm.username");
        }
        if (userService.findByUsername(registerModel.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.registerForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (registerModel.getPassword().length() < 6) {
            errors.rejectValue("password", "Size.registerForm.password");
        }

        if (!registerModel.getPasswordConfirm().equals(registerModel.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.registerForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characterName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characterRace", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "characterSpecialization", "NotEmpty");
    }
}
