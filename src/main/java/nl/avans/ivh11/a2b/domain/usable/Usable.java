package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Inheritance(strategy =  InheritanceType.SINGLE_TABLE)
public abstract class Usable {

    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String description;
//    @OneToOne(cascade = CascadeType.ALL)
//    protected Stats stats;

    public abstract void use(Character character);
}
