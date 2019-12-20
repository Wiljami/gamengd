package fi.tuni.tiko.gamengd.controller.ui;

import fi.tuni.tiko.gamengd.entity.Player;

/**
 * UIListener interface lets object listen to changes to ui.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public interface UIListener {
    /**
     * triggerUIListener is the method called when listener is triggered.
     * @param source source Player
     */
    void triggerUIListener(Player source);
}
