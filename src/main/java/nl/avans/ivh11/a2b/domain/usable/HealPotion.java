package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.character.CharacterStub;

import javax.persistence.Entity;
import java.util.Random;

/**
 * HealPotion
 * is a usable that can be created by the UsableFactory.
 */
@Entity
@Getter
@Setter
public class HealPotion extends Usable {
    private int level;
    public HealPotion(UsableType type, int level) {
        this.type = type;
        this.level = level;
        this.name = "Heal potion";
        this.description = "Increases hitpoints";
    }

    /**
     * use
     * heal potion is used, current HP is randomly increased. See also setHealAmount()
     * @param character
     */
    @Override
    public void use(CharacterStub character) {
        character.getStats().setHitpoints(character.getStats().getHitpoints() + setHealAmount(character.getCurrentHitpoints()));
    }

    /**
     * setHealAmount
     * randomly increases current HP minus level
     * @param maxHp
     * @return int
     */
    private int setHealAmount(int maxHp) {
        // e.g. hp 50, lvl 20: max heal = 30;
        return new Random().nextInt(maxHp - level);
    }

}
