package fi.tuni.tiko.gamengd.controller;

/**
 * TurnInfo is an information class holding info of the current turn.
 *
 * TurnInfo holds info of the current turn and it is passed to each TurnActor
 * as their turn begins.
 * @author Viljami Pietarila
 * @version 2019.1121
 */
public class TurnInfo {
    private int turn;
    private TurnController turnController;

    public TurnInfo(int turn, TurnController turnController) {
        this.turn = turn;
        this.turnController = turnController:
    }

    public int getTurn() {
        return turn;
    }

    public TurnController getTurnController() {
        return turnController;
    }
}