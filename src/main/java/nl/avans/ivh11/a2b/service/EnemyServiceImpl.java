package nl.avans.ivh11.a2b.service;

import nl.avans.ivh11.a2b.datastorage.enemy.EnemyRepository;
import nl.avans.ivh11.a2b.datastorage.usable.MediaRepository;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("EnemyService")
@Repository
public class EnemyServiceImpl implements EnemyService {

    @Autowired
    private EnemyRepository enemyRepository;

    /**
     * Find all enemies
     * @return a List of Enemy's
     */
    @Transactional(readOnly = true)
    @Override
    public List<Enemy> findAll() {
        return (List<Enemy>) enemyRepository.findAll();
    }

    /**
     * Find all enemies
     * @return a List of Enemy's
     */
    @Transactional
    @Override
    public Enemy findById(Long id) {
        return enemyRepository.findOne(id);
    }
}
