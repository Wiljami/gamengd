package fi.tuni.tiko.gamengd.util.json;

public class JacksonGame {
    private String windowTitle;
    private int resolutionX;
    private int resolutionY;
    private JacksonLevel[] levels;

    public JacksonGame() {
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public JacksonLevel[] getLevels() {
        return levels;
    }

    public void setLevels(JacksonLevel[] levels) {
        this.levels = levels;
    }
}
