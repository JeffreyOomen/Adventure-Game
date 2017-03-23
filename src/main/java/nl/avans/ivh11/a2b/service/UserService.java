package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.auth.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * User service interface
 */
public interface UserService {

    /**
     * Persist a new User
     * @param user User
     */
    void create(User user);

    /**
     * Save the user and update password in necessary
     * @param user User
     */
    void save(User user);

    /**
     * Get UserDetail by username
     * @param username String username
     * @return UserDatils
     */
    User findByUsername(String username);
}
