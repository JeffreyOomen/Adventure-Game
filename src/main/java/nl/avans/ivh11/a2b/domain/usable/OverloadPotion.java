package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

/**
 * OverloadPotion
 * is a usable that can be created by the UsableFactory.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class OverloadPotion extends Usable {

    public OverloadPotion(UsableType type, int level) {
        this.type = type;
        this.name = "Overload potion";
        this.description = "Gives super power";
    }

    /**
     * use
     * when this item is used the character is in a powered state.
     * @param character
     */
    @Override
    public void use(Character character) {
        character.setState(character.getPoweredState());
    }

}
