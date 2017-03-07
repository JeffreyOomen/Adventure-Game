package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class Heal implements ActionBehavior {
    @Override
    public void action(Opponent opponent) {
        opponent.heal(10);
    }
}
