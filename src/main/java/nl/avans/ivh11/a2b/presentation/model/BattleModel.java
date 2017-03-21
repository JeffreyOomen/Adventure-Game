package nl.avans.ivh11.a2b.presentation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.ArrayList;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Getter
@Setter
@NoArgsConstructor
public class BattleModel {

    private Boolean isCharacterAlive;
    private Boolean isEnemyAlive;
    private Stats characterStats;
    private Stats enemyStats;
    private String message;

    public BattleModel(Boolean isCharacterAlive, Boolean isEnemyAlive, Stats characterStats, Stats enemyStats, String message) {
        this.isCharacterAlive = isCharacterAlive;
        this.isEnemyAlive = isEnemyAlive;
        this.characterStats = characterStats;
        this.enemyStats = enemyStats;
        this.message = message;
    }

}
