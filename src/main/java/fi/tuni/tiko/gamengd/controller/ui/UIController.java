package fi.tuni.tiko.gamengd.controller.ui;

import java.util.ArrayList;

public class UIController {
    private ArrayList<UIListener> uiListeners;
    public UIController() {
        uiListeners = new ArrayList<>();
    }

    public void addUIListener (UIListener listener) {
        uiListeners.add(listener);
    }

    public void trigger () {
        for (UIListener uiListener : uiListeners) {
            uiListener.triggerUIListener();
        }
    }
}
