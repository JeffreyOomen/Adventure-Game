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
     * @param messages
     */
    @Override
    public void update(List<String> messages) {
        this.messages.addAll(messages);
    }

    @Override
    public void update(String message) {
        System.out.println("Battle message: " + message);
        this.messages.add(message);
    }

    /**
     * Gets the battle messages
     * @return a List with messages
     */
    public List<String> getMessages() {
        return this.messages;
    }
}

