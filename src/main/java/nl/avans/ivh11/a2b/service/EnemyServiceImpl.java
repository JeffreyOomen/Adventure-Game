package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by isazu on 31-3-2017.
 */
public class EnemyServiceImpl implements EnemyService {
    private EnemyRepository enemyRepository;

    public EnemyServiceImpl(EnemyRepository enemyRepository) {
        this.enemyRepository = enemyRepository;
    }

    /**
     * Find all enemies
     * @return a List of Enemy's
     */
    @Transactional
    @Override
    public List<Enemy> findAllEnemies() {
        return (List<Enemy>) enemyRepository.findAll();
    }

    /**
     * Gets the Enemy by id
     * @param id the id of the Enemy to be found
     * @return the Enemy with the specified id
     */
    @Transactional(readOnly = true)
    @Override
    public Enemy findEnemyById(long id) {
        return enemyRepository.findOne(id);
    }
}
