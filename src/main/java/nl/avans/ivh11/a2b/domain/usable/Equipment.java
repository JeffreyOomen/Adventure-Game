package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.CharacterStats;

import javax.persistence.Entity;

@Entity
public class Equipment extends Usable {

    @Getter
    @Setter
    private EquipmentType type;
    @Getter
    @Setter
    private CharacterStats stats;

    public Equipment(EquipmentType type, CharacterStats stats) {
        this.type = type;
        this.stats = stats;
    }

    @Override
    public void use(Character character) {
//        character.setState(character.mountEquipment(this)); // TODO: implementeren
    }

}
