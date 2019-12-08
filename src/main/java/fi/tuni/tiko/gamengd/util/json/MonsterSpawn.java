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
    private String type;
    private int spawnPointX;
    private int spawnPointY;
    public MonsterSpawn() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpawnPointX() {
        return spawnPointX;
    }

    public void setSpawnPointX(int spawnPointX) {
        this.spawnPointX = spawnPointX;
    }

    public int getSpawnPointY() {
        return spawnPointY;
    }

    public void setSpawnPointY(int spawnPointY) {
        this.spawnPointY = spawnPointY;
    }
}
