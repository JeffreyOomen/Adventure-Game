package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;

import java.util.List;

public interface OpponentService
{
    Character findCharacterById(long id);

    List<Enemy> findAllEnemies();

    Enemy findEnemyById(long id);
}
