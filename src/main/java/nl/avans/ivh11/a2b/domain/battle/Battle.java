package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Class to command actions every turn
 */
public class Battle implements Observer
{

    private Queue<String> messages;

    public Battle() {
        this.messages = new ArrayDeque<>();
    }

    /**
     * Call execute function on the assigned command
     * @param command implementation of command interface
     */
    public void playTurn(Command command) {
        command.execute();
    }

    /**
     * Add message
     * @param message
     */
    @Override
    public void update(String message) {
        this.messages.add(message);
    }
}

