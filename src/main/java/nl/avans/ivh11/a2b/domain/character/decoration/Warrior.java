package nl.avans.ivh11.a2b.domain.character.decoration;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

/**
 * Represents a Character, specialized in Melee attack style
 */
@Entity
@NoArgsConstructor
public class Warrior extends CharacterDecorator
{
    private final static int STRENGTH_ACCURACY = 20;
    private final static int MAGIC_ACCURACY = 0;
    private final static int DEFENSE_ACCURACY = 10;
    private final static int ARCHERY_ACCURACY = 0;

    /**
     * Constructor
     * @param character the character to be decorated
     */
    public Warrior(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + " specialized in Melee";
    }

    @Override
    public int getStrengthAccuracy() {
        return this.character.getStrengthAccuracy() + STRENGTH_ACCURACY;
    }

    @Override
    public int getMagicAccuracy() {
        return this.character.getMagicAccuracy() + MAGIC_ACCURACY;
    }

    @Override
    public int getDefenseAccuracy() {
        return this.character.getDefenseAccuracy() + DEFENSE_ACCURACY;
    }

    @Override
    public int getArcheryAccuracy() {
        return this.character.getArcheryAccuracy() + ARCHERY_ACCURACY;
    }
}
