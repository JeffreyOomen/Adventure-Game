package nl.avans.ivh11.a2b.domain.util;

public interface Opponent
{
    public void performAction(Opponent opponent);

    public boolean isAlive();

    public void takeDamage(int hit);

    public void heal(int hitPoints);

    public void receiveXp(int xp);
}
