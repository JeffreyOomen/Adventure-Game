package nl.avans.ivh11.a2b.domain.util;

import nl.avans.ivh11.a2b.domain.util.observer.Observable;

public interface Opponent extends Observable
{
    /**
     * Performs an action against the Opponent
     * @param opponent the Character's Opponent
     */
    public void performAction(Opponent opponent);

    /**
     * Determines if the Character is still alive
     * @return true if the Character is alive, false otherwise
     */
    public boolean isAlive();

    /**
     * Take damage as result van an enemy attack
     * @param hit int damage to take
     */
    public void takeDamage(int hit);

    /**
     * Adds the given hitpoints to the currentHitpoints
     * @param hitPoints int
     */
    public void heal(int hitPoints);

    /**
     * Receive an incoming XP bounty
     */
    public void receiveXp(int xp);

    public Stats getStats();

    public String getName();
}
