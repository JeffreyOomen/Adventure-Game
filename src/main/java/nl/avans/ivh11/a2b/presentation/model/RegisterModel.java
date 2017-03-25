package nl.avans.ivh11.a2b.presentation.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Register model
 */
@Getter
@Setter
public class RegisterModel {
    private String username;

    private String password;

    private String passwordConfirm;

    private String characterName;

    private String characterRace;

    private String characterSpecialization;
}
