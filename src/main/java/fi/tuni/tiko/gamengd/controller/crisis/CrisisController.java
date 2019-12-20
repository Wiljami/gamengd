package fi.tuni.tiko.gamengd.controller.crisis;

import java.util.ArrayList;

/**
 * CrisisController is the object responsible for controlling crisises.
 *
 * CrisisController, named as such because EventController might be confused
 * with some java's other events is a central controlling object for all the
 * crisis within the game. It is given the crisis from different sources, such
 * as levels. It runs through them each turn and triggers them depending on the
 * chance given in the crisis.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class CrisisController {
    /**
     * List of registered crisises
     */
    private ArrayList<Crisis> crisisList;

    /**
     * CrisisController constructor
     */
    public CrisisController() {
        crisisList = new ArrayList<>();
    }

    /**
     * run method runs through the entire list of crisis to trigger them.
     *
     * Iterates through the entire list of crisises and triggers them depending
     * on their chance. Also hands them the current turn number for.
     * @param turn current Turn number.
     */
    public void run(int turn) {
        for (Crisis c : crisisList) {
            double random = Math.random();
            if (random < c.getChance()) {
                c.trigger(turn);
            }
        }
    }

    /**
     * addCrisis adds a crisis to the list.
     * @param crisis Crisis to be added.
     */
    public void addCrisis(Crisis crisis) {
        crisisList.add(crisis);
    }

    /**
     * removeCrisis removes a Crisis from the list
     * @param crisis Crisis to be removed
     * @return boolean wether the operation was succesful.
     */
    public boolean removeCrisis(Crisis crisis) {
        return crisisList.remove(crisis);
    }

    /**
     * clear empties the crisisList.
     */
    public void clear() {
        crisisList.clear();
    }
}