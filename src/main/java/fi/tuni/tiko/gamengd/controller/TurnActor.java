package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.TurnInfo;

public interface TurnActor {
    public void doTurn(TurnInfo turnInfo);
}