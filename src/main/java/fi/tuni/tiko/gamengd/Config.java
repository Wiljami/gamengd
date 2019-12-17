package fi.tuni.tiko.gamengd;

public class Config {
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static double minResolutionX = 600;
    private static double minResolutionY = 400;
    private static boolean fullScreen = false;
    private static String defaultGame = "game.json";

    private static boolean mouseControl = true;
    private static boolean toolTips = true;

    public static double getResolutionX() {
        return resolutionX;
    }

    public static void setResolutionX(double resolutionX) {
        Config.resolutionX = resolutionX;
    }

    public static double getResolutionY() {
        return resolutionY;
    }

    public static void setResolutionY(double resolutionY) {
        Config.resolutionY = resolutionY;
    }

    public static double getMinResolutionX() {
        return minResolutionX;
    }

    public static void setMinResolutionX(double minResolutionX) {
        Config.minResolutionX = minResolutionX;
    }

    public static double getMinResolutionY() {
        return minResolutionY;
    }

    public static void setMinResolutionY(double minResolutionY) {
        Config.minResolutionY = minResolutionY;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setFullScreen(boolean fullScreen) {
        Config.fullScreen = fullScreen;
    }

    public static String getDefaultGame() {
        return defaultGame;
    }

    public static void setDefaultGame(String defaultGame) {
        Config.defaultGame = defaultGame;
    }

    public static boolean isMouseControl() {
        return mouseControl;
    }

    public static void setMouseControl(boolean mouseControl) {
        Config.mouseControl = mouseControl;
    }

    public static boolean isToolTips() {
        return toolTips;
    }

    public static void setToolTips(boolean toolTips) {
        Config.toolTips = toolTips;
    }
}
