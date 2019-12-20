package fi.tuni.tiko.gamengd;

public class GameConfig {
    private static String game = "game.json";
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static double minResolutionX = 600;
    private static double minResolutionY = 400;
    private static boolean fullScreen = false;
    private static double tileSize = 50;

    private static boolean mouseControl = true;
    private static boolean toolTips = true;

    /**
     * file name of the fail picture that is used when picture is not found.
     */
    private static String failPicture = "fail.png";
    /**
     * Default folder where the ImageLoader looks for the image files.
     */
    private static String graphicsFolder = "graphics/";

    /**
     * Folder for monster jsons.
     */
    private static String monsterFolder = "monsters/";
    /**
     * Folder for maps.
     */
    private static String mapFolder = "maps/";

    private static int logHistory = 100;
    private static int logHeight = 6;

    private static String arrowDiag = "arrowDiagonal.png";
    private static String arrowStraight = "arrowStraight.png";
    private static String centerSquare = "square.png";
    private static String stairsUp = "stairsUp.png";
    private static String stairsDown = "stairsDown.png";
    private static String voidTile = "voidtile.png";

    private static int playerMovementDelay = 150;

    public static double getResolutionX() {
        return resolutionX;
    }

    public static void setResolutionX(double resolutionX) {
        GameConfig.resolutionX = resolutionX;
    }

    public static double getResolutionY() {
        return resolutionY;
    }

    public static void setResolutionY(double resolutionY) {
        GameConfig.resolutionY = resolutionY;
    }

    public static double getMinResolutionX() {
        return minResolutionX;
    }

    public static void setMinResolutionX(double minResolutionX) {
        GameConfig.minResolutionX = minResolutionX;
    }

    public static double getMinResolutionY() {
        return minResolutionY;
    }

    public static void setMinResolutionY(double minResolutionY) {
        GameConfig.minResolutionY = minResolutionY;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setFullScreen(boolean fullScreen) {
        GameConfig.fullScreen = fullScreen;
    }

    public static String getGame() {
        return game;
    }

    public static void setGame(String game) {
        GameConfig.game = game;
    }

    public static boolean isMouseControl() {
        return mouseControl;
    }

    public static void setMouseControl(boolean mouseControl) {
        GameConfig.mouseControl = mouseControl;
    }

    public static boolean isToolTips() {
        return toolTips;
    }

    public static void setToolTips(boolean toolTips) {
        GameConfig.toolTips = toolTips;
    }

    public static String getFailPicture() {
        return failPicture;
    }

    public static void setFailPicture(String failPicture) {
        GameConfig.failPicture = failPicture;
    }

    public static String getGraphicsFolder() {
        return graphicsFolder;
    }

    public static void setGraphicsFolder(String graphicsFolder) {
        GameConfig.graphicsFolder = graphicsFolder;
    }

    public static String getMonsterFolder() {
        return monsterFolder;
    }

    public static void setMonsterFolder(String monsterFolder) {
        GameConfig.monsterFolder = monsterFolder;
    }

    public static String getMapFolder() {
        return mapFolder;
    }

    public static void setMapFolder(String mapFolder) {
        GameConfig.mapFolder = mapFolder;
    }

    public static String getArrowDiag() {
        return arrowDiag;
    }

    public static void setArrowDiag(String arrowDiag) {
        GameConfig.arrowDiag = arrowDiag;
    }

    public static String getArrowStraight() {
        return arrowStraight;
    }

    public static void setArrowStraight(String arrowStraight) {
        GameConfig.arrowStraight = arrowStraight;
    }

    public static String getCenterSquare() {
        return centerSquare;
    }

    public static void setCenterSquare(String centerSquare) {
        GameConfig.centerSquare = centerSquare;
    }

    public static String getStairsUp() {
        return stairsUp;
    }

    public static void setStairsUp(String stairsUp) {
        GameConfig.stairsUp = stairsUp;
    }

    public static String getStairsDown() {
        return stairsDown;
    }

    public static void setStairsDown(String stairsDown) {
        GameConfig.stairsDown = stairsDown;
    }

    public static String getVoidTile() {
        return voidTile;
    }

    public static void setVoidTile(String voidTile) {
        GameConfig.voidTile = voidTile;
    }

    public static double getTileSize() {
        return tileSize;
    }

    public static void setTileSize(double tileSize) {
        GameConfig.tileSize = tileSize;
    }

    public static int getPlayerMovementDelay() {
        return playerMovementDelay;
    }

    public static void setPlayerMovementDelay(int playerMovementDelay) {
        GameConfig.playerMovementDelay = playerMovementDelay;
    }

    public static int getLogHistory() {
        return logHistory;
    }

    public static void setLogHistory(int logHistory) {
        GameConfig.logHistory = logHistory;
    }

    public static int getLogHeight() {
        return logHeight;
    }

    public static void setLogHeight(int logHeight) {
        GameConfig.logHeight = logHeight;
    }
}
