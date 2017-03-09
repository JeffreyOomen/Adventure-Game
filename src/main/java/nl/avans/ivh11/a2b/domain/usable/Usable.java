package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Usable abstract superclass for potions and equipment, used by the UsableFactory to create usable objects.
 */
@Entity
@Getter
@Setter
public abstract class Usable {

    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String description;
    protected UsableType type;

    /**
     * Use usable item on given character
     * @param character
     */
    public abstract void use(Character character);
}
