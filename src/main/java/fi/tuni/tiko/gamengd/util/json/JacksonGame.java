package fi.tuni.tiko.gamengd.util.json;

public class JacksonGame {
    private String windowTitle;
    private JacksonLevel[] levels;
    private JacksonPlayer player;

    public JacksonGame() {
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
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
