package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;
import java.util.Random;

/**
 * HealPotion
 * is a usable that can be created by the UsableFactory.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HealPotion extends Usable {
    private int level;
    private int healAmount = 0;
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
    public void use(Character character) {
        character.heal(setHealAmount(character.getCurrentHitpoints()));
    }

    /**
     * setHealAmount
     * randomly increases current HP minus level
     * @param maxHp
     * @return int
     */
    private int setHealAmount(int maxHp) {
        return new Random().nextInt(maxHp - level);
    }

}
