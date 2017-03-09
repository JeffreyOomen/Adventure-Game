package nl.avans.ivh11.a2b.domain.usable;

import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

@Entity
public class OverloadPotion extends Usable {

    public OverloadPotion(String name, String description) {
        name = name;
        description = description;
    }

    @Override
    public void use(Character character) {
//        character.setState(character.getPoweredState()); // TODO: implementeren
    }

}
