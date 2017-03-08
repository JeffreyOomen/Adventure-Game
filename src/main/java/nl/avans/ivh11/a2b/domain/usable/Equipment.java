package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.CharacterStats;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Equipment extends Usable {

//    @Id
//    @GeneratedValue
//    protected long id;
    protected UsableType type;
    protected CharacterStats stats;

    public Equipment(UsableType type, CharacterStats stats) {
        this.type = type;
        this.stats = stats;
    }

    @Override
    public void use(Character character) {
//        character.setState(character.mountEquipment(this)); // TODO: implementeren
    }

}
