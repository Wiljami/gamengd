package fi.tuni.tiko.gamengd.util.json;

/**
 * MonsterSpawn is json data read to an object.
 *
 * MonsterSpawn contains information for monster spawns within a level. Its
 * data is read from a json file using Jackson.
 *
 * @author Viljami Pietarila
 * @version 2019.1208
 */
public class MonsterSpawn {
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
