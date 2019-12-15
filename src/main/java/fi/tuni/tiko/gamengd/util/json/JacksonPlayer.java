package fi.tuni.tiko.gamengd.util.json;

public class JacksonPlayer {
    private String playerGraphicFile;
    private int playerSpawnX;
    private int playerSpawnY;
    private int playerHitPoints;
    private int playerAttack;
    private int playerDefense;
    private String levelId;

    public JacksonPlayer() {
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

    public String getPlayerGraphicFile() {
        return playerGraphicFile;
    }

    public void setPlayerGraphicFile(String playerGraphicFile) {
        this.playerGraphicFile = playerGraphicFile;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }
}
