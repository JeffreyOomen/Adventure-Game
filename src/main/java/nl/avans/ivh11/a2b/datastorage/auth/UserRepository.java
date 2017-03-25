package nl.avans.ivh11.a2b.datastorage.auth;

import nl.avans.ivh11.a2b.domain.auth.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User repository
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
