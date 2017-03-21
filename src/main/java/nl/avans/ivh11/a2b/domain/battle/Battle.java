package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.observer.Observer;

import java.util.*;

/**
 * Class to command actions every turn
 */
public class Battle implements Observer
{

    private Queue<String> messages;
    private List<String> levelMessages;

    public Battle() {
        this.messages = new ArrayDeque<>();
        this.levelMessages = new ArrayList<>();
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
        for (String message : messages) {
            System.out.println("Battle message: " + message);
            this.levelMessages.add(message);
        }
    }

    @Override
    public void update(String message) {
        System.out.println("Battle message: " + message);
        this.messages.add(message);
    }

    /**
     * getNextMessage
     * get next message from Queue
     * @return String
     */
    public String getNextMessage() {
        return messages.poll();
    }

    public List<String> getMessages() {
        return this.levelMessages;
    }
}

