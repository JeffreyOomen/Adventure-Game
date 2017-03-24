package nl.avans.ivh11.a2b.domain.util;

import java.util.Map;
import java.util.Random;

/**
 * Singleton class which returns a random number based on the attributes and which method is called
 */
public class CustomRandom {
    private double min;
    private double max;
    private static volatile CustomRandom instance = null;
    private static volatile Random r;

    // Private constructor to prevent direct instantiation
    private CustomRandom() {}

    /**
     * Check if class is already instantiated and create if not
     * @return instance of class CustomRandom
     */
    public static CustomRandom getInstance() {
        if (instance == null) {
            synchronized (CustomRandom.class) {
                if (instance == null) {
                    r = new Random();
                    instance = new CustomRandom();
                }
            }
        }
        return instance;
    }

    /**
     * Create random number based on the min and max set in the random methods
     * @return int random number
     */
    public int getRandomNumber() {
        int range = (int) (max - min + 1);
        return (int)(r.nextInt(range) + min);
    }

    /**
     * Generates damage done by the attack on the defender based on certain
     * @param attackingLevel e.g. strength, magic, or archery level of the attacker
     * @param attackingAccuracy e.g. strength, magic or archery accuracy of the attacker
     * @param defenseLevel defense level of the defender
     * @param defenseAccuracy defense accuracy of the defender
     * @return an integer which represents the actual damage done by the attacker on the defender
     */
    public int randomOpponentDamage(int attackingLevel, int attackingAccuracy, int defenseLevel, int defenseAccuracy) {
        // generate a random number between 0 - 100
        int randomNumber = r.nextInt(100);

        // Attacker damage done
        int randomAttackingDamage = this.randomAttackingDamage(attackingLevel, attackingAccuracy, randomNumber);
        // Defender damage defended
        int randomDefendingDamage = this.randomDefendingDamage(defenseLevel, defenseAccuracy, randomNumber);

        // From the 50 attacking damage, 20 is being defended, therefore resulting in 30 actual damage
        int actualDamage = randomAttackingDamage - randomDefendingDamage;
        return actualDamage < 0 ? 0 : actualDamage;
    }

    /**
     * Generates a random integer representing the damage done by the attacker.
     * This works based on the attacking level (determines the max damage which can be done)
     * and with probabilities based on the attacking accuracy (the higher the accuracy, the
     * higher the chances are that the damage done will be closer to the max damage).
     * @param attackingLevel e.g. strength, magic, or archery level of the attacker
     * @param attackingAccuracy e.g. strength, magic or archery accuracy of the attacker
     * @param randomNumber say for example the attacking level is 10, and the accuracy is 60%.
     * The max damage which can be done is (10 * 1.5) = 15. There is a 60% chance that the damage
     * will be between 0 - 8 and 40% chance it will be 8 - 15. The randomNumber gives a random
     * number with which can be determined if the 60% chance will be applied or the 40% chance.
     * @return a randomly generated number which represents the attacking damage done based on level and accuracy
     */
    private int randomAttackingDamage(int attackingLevel, int attackingAccuracy, int randomNumber) {
        int maxAttackingDamage = (int) Math.ceil(attackingLevel * 1.5);
        int midAttackingDamage = (int) Math.ceil(maxAttackingDamage / 2);

        if (randomNumber < ((attackingAccuracy / 100) * attackingLevel)) {
            return r.nextInt((maxAttackingDamage - midAttackingDamage) + 1) + midAttackingDamage;
        } else {
            return r.nextInt(midAttackingDamage);
        }
    }

    /**
     * Generates a random integer representing the damage done by the attacker.
     * This works based on the attacking level (determines the max damage which can be done)
     * and with probabilities based on the attacking accuracy (the higher the accuracy, the
     * higher the chances are that the damage done will be closer to the max damage).
     * @param defenseLevel defense level of the defender
     * @param defenseAccuracy defense accuracy of the defender
     * @param randomNumber say for example the defense level is 10, and the accuracy is 60%.
     * The max damage which can be defended is (10 * 1.2) = 12. There is a 60% chance that the damage defended
     * will be between 0 - 6 and 40% chance it will be 6 - 12. The randomNumber gives a random
     * number with which can be determined if the 60% chance will be applied or the 40% chance.
     * @return a randomly generated number which represents the damage being defended based on level and accuracy
     */
    private int randomDefendingDamage(int defenseLevel, int defenseAccuracy, int randomNumber) {
        int maxDefendingDamage = (int) Math.ceil(defenseLevel * 1.5);
        int midDefendingDamage = (int) Math.ceil(maxDefendingDamage / 2);

        if (randomNumber < ((defenseAccuracy / 100) * defenseLevel)) {
            return r.nextInt((maxDefendingDamage - midDefendingDamage) + 1) + midDefendingDamage;
        } else {
            return r.nextInt(midDefendingDamage);
        }
    }

    /**
     * Gives an Opponent which has the role of Enemy certain Stats
     * based on the Stats of the Opponent which has the role of Character.
     * @param character an Object of Character
     * @return the Enemy Object with random stats based on the Character Stats
     */
    public Stats randomEnemyStats(Opponent character) {
        int randomNumber = r.nextInt((110 - 50) + 1) + 50;
        Map<String, Integer> attackStyleStats = character.getAttackStyleStats();

        // add more hp for fun
        int hpMultiplier = 1;
        if (randomNumber < 60) {
            hpMultiplier = 30;
        }

        Stats enemyStats = new Stats();
        enemyStats.setHitpoints((int)((randomNumber / 100.0) * character.getHitpoints() * hpMultiplier));
        enemyStats.setCurrentHitpoints((int)((randomNumber / 100.0) * character.getHitpoints() * hpMultiplier));
        enemyStats.setStrength((int)((randomNumber / 100.0) * attackStyleStats.get("AttackStyleLevel")));
        enemyStats.setStrengthAccuracy((int)((randomNumber / 100.0) * attackStyleStats.get("AttackStyleAccuracy")));
        enemyStats.setDefense((int)((randomNumber / 100.0) * character.getStats().getDefense()));
        enemyStats.setDefenseAccuracy((int)((randomNumber / 100.0) * character.getStats().getDefenseAccuracy()));

        return enemyStats;
    }

    /**
     * Gives random number based on the player's level
     * @param level player's level
     * @return int random number
     */
    public int randomLevel (int level) {
        min = level * 0.75;
        max = level * 1.15;
        return getRandomNumber();
    }

    /**
     * Gives random number based on the number of possible enemies
     * @param enemies length of list with enemies
     * @return int random number
     */
    public int randomEnemy (double enemies) {
        min = 0;
        max = enemies - 1;
        return getRandomNumber();
    }

    public int randomBetweenZeroAnd(int max) {
        return new Random().nextInt(max);
    }
}
