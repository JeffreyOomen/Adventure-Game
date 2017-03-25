package nl.avans.ivh11.a2b.domain.character;

import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.decoration.Archer;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.character.decoration.Warrior;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.usable.UsableType;
import nl.avans.ivh11.a2b.domain.util.Stats;

/**
 * Character factory
 */
public class CharacterFactory {

    /**
     * Create a new Character using the given parameters
     * @param name the character name
     * @param race the character race (Dwarf, Elf, Troll)
     * @param specialization the character specialization (Warrior, Archer, Mage)
     * @return Character
     */
    public static Character createCharacter(String name, String race, String specialization) {
        Character character = createCharacterRaceInstance(name, race);

        switch (specialization) {
            case "archer":
                character = new Archer(character);
                character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_BOW);
                break;
            case "mage":
                character = new Mage(character);
                character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_STAFF);
                break;
            case "warrior":
                character = new Warrior(character);
                character.setAttackStyle(UsableType.EQUIPMENT_WEAPON_SWORD);
                break;
            default:
                throw new IllegalArgumentException("Provide a valid specialization");
        }

        character.setActionBehavior(new NormalAttack());
        character.setState(NormalState.getInstance());
        return character;
    }

    /**
     * Create a new Character race (Dwarf, Elf, Troll)
     * @param name the character name
     * @param race the character race
     * @return Character
     */
    private static Character createCharacterRaceInstance(String name, String race) {
        Character character;

        switch (race) {
            case "dwarf":
                character = new Dwarf(name, new Stats(), null);
                break;
            case "elf":
                character = new Elf(name, new Stats(), null);
                break;
            case "troll":
                character = new Troll(name, new Stats(), null);
                break;
            default:
                throw new IllegalArgumentException("Provide a valid race");
        }

        return character;
    }
}
