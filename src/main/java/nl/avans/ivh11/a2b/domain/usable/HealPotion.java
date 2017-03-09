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
public class HealPotion extends Usable {
    private int level;

    //TODO: via level kunnen we hier misschien een percentage van je health omhoog gooien
    public HealPotion(UsableType type, int level) {
        this.type = type;
        this.name = "Heal potion";
        this.description = "Increases hitpoints";
    }

    @Override
    public void use(Character character) {
        character.setState(character.getPoweredState());
    }

}
