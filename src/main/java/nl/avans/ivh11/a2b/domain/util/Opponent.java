package nl.avans.ivh11.a2b.domain.util;

public interface Opponent
{
    public void performAction(Opponent opponent);

    public boolean isAlive();

    public void bearHit(int hit);

    public void receiveXp(int xp);
}
