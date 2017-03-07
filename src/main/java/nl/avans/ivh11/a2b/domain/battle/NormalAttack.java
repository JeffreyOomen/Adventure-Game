package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class NormalAttack implements ActionBehavior {
    @Override
    public void action(Opponent opponent) {
        opponent.takeDamage(10); //TODO Get from Random
    }
}
