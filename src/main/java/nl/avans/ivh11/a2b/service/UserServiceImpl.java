package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.auth.RoleRepository;
import nl.avans.ivh11.a2b.datastorage.auth.UserRepository;
import nl.avans.ivh11.a2b.domain.auth.Role;
import nl.avans.ivh11.a2b.domain.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null) {
            role = new Role();
            role.setName("ROLE_USER");
        }
        user.addRole(role);
        userRepository.save(user);
    }

    /**
     * Save the user and update password in necessary
     * @param user User
     */
    @Override
    public void save(User user) {
        if(!user.getPasswordConfirm().isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
        }
        userRepository.save(user);
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
