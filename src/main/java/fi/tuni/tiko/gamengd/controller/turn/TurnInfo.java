package fi.tuni.tiko.gamengd.controller.turn;

/**
 * TurnInfo is an information class holding info of the current turn.
 *
 * TurnInfo holds info of the current turn and it is passed to each TurnActor
 * as their turn begins.
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class TurnInfo {
    /**
     * Current turn number
     */
    private int turn;

    /**
     * Reference to turnController.
     */
    private TurnController turnController;

    /**
     * TurnInfo constructor.
     * @param turn currentTurn
     * @param turnController reference to the turnController
     */
    public TurnInfo(int turn, TurnController turnController) {
        this.turn = turn;
        this.turnController = turnController;
    }

    /**
     * getter for turn.
     * @return turn value
     */
    public int getTurn() {
        return turn;
    }

    /**
     * getter for the turnController
     * @return turnController
     */
    public TurnController getTurnController() {
        return turnController;
    }
}
