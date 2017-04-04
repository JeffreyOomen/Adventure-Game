package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Opponent;

public interface BattleService
{
    Opponent setupBattle(Opponent character);

    String battleReport();

    void attack();

    void specialAttack();

    void heal();

    Battle getBattle();

    boolean hasOngoingBattle();
}
