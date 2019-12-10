package fi.tuni.tiko.gamengd.util.json;

import java.util.ArrayList;

public class JacksonLevel {
    private String map;
    private int playerSpawnX;
    private int playerSpawnY;
    private int playerHitPoints;
    private int playerAttack;
    private int playerDefense;
    private ArrayList<MonsterSpawn> monsterSpawns;

    public JacksonLevel() {
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getPlayerSpawnX() {
        return playerSpawnX;
    }

    public void setPlayerSpawnX(int playerSpawnX) {
        this.playerSpawnX = playerSpawnX;
    }

    public int getPlayerSpawnY() {
        return playerSpawnY;
    }

    public void setPlayerSpawnY(int playerSpawnY) {
        this.playerSpawnY = playerSpawnY;
    }

    public int getPlayerHitPoints() {
        return playerHitPoints;
    }

    public void setPlayerHitPoints(int playerHitPoints) {
        this.playerHitPoints = playerHitPoints;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    public int getPlayerDefense() {
        return playerDefense;
    }

    public void setPlayerDefense(int playerDefense) {
        this.playerDefense = playerDefense;
    }

    public ArrayList<MonsterSpawn> getMonsterSpawns() {
        return monsterSpawns;
    }

    public void setMonsterSpawns(ArrayList<MonsterSpawn> monsterSpawns) {
        this.monsterSpawns = monsterSpawns;
    }
}

