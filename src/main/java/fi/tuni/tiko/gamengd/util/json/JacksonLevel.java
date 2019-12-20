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
        private int conX;
        private int conY;
        private String connection;
        public StairData() {}

        /**
         * Getter for up
         *
         * @return value of up
         */
        public boolean isUp() {
            return up;
        }

        /**
         * Sets up
         *
         * @param up new value
         */
        public void setUp(boolean up) {
            this.up = up;
        }

        /**
         * Getter for x
         *
         * @return value of x
         */
        public int getX() {
            return x;
        }

        /**
         * Sets x
         *
         * @param x new value
         */
        public void setX(int x) {
            this.x = x;
        }

        /**
         * Getter for y
         *
         * @return value of y
         */
        public int getY() {
            return y;
        }

        /**
         * Sets y
         *
         * @param y new value
         */
        public void setY(int y) {
            this.y = y;
        }

        /**
         * Getter for conX
         *
         * @return value of conX
         */
        public int getConX() {
            return conX;
        }

        /**
         * Sets conX
         *
         * @param conX new value
         */
        public void setConX(int conX) {
            this.conX = conX;
        }

        /**
         * Getter for conY
         *
         * @return value of conY
         */
        public int getConY() {
            return conY;
        }

        /**
         * Sets conY
         *
         * @param conY new value
         */
        public void setConY(int conY) {
            this.conY = conY;
        }

        /**
         * Getter for connection
         *
         * @return value of connection
         */
        public String getConnection() {
            return connection;
        }

        /**
         * Sets connection
         *
         * @param connection new value
         */
        public void setConnection(String connection) {
            this.connection = connection;
        }
    }

    /**
     * MonsterSpawn is json data read to an object.
     *
     * MonsterSpawn contains information for monster spawns within a level. Its
     * data is read from a json file using Jackson.
     *
     * @author Viljami Pietarila
     * @version 2019.1215
     */
    public static class MonsterSpawn {
        /**
         * Type of the monster.
         */
        private String type;
        /**
         * x-coordinate of the spawn point.
         */
        private int spawnPointX;
        /**
         * y-coordinate of the spawn point.
         */
        private int spawnPointY;

        /**
         * Constructor for MonsterSpawn.
         */
        public MonsterSpawn() {
        }

        /**
         * getter for type.
         * @return type
         */
        public String getType() {
            return type;
        }

        /**
         * setter for type.
         * @param type new type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * getter for spawnPointX.
         * @return spawnPointX
         */
        public int getSpawnPointX() {
            return spawnPointX;
        }

        /**
         * setter for spawnPointX.
         * @param spawnPointX new spawnPointX
         */
        public void setSpawnPointX(int spawnPointX) {
            this.spawnPointX = spawnPointX;
        }
        /**
         * getter for spawnPointY.
         * @return spawnPointY
         */
        public int getSpawnPointY() {
            return spawnPointY;
        }
        /**
         * setter for spawnPointY.
         * @param spawnPointY new spawnPointY
         */
        public void setSpawnPointY(int spawnPointY) {
            this.spawnPointY = spawnPointY;
        }
    }
}
