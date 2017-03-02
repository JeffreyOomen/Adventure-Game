package nl.avans.ivh11.a2b.datastorage.character;

import org.springframework.data.repository.CrudRepository;
import nl.avans.ivh11.a2b.domain.character.Character;

public interface CharacterRepository extends CrudRepository<Character, Long>
{
    // Auto-implemented
}
