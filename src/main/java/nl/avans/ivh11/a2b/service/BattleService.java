package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;

public interface BattleService
{
    void startBattle(Character c, Enemy e);

    void attack();

    Enemy randomEnemy();

    void specialAttack();

    void heal();

    Battle getBattle();
}
