package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import java.util.*;

/**
 * Class to command actions every turn
 */
public class Battle implements Observer
{
    private List<String> messages;

    public Battle() {
        this.messages = new ArrayList<>();
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
     * @param messages a list with messages which should be logged
     */
    @Override
    public void update(List<String> messages) {
        this.messages.addAll(messages);
    }

    /**
     * Gets the battle messages which should be logged
     * @return a List with messages
     */
    public List<String> getMessages() {
        return this.messages;
    }
}

