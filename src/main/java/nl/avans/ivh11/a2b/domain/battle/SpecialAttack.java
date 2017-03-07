package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 5-3-17.
 */
public class SpecialAttack implements ActionBehavior {
    @Override
    public void action(Opponent opponent) {
        opponent.takeDamage(20); //TODO Get from Random
    }
}
