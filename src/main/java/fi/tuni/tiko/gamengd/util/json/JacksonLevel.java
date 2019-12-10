package fi.tuni.tiko.gamengd.util.json;

import java.util.ArrayList;

public class JacksonLevel {
    private String map;

    private JacksonPlayer player;

    private ArrayList<MonsterSpawn> monsterSpawns;

    public JacksonLevel() {
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public ArrayList<MonsterSpawn> getMonsterSpawns() {
        return monsterSpawns;
    }

    public void setMonsterSpawns(ArrayList<MonsterSpawn> monsterSpawns) {
        this.monsterSpawns = monsterSpawns;
    }

    public JacksonPlayer getPlayer() {
        return player;
    }

    public void setPlayer(JacksonPlayer player) {
        this.player = player;
    }
}

