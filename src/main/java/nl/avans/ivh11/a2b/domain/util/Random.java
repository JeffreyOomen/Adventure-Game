package nl.avans.ivh11.a2b.domain.util;

/**
 * Created by isazu on 6-3-2017.
 */
public class Random {
    private double min, max;
    private static volatile Random instance = null;

    private Random() {}

    public static Random getInstance() {
        if (instance == null) {
            synchronized (Random.class) {
                if (instance == null) {
                    instance = new Random();
                }
            }
        }
        return instance;
    }

    public int getRandom() {
        double range = max - min + 1;
        int randomNumber = (int)((Math.random() * range) + min);
        return randomNumber;
    }

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
        int rnd = getRandom();
        return rnd;
    }

    public int randomLevel (int level) {
        min = level * 0.75;
        max = level * 1.15;
        int rnd = getRandom();
        return rnd;
    }

    public int randomEnemy (int enemies) {
        min = 0;
        max = enemies;
        int rnd = getRandom();
        return rnd;
    }
}
