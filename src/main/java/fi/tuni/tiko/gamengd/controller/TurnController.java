package fi.tuni.tiko.gamengd.controller;

import java.util.ArrayList;

public class TurnController {
    private int turn;

    private ArrayList<TurnActor> turnActors;

    public TurnController() {
        setTurn(1);
        turnActors = new ArrayList<>();
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }
}