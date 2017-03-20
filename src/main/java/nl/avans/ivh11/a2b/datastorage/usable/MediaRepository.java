package nl.avans.ivh11.a2b.datastorage.usable;

import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
import org.springframework.data.repository.CrudRepository;

public interface MediaRepository extends CrudRepository<Media, Long>
{
    // Auto-implemented
}

