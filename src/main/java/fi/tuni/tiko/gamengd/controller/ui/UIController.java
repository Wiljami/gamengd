package fi.tuni.tiko.gamengd.controller.ui;

import fi.tuni.tiko.gamengd.entity.Player;
import fi.tuni.tiko.gamengd.ui.GameLog;

import java.util.ArrayList;

/**
 * UIController controls flow of info from game to the UI elements.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class UIController {
    /**
     * List of uiListeners.
     */
    private ArrayList<UIListener> uiListeners;

    /**
     * Reference to the gameLog.
     */
    private GameLog gameLog;

    /**
     * UIController constructor.
     */
    public UIController() {
        uiListeners = new ArrayList<>();
    }

    /**
     * addUIlistener adds an UI listener to the controller.
     * @param listener UIListener to be added.
     */
    public void addUIListener (UIListener listener) {
        uiListeners.add(listener);
    }

    /**
     * addGameLog creates a reference to the gameLog.
     * @param gameLog GameLog of the game.
     */
    public void addGameLog(GameLog gameLog) {
        this.gameLog = gameLog;
    }

    /**
     * updateGameLog lets the gameLog know of a new message to display.
     * @param message message to display.
     */
    public void updateGameLog(String message) {
        gameLog.updateGameLog(message);
    }

    /**
     * trigger goes through the uiListeners and sends given message to each.
     * @param source reference to the Player source.
     */
    public void trigger (Player source) {
        for (UIListener uiListener : uiListeners) {
            uiListener.triggerUIListener(source);
        }
    }
}
