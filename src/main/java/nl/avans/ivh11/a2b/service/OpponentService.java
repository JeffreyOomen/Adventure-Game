package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;

public interface OpponentService
{
    Character findCharacterById(long id);

    Enemy findEnemyById(long id);
}
