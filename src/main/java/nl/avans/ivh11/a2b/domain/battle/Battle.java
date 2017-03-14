package nl.avans.ivh11.a2b.domain.battle;

/**
 * Class to command actions every turn
 */
public class Battle {

    /**
     * Call execute function on the assigned command
     * @param command implementation of command interface
     */
    public void playTurn(Command command) {
        command.execute();
    }
}

