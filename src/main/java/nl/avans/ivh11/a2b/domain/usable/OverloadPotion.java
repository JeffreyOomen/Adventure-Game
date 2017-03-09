package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

/**
 * HealPotion
 * is a usable that can be created by the UsableFactory.
 */
@Entity
@Getter
@Setter
public class OverloadPotion extends Usable {

    //TODO: via level kunnen we hier stats omhoog gooien van character ofzo
    public OverloadPotion(UsableType type, int level) {
        this.type = type;
        this.name = "Overload potion";
        this.description = "Gives super power";
    }

    @Override
    public void use(Character character) {
        character.setState(character.getPoweredState());
    }

}
