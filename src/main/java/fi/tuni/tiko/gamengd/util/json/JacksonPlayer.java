package fi.tuni.tiko.gamengd.util.json;

/**
 * JacksonPlayer is an object created from a JSONFile using Jackson.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class JacksonPlayer {
    /**
     * graphicFile for the player Sprite.
     */
    private String graphicFile;
    /**
     * starting x-coordinate.
     */
    private int spawnX;
    /**
     * starting y-coordinate.
     */
    private int spawnY;
    /**
     * player hitPoints.
     */
    private int hitPoints;
    /**
     * player maxHitPoints.
     */
    private int maxHitPoints;
    /**
     * player attack.
     */
    private int attack;
    /**
     * player defense.
     */
    private int defense;
    /**
     * player kills.
     */
    private int kills;
    /**
     * player name.
     */
    private String name;
    /**
     * starting level for the player.
     */
    private String levelId;

    /**
     * JacksonPlayer constructor.
     */
    public JacksonPlayer() {
    }

    /**
     * Getter for graphicFile
     *
     * @return value of graphicFile
     */
    public String getGraphicFile() {
        return graphicFile;
    }

    /**
     * Sets graphicFile
     *
     * @param graphicFile new value
     */
    public void setGraphicFile(String graphicFile) {
        this.graphicFile = graphicFile;
    }

    /**
     * Getter for spawnX
     *
     * @return value of spawnX
     */
    public int getSpawnX() {
        return spawnX;
    }

    /**
     * Sets spawnX
     *
     * @param spawnX new value
     */
    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    /**
     * Getter for spawnY
     *
     * @return value of spawnY
     */
    public int getSpawnY() {
        return spawnY;
    }

    /**
     * Sets spawnY
     *
     * @param spawnY new value
     */
    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    /**
     * Getter for hitPoints
     *
     * @return value of hitPoints
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Sets hitPoints
     *
     * @param hitPoints new value
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Getter for maxHitPoints
     *
     * @return value of maxHitPoints
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * Sets maxHitPoints
     *
     * @param maxHitPoints new value
     */
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    /**
     * Getter for attack
     *
     * @return value of attack
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Sets attack
     *
     * @param attack new value
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Getter for defense
     *
     * @return value of defense
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets defense
     *
     * @param defense new value
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Getter for kills
     *
     * @return value of kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * Sets kills
     *
     * @param kills new value
     */
    public void setKills(int kills) {
        this.kills = kills;
    }

    /**
     * Getter for name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name
     *
     * @param name new value
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for levelId
     *
     * @return value of levelId
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * Sets levelId
     *
     * @param levelId new value
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
