package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;

/**
 * Created by isazu on 6-3-2017.
 */
public class Battle {
    public Battle (Character ch, Enemy en) {
    }

    public void playTurn(Command command) {
        command.execute();
    }
}

