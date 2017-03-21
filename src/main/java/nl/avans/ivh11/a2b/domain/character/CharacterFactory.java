package nl.avans.ivh11.a2b.domain.character;

import nl.avans.ivh11.a2b.domain.battle.NormalAttack;
import nl.avans.ivh11.a2b.domain.character.decoration.Archer;
import nl.avans.ivh11.a2b.domain.character.decoration.Mage;
import nl.avans.ivh11.a2b.domain.character.decoration.Warrior;
import nl.avans.ivh11.a2b.domain.character.state.NormalState;
import nl.avans.ivh11.a2b.domain.util.Stats;

/**
 * Character factory
 */
public class CharacterFactory {

    public static Character getCharacter(String name, String race, String specialization) {
        Character character = getCharacterRaceInstance(name, race);

        switch (specialization) {
            case "archer":
                character = new Archer(character);
                break;
            case "mage":
                character = new Mage(character);
                break;
            case "warrior":
                character = new Warrior(character);
                break;
            default:
                throw new IllegalArgumentException("Provide a valid specialization");
        }

        character.setActionBehavior(new NormalAttack());
        character.setState(NormalState.getInstance());
        return character;
    }

    private static Character getCharacterRaceInstance(String name, String race) {
        Character character;

        switch (race) {
            case "dwarf":
                character = new Dwarf(name, new Stats());
                break;
            case "elf":
                character = new Elf(name, new Stats());
                break;
            case "troll":
                character = new Troll(name, new Stats());
                break;
            default:
                throw new IllegalArgumentException("Provide a valid race");
        }

        return character;
    }
}
