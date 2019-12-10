package fi.tuni.tiko.gamengd.util;

/**
 * GameMechanic class contains misc methods that are used by the game engine.
 *
 * The methods contained in GameMechanic are potentially used in multiple
 * places of the game engine.
 *
 * @author Viljami Pietarila
 * @version 2019.1210
 */
public class GameMechanic {
    /**
     * randomRoll method generates and returns a random int value.
     *
     * randomRoll makes a 2d6 open ended dice roll and returns its sum. Open
     * ended in this case means that if you roll a 6 from the dice, you roll it
     * again and add its value to the sum. You keep rolling until no 6 is rolled.
     *
     * @return randomized int value.
     */
    public static int randomRoll() {
        int sum = 0;
        for (int rolls = 2; rolls > 0; rolls--) {
            int roll = 0;
            do {
                roll = (int) (Math.random() * 6 + 1);
                sum += roll;
            } while (roll == 6);
        }
        return sum;
    }
}
