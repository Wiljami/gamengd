package fi.tuni.tiko.gamengd.controller.turn;

/**
 * TurnListener interface lets an element listen to the flow of the turns.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public interface TurnListener {
    /**
     * inform the TurnListener with TurnInfo.
     * @param turnInfo info of the current turn.
     */
    void inform(TurnInfo turnInfo);
}
