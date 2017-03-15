package nl.avans.ivh11.a2b.domain.util;

import java.util.Random;

/**
 * Singleton class which returns a random number based on the attributes and which method is called
 */
public class CustomRandom {
    private double min, max;
    private static volatile CustomRandom instance = null;

    /**
     * Check if class is already instantiated and create if not
     * @return instance of class CustomRandom
     */
    public static CustomRandom getInstance() {
        if (instance == null) {
            synchronized (CustomRandom.class) {
                if (instance == null) {
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
        double range = max - min + 1;
        return (int)((Math.random() * range) + min);
    }

    /**
     * Gives random number of how much damage has been taken
     * @param strength attacking power of attacker
     * @param strengthAccuracy attackingrate of attacker
     * @param defense defensive power of defender
     * @param defenseAccuracy defenserate of defender
     * @return int random number
     */
    public int randomDamage (double strength, double strengthAccuracy, double defense, int defenseAccuracy) {
        double strDouble = Math.random() * 100;
        double defDouble = Math.random() * 100;
        defense = defense * 0.25;
        int maxDefense;
        if (defenseAccuracy >= defDouble) {
            maxDefense = (int) (Math.random() * defense + defense);
        } else {
            maxDefense = (int) (Math.random() * defense);
        }
        if (strength - 1 < maxDefense) {
             strength = 1;
        } else {
            strength -= maxDefense;
        }
        if (strengthAccuracy >= strDouble) {
            min = strength * 0.5;
            max = strength;
        } else {
            min = 1;
            max = strength * 0.5;
        }
        return getRandomNumber();
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
    public int randomEnemy (int enemies) {
        min = 0;
        max = enemies;
        return getRandomNumber();
    }

    public int randomBetweenZeroAnd(int max) {
        return new Random().nextInt(max);
    }
}
