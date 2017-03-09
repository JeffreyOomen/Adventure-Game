package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class HealPotion extends Usable {

    public HealPotion(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public void use(Character character) {
//        character.setState(character.getPoweredState()); // TODO: implementeren
    }

}
