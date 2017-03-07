package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

import java.io.Serializable;

public interface ActionBehavior extends Serializable
{
    public void action(Opponent opponent);
}
