package fi.tuni.tiko.gamengd.controller.ui;

import fi.tuni.tiko.gamengd.entity.Player;

import java.util.ArrayList;

public class UIController {
    private ArrayList<UIListener> uiListeners;
    public UIController() {
        uiListeners = new ArrayList<>();
    }

    public void addUIListener (UIListener listener) {
        uiListeners.add(listener);
    }

    public void trigger (Player player) {
        for (UIListener uiListener : uiListeners) {
            uiListener.receivePlayerData(player);
        }
    }
}
