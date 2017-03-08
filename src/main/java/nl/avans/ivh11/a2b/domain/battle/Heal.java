package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class Heal implements ActionBehavior
{
    /**
     * Heal the given Opponent.
     * This would typically be the current user
     * @param opponent Opponent
     */
    @Override
    public void action(Opponent opponent) {
        opponent.heal(10);
    }
}
