package nl.avans.ivh11.a2b.datastorage.usable;

import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface MediaRepository extends CrudRepository<Media, Long>
{
    Media findByName(String name);
}

