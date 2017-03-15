package nl.avans.ivh11.a2b.presentation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.util.Stats;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Getter
@Setter
public class BattleModel {
    private Stats characterStats;
    private Stats enemyStats;

    public BattleModel(Stats characterStats, Stats enemyStats) {
        this.characterStats = characterStats;
        this.enemyStats = enemyStats;
    }

}
