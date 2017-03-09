package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Stats;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Equipment")
@Getter
@Setter
public class Equipment extends Usable {

    protected UsableType type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "STATS_ID")
    protected Stats stats;

    public Equipment(String name, String description, UsableType type, Stats stats) {
        super();
        this.name = name;
        this.description = description;
        this.type = type;
        this.stats = stats;
    }

    @Override
    public void use(Character character) {
//        character.setState(character.mountEquipment(this.type, this);); // TODO: implementeren
    }

}
