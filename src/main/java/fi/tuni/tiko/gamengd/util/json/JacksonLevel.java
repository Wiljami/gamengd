package fi.tuni.tiko.gamengd.util.json;

import java.util.ArrayList;

public class JacksonLevel {
    private String map;
    private int level;
    private String id;

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
