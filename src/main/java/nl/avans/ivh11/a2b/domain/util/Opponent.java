package nl.avans.ivh11.a2b.domain.util;

import nl.avans.ivh11.a2b.domain.util.observer.Observable;

public interface Opponent extends Observable
{
    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    void performAction(Opponent opponent);

    /**
     * Determines if the Character is still alive
     * @return true if the Character is alive, false otherwise
     */
    boolean isAlive();

    /**
     * Take damage as result van an enemy attack
     * @param hit int damage to take
     */
    void takeDamage(int hit);

    /**
     * Adds the given hitpoints to the currentHitpoints
     * @param hitPoints int
     */
    void heal(int hitPoints);

    /**
     * Receive an incoming XP bounty
     */
    void receiveXp(int xp);

    Stats getStats();

    String getName();
}
