package fi.tuni.tiko.gamengd.controller;

import java.util.ArrayList;

public class CrisisController {
    private ArrayList<Crisis> crisisList;

    public CrisisController() {
        crisisList = new ArrayList<>();
    }

    public void run(int turn) {
        for (Crisis c : crisisList) {
            double random = Math.random();
            System.out.println(c.getChance());
            if (random < c.getChance()) {
                c.trigger();
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