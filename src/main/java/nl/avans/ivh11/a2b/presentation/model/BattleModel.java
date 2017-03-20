package nl.avans.ivh11.a2b.presentation.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.avans.ivh11.a2b.domain.battle.Battle;
import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.enemy.Enemy;
import nl.avans.ivh11.a2b.domain.usable.Usable;
import nl.avans.ivh11.a2b.domain.util.Stats;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Getter
@Setter
@NoArgsConstructor
public class BattleModel {

    private List<Usable> inventory;
    private Boolean isCharacterAlive;
    private Boolean isEnemyAlive;
    private Stats characterStats;
    private Stats enemyStats;
    private String message;

    public BattleModel(List<Usable> inventory, Boolean isCharacterAlive, Boolean isEnemyAlive, Stats characterStats, Stats enemyStats, String message) {
        this.inventory = inventory;
        this.isCharacterAlive = isCharacterAlive;
        this.isEnemyAlive = isEnemyAlive;
        this.characterStats = characterStats;
        this.enemyStats = enemyStats;
        this.message = message;
    }

}
