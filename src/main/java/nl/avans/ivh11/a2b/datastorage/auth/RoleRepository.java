package nl.avans.ivh11.a2b.datastorage.auth;

import nl.avans.ivh11.a2b.domain.auth.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Role repository
 */
public interface RoleRepository extends CrudRepository<Role, Long> {
}
