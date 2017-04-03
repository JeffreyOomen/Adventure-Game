package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.auth.RoleRepository;
import nl.avans.ivh11.a2b.datastorage.auth.UserRepository;
import nl.avans.ivh11.a2b.domain.auth.Role;
import nl.avans.ivh11.a2b.domain.auth.User;
import nl.avans.ivh11.a2b.domain.character.CharacterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;

/**
 * User service
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Persist a new User
     * @param user User
     */
    @Override
    public User create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPlainPassword()));
        user.setPlainPassword(null);
        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null) {
            role = new Role();
            role.setName("ROLE_USER");
        }
        user.addRole(role);
        return userRepository.save(user);
    }

    /**
     * Save the user and update password in necessary
     * @param user User
     */
    @Override
    public User save(User user) {
        if(user.getPlainPassword() != null && !user.getPlainPassword().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPlainPassword()));
            user.setPlainPassword(null);
        }
        return userRepository.save(user);
    }

    /**
     * Find by username
     * @param username String username
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
