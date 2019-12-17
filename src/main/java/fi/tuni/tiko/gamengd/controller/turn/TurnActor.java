package fi.tuni.tiko.gamengd.controller.turn;

/**
 * TurnActor interface allows object to be handled by TurnController
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public interface TurnActor {
    /**
     * doTurn method is called whenever it is the turn of this actor to act.
     * @param turnInfo TurnInfo of the current turn.
     */
    void doTurn(TurnInfo turnInfo);
}
