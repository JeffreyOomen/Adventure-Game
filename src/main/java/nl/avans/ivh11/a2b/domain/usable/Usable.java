package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Media;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
    protected String imageUrl;
//    @OneToOne()
//    protected Media media;

    /**
     * Use usable item on given character
     * @param character
     */
    public abstract void use(Character character);
}
