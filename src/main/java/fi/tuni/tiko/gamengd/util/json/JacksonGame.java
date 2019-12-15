package fi.tuni.tiko.gamengd.util.json;

public class JacksonGame {
    private String gameTitle;
    private JacksonLevel[] levels;
    private JacksonPlayer player;

    public JacksonGame() {
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public JacksonLevel[] getLevels() {
        return levels;
    }

    public void setLevels(JacksonLevel[] levels) {
        this.levels = levels;
    }

    public JacksonPlayer getPlayer() {
        return player;
    }

    public void setPlayer(JacksonPlayer player) {
        this.player = player;
    }
}
