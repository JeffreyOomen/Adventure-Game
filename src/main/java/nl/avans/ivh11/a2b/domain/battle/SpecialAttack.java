package nl.avans.ivh11.a2b.domain.battle;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;
import nl.avans.ivh11.a2b.domain.util.Random;

/**
 * Attack the Opponent with a special attack
 */
public class SpecialAttack implements ActionBehavior
{
    /**
     * Attack the enemy with a special attack
     * @param attacker
     * @param defender
     */
    public void action(Opponent attacker, Opponent defender) {

        Double damage = Random.getInstance().randomDamage(
                // Attacker determine strength
                attacker.getStats().getStrength(),
                attacker.getStats().getStrengthAccuracy(),
                // Defender determine defense
                defender.getStats().getDefense(),
                defender.getStats().getDefenseAccuracy()
        ) * 1.20;
        defender.takeDamage(damage.intValue());
    }
}
