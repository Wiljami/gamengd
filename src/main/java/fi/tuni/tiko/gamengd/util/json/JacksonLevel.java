package fi.tuni.tiko.gamengd.util.json;

import java.util.ArrayList;

public class JacksonLevel {
    private String map;
    private String id;

    private ArrayList<MonsterSpawn> monsterSpawns;
    private ArrayList<StairData> stairs;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<StairData> getStairs() {
        return stairs;
    }

    public void setStairs(ArrayList<StairData> stairs) {
        this.stairs = stairs;
    }

    public static class StairData {
        private boolean up;
        private int x;
        private int y;
        private String connection;
        public StairData() {}

        public boolean isUp() {
            return up;
        }

        public void setUp(boolean up) {
            this.up = up;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public String getConnection() {
            return connection;
        }

        public void setConnection(String connection) {
            this.connection = connection;
        }
    }
}
