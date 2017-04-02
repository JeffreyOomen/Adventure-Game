package nl.avans.ivh11.a2b.domain.character.decoration;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.character.Character;

import javax.persistence.Entity;

/**
 * Represents a Character, specialized in Archery attack style
 */
@Entity
@NoArgsConstructor
public class Archer extends CharacterDecorator
{
    private static final int STRENGTH_ACCURACY = 0;
    private static final int MAGIC_ACCURACY = 0;
    private static final int DEFENSE_ACCURACY = 10;
    private static final int ARCHERY_ACCURACY = 20;

    /**
     * Constructor
     * @param character the character to be decorated
     */
    public Archer(Character character) {
        super(character);
    }

    @Override
    public String getDescription() {
        return this.character.getDescription() + " specialized in Archery";
    }

    @Override
    public int getStrengthAccuracy() {
        if(this.character.getStrengthAccuracy() + STRENGTH_ACCURACY > 100) {
            return 100;
        }
        return this.character.getStrengthAccuracy() + STRENGTH_ACCURACY;
    }

    @Override
    public int getMagicAccuracy() {
        if(this.character.getMagicAccuracy() + MAGIC_ACCURACY > 100) {
            return 100;
        }
        return this.character.getMagicAccuracy() + MAGIC_ACCURACY;
    }

    @Override
    public int getDefenseAccuracy() {
        if(this.character.getDefenseAccuracy() + DEFENSE_ACCURACY > 100) {
            return 100;
        }
        return this.character.getDefenseAccuracy() + DEFENSE_ACCURACY;
    }

    @Override
    public int getArcheryAccuracy() {
        if(this.character.getArcheryAccuracy() + ARCHERY_ACCURACY > 100) {
            return 100;
        }
        return this.character.getArcheryAccuracy() + ARCHERY_ACCURACY;
    }
}
