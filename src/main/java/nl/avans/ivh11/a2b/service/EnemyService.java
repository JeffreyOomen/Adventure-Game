package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.enemy.Enemy;

import java.util.List;

/**
 * Created by isazu on 31-3-2017.
 */
public interface EnemyService {

    List<Enemy> findAll();

    Enemy findById(long id);
}
