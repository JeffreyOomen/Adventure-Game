package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.auth.User;

/**
 * User service interface
 */
public interface UserService {

    public void save(User user);

    public User findByUsername(String username);
}
