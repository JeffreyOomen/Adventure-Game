package nl.avans.ivh11.a2b.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Security Service interface
 */
public interface SecurityService {

    public String findLoggedInUsername();

    public void autologin(String username, String password);
}
