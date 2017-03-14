package nl.avans.ivh11.a2b.datastorage.character;

import nl.avans.ivh11.a2b.domain.usable.Equipment;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, Long>
{
    // Auto-implemented
}
