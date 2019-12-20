package fi.tuni.tiko.gamengd.util.json;

import java.util.ArrayList;
/**
 * JacksonLevel is an object created from a JSONFile using Jackson.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class JacksonLevel {
    /**
     * fileName of the mapFile for this level.
     */
    private String map;
    /**
     * id of this level.
     */
    private String id;
    /**
     * ArrayList of Monsters to spawn to this level.
     */
    private ArrayList<MonsterSpawn> monsterSpawns;
    /**
     * ArrayList of Stairs within this level.
     */
    private ArrayList<StairData> stairs;

    /**
     * JacksonLevel constructor.
     */
    public JacksonLevel() {
    }

    /**
     * Getter for map
     *
     * @return value of map
     */
    public String getMap() {
        return map;
    }

    /**
     * Sets map
     *
     * @param map new value
     */
    public void setMap(String map) {
        this.map = map;
    }

    /**
     * Getter for id
     *
     * @return value of id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id
     *
     * @param id new value
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for monsterSpawns
     *
     * @return value of monsterSpawns
     */
    public ArrayList<MonsterSpawn> getMonsterSpawns() {
        return monsterSpawns;
    }

    /**
     * Sets monsterSpawns
     *
     * @param monsterSpawns new value
     */
    public void setMonsterSpawns(ArrayList<MonsterSpawn> monsterSpawns) {
        this.monsterSpawns = monsterSpawns;
    }

    /**
     * Getter for stairs
     *
     * @return value of stairs
     */
    public ArrayList<StairData> getStairs() {
        return stairs;
    }

    /**
     * Sets stairs
     *
     * @param stairs new value
     */
    public void setStairs(ArrayList<StairData> stairs) {
        this.stairs = stairs;
    }

    /**
     * StairData is an object holding data for Stair elements.
     *
     * @author Viljami Pietarila
     * @version 2019.1220
     */
    public static class StairData {
        /**
         * boolean wether the stair Sprite should point up or down.
         */
        private boolean up;
        /**
         * x-coordinate of the stair.
         */
        private int x;
        /**
         * y-coordinate of the stair.
         */
        private int y;
        /**
         * destination x-coordinate of the stair.
         */
        private int conX;
        /**
         * destination y-coordinate of the stair.
         */
        private int conY;
        /**
         * destination level id.
         */
        private String connection;

        /**
         * StairData constructor.
         */
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
