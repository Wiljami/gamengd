package fi.tuni.tiko.gamengd.controller.input;

/**
 * CommandTarget interface allows an object become a target of Commands.
 *
 * CommandTargets are affected by key and button inputs of the game.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public interface CommandTarget {
    /**
     * receiveCommand is triggered by the Command.
     * @param message String message from the Command.
     */
    void receiveCommand(String message);
}
