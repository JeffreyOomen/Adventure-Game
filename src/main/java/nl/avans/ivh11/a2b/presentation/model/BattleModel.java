package nl.avans.ivh11.a2b.presentation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.List;
import java.util.Map;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Getter
@Setter
@NoArgsConstructor
public class BattleModel {

    private Map<Long, Usable> inventory;
    private Boolean isCharacterAlive;
    private Boolean isEnemyAlive;
    private boolean isHealAttackEnabled;
    private boolean isSpecialAttackEnabled;
    private Stats characterStats;
    private Stats enemyStats;
    private String message;

    public BattleModel(Map<Long, Usable> inventory, Boolean isCharacterAlive, Boolean isEnemyAlive, Boolean isHealAttackEnabled, Boolean isSpecialAttackEnabled,Stats characterStats, Stats enemyStats, String message) {
        this.inventory = inventory;
        this.isCharacterAlive = isCharacterAlive;
        this.isEnemyAlive = isEnemyAlive;
        this.isHealAttackEnabled = isHealAttackEnabled;
        this.isSpecialAttackEnabled = isSpecialAttackEnabled;
        this.characterStats = characterStats;
        this.enemyStats = enemyStats;
        this.message = message;
    }

}
