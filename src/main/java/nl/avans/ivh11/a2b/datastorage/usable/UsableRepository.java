package nl.avans.ivh11.a2b.datastorage.usable;

import nl.avans.ivh11.a2b.domain.usable.Usable;
import org.springframework.data.repository.CrudRepository;

public interface UsableRepository extends CrudRepository<Usable, Long>
{
    // Auto-implemented
}

