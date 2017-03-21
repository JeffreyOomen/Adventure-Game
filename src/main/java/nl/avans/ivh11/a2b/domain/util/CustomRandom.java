package nl.avans.ivh11.a2b.domain.util;

import java.util.Random;

/**
 * Singleton class which returns a random number based on the attributes and which method is called
 */
public class CustomRandom {
    private double min;
    private double max;
    private static volatile CustomRandom instance = null;
    private static volatile Random r;

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
     * Gives random number of how much damage has been taken
     * @param strength attacking power of attacker
     * @param strengthAccuracy attackingrate of attacker
     * @param defense defensive power of defender
     * @param defenseAccuracy defenserate of defender
     * @return int random number
     */
    public int randomDamage (double strength, double strengthAccuracy, double defense, int defenseAccuracy) {
        double strDouble = r.nextInt(100);
        double defDouble = r.nextInt(100);
        double def = defense * 0.25;
        double str;
        int maxDefense;
        if (defenseAccuracy >= defDouble) {
            maxDefense = (int) (r.nextInt((int)def) + def);
        } else {
            maxDefense = (r.nextInt((int)def));
        }
        if (strength - 1 < maxDefense) {
             str = 1;
        } else {
            str = strength - maxDefense;
        }
        if (strengthAccuracy >= strDouble) {
            min = str * 0.5;
            max = str;
        } else {
            min = 1;
            max = str * 0.5;
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
    public int randomEnemy (double enemies) {
        min = 0;
        max = enemies - 1;
        return getRandomNumber();
    }

    public int randomBetweenZeroAnd(int max) {
        return new Random().nextInt(max);
    }
}
