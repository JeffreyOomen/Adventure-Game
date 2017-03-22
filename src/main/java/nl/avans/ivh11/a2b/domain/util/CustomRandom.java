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
        System.out.println("RANDOM NUMMER STILL GOES GOED " + range + " " + range + min);
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

        double def = defense;
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
        System.out.println("Attacking level: " + attackingLevel);
        System.out.println("Attacking accuracy: " + attackingAccuracy);
        System.out.println("Random number: " + randomNumber);
        int maxAttackingDamage = (int) Math.ceil(attackingLevel * 1.5);
        System.out.println("Max damage: " + maxAttackingDamage);
        int midAttackingDamage = (int) Math.ceil(maxAttackingDamage / 2);
        System.out.println("Mid damage: " + midAttackingDamage);

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
        int maxDefendingDamage = (int) Math.ceil(defenseLevel * 1.2);
        int midDefendingDamage = (int) Math.ceil(maxDefendingDamage / 2);

        if (randomNumber < ((defenseAccuracy / 100) * defenseLevel)) {
            return r.nextInt((maxDefendingDamage - midDefendingDamage) + 1) + midDefendingDamage;
        } else {
            return r.nextInt(midDefendingDamage);
        }
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
