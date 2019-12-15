package fi.tuni.tiko.gamengd.controller.ui;

import fi.tuni.tiko.gamengd.ui.GameLog;

import java.util.ArrayList;

public class UIController {
    private ArrayList<UIListener> uiListeners;
    private GameLog gameLog;
    public UIController() {
        uiListeners = new ArrayList<>();
    }

    public void addUIListener (UIListener listener) {
        uiListeners.add(listener);
    }

    public void addGameLog(GameLog gameLog) {
        this.gameLog = gameLog;
    }

    public void updateGameLog(String message) {
        gameLog.updateGameLog(message);
    }

    public void trigger (String message) {
        for (UIListener uiListener : uiListeners) {
            uiListener.triggerUIListener();
        }
    }
}
