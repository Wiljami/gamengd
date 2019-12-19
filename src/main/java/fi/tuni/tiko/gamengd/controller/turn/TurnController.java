package fi.tuni.tiko.gamengd.controller.turn;

import fi.tuni.tiko.gamengd.controller.crisis.CrisisController;
import java.util.ArrayList;

/**
 * TurnController object controls each TurnActors turn within the game.
 *
 * TurnController is responsible for the flow of the game by handing out
 * permissions and turns to different Units and CrisisController to act.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class TurnController {
    /**
     * turn counter.
     */
    private int turn;
    /**
     * Reference to the crisisController.
     */
    private CrisisController crisisController;

    /**
     * A list of turnActors who will receive a turn.
     */
    private ArrayList<TurnActor> turnActors;

    /**
     * A list of turnActors who have yet to act this turn.
     */
    private ArrayList<TurnActor> currentTurnActors;

    /**
     * List of TurnListeners who need to hear of the turn flow.
     */
    private ArrayList<TurnListener> turnListeners;

    /**
     * TurnController constructor.
     * @param crisisController reference to the CrisisController
     */
    public TurnController(CrisisController crisisController) {
        this.crisisController = crisisController;
        turnActors = new ArrayList<>();
        currentTurnActors = new ArrayList<>();
        turnListeners = new ArrayList<>();
    }

    /**
     * addTurn adds a turnActor to the TurnController.
     * @param turnActor TurnActor to be added.
     */
    public void addTurn(TurnActor turnActor) {
        turnActors.add(turnActor);
    }

    /**
     * removeTurnActor removes a TurnActor from the Controller.
     *
     * It removes the TurnActor from both the current and the turnActors list.
     * @param turnActor turnActor to be removed.
     * @return boolean wether the operation was succesful.
     */
    public boolean removeTurn(TurnActor turnActor) {
        currentTurnActors.remove(turnActor);
        return turnActors.remove(turnActor);
    }

    /**
     * doTurn tells an element to do their turn.
     *
     * If the currentTurnActors is empty a newTurn is created.
     */
    public void doTurn() {
        if (currentTurnActors.size() == 0) {
            newTurn();
        } else {
            TurnInfo turnInfo = new TurnInfo(getTurn(), this);
            for(TurnListener turnListener : turnListeners) {
                turnListener.inform(turnInfo);
            }
            currentTurnActors.remove(0).doTurn(turnInfo);
        }
    }

    /**
     * finishedTurn is called by TurnActors once they finish their turn.
     */
    public void finishedTurn() {
        doTurn();
    }

    /**
     * newTurn is called when turn changes.
     *
     * When currentTurnActors is empty a new turn is created.
     */
    @SuppressWarnings("unchecked")
    private void newTurn() {
        crisisController.run(turn);
        turn++;
        currentTurnActors = (ArrayList<TurnActor>)turnActors.clone();
        doTurn();
    }

    /**
     * Register a turnListener to the List
     * @param turnListener TurnListener to be added
     */
    public void registerTurnListener(TurnListener turnListener) {
        turnListeners.add(turnListener);
    }

    /**
     * Unregister a TurnListener.
     * @param turnListener the TurnListener to be unregistered.
     * @return wether the operation was succesful
     */
    public boolean unRegisterTurnListener(TurnListener turnListener) {
        return turnListeners.remove(turnListener);
    }

    /**
     * setter for turn.
     * @param turn new turn value
     */
    public void setTurn(int turn) {
        this.turn = turn;
    }

    /**
     * getter for turn.
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    public void clear() {
        currentTurnActors.clear();
        turnActors.clear();
    }
}
