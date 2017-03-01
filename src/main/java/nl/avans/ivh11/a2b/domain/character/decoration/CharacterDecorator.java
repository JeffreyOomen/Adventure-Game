package nl.avans.ivh11.a2b.domain.character.decoration;

import lombok.NoArgsConstructor;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Stats;
import nl.avans.ivh11.a2b.domain.util.EquipmentEnum;

import javax.persistence.*;

/**
 * With this class hierarchy, a Character can be decorated
 * with specialization in an attack style
 */
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CharacterDecorator extends Character
{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARACTER_ID")
    Character character; // this object will be decorated

    /**
     * Constructor
     * @param character an Object of Character
     */
    public CharacterDecorator(Character character) {
        this.character = character;
    }

    /**
     * Gets the Hitpoints amount
     * @return the Hitpoints amount
     */
    public int getHitpoints() {
        return this.character.getHitpoints();
    }

    /**
     * Gets the Current Hitpoints amount
     * @return the Current Hitpoints amount
     */
    public int getCurrentHitpoints() {
        return this.character.getCurrentHitpoints();
    }

    /**
     * Receive an incoming XP bounty
     */
    public void receiveXp(int earnedXp) {
        this.character.receiveXp(earnedXp);
    }

    /**
     * Sets the Attack Style
     * @param attackStyle the Attack Style to be used by the character
     */
    public void setAttackStyle(EquipmentEnum attackStyle) {
        this.character.setAttackStyle(attackStyle);
    }

    /**
     * Gets the Stats of the Character
     * @return the Stats of the Character
     */
    public Stats getStats() {
        return this.character.getStats();
    }
}
