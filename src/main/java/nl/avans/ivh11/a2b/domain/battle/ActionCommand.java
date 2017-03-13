package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by isazu on 6-3-2017.
 */
public class ActionCommand implements Command {
    private Opponent attacker;
    private Opponent defender;

    public ActionCommand (Opponent attacker, Opponent defender) {
        attacker = this.attacker;
        defender = this.defender;
    }

    public void execute () {
        attacker.performAction(defender);
    }
}
