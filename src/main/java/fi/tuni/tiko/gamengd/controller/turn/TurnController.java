package fi.tuni.tiko.gamengd.controller.turn;

import fi.tuni.tiko.gamengd.controller.crisis.CrisisController;

import java.util.ArrayList;

public class TurnController {
    private int turn;
    private CrisisController crisisController;


    private ArrayList<TurnActor> turnActors;
    private ArrayList<TurnActor> currentTurnActors;

    public TurnController(CrisisController crisisController) {
        this.crisisController = crisisController;
        setTurn(0);
        turnActors = new ArrayList<>();
        currentTurnActors = new ArrayList<>();
    }

    public void addTurn(TurnActor turnActor) {
        turnActors.add(turnActor);
    }

    public boolean removeTurn(TurnActor turnActor) {
        currentTurnActors.remove(turnActor);
        return turnActors.remove(turnActor);
    }

    public void doTurn() {
        if (currentTurnActors.size() == 0) {
            newTurn();
        } else {
            TurnInfo turnInfo = new TurnInfo(getTurn(), this);
            currentTurnActors.remove(0).doTurn(turnInfo);
        }
    }

    public void finishedTurn() {
        doTurn();
    }

    private void newTurn() {
        crisisController.run(turn);
        turn++;
        currentTurnActors = (ArrayList<TurnActor>)turnActors.clone();
        doTurn();
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}