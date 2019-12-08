package fi.tuni.tiko.gamengd.controller.crisis;

import java.util.ArrayList;

public class CrisisController {
    private ArrayList<Crisis> crisisList;

    public CrisisController() {
        crisisList = new ArrayList<>();
    }

    public void run(int turn) {
        for (Crisis c : crisisList) {
            double random = Math.random();
            if (random < c.getChance()) {
                c.trigger(turn);
            }
        }
    }

    public void addCrisis(Crisis crisis) {
        crisisList.add(crisis);
    }

    public boolean removeCrisis(Crisis crisis) {
        return crisisList.remove(crisis);
    }
}