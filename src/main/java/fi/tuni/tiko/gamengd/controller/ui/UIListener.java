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
     * UILIst
     */
    void triggerUIListener(Player source);
}
