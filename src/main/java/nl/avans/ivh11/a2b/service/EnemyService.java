package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.domain.enemy.Enemy;

import java.util.List;

public interface EnemyService {

    List<Enemy> findAll();

    Enemy findById(Long id);
}
