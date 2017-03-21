package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Perform action commander by battle
 */
public class ActionCommand implements Command {
    private Opponent attacker;
    private Opponent defender;

    /**
     * Constructor
     * @param attacker the one performing the action
     * @param defender the one taking the damage
     */
    public ActionCommand (Opponent attacker, Opponent defender) {
        this.attacker = attacker;
        this.defender = defender;
    }

    /**
     * Execute action by the attacker on the defender
     */
    @Override
    public void execute () {
        attacker.performAction(defender);
    }
}
