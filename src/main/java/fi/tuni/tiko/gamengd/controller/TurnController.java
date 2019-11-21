package fi.tuni.tiko.gamengd.controller;

import java.util.ArrayList;

public class TurnController {
    private int turn;

    private ArrayList<TurnActor> turnActors;
    private ArrayList<TurnActor> currentTurnActors;

    public TurnController() {
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

    private void newTurn() {
        turn++;
        currentTurnActors = (ArrayList<TurnActor>)turnActors.clone();
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}