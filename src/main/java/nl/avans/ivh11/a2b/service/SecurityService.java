package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.auth.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Security Service interface
 */
public interface SecurityService {

    /**
     * Get the username of the currently logged in user
     * @return String username
     */
    String findLoggedInUsername();

    /**
     * Find the currently logged in user
     * @return User
     */
    User findLoggedInUser();

    /**
     * Automatically login user using username and password
     * @param username String username
     * @param password String plain password
     */
    void autologin(String username, String password);
}
