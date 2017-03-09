package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Usable {

    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String description;

    public abstract void use(Character character);
}
