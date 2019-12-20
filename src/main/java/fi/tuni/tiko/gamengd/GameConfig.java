package fi.tuni.tiko.gamengd;

/**
 * GameConfig is a static class holding various configuration data.
 *
 * GameConfig holds various configuration data that the other elements
 * of the game engine will call for their own needs. Each of the configuration
 * pieces have their setters and getters and the game launcher can access them
 * before launching the game itself.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class GameConfig {
    /**
     * File name of the game json file.
     */
    private static String game = "game.json";
    /**
     * Window resolution in x.
     */
    private static double resolutionX = 1200;
    /**
     * Window resolution in y.
     */
    private static double resolutionY = 800;
    /**
     * Min window resolution in x.
     */
    private static double minResolutionX = 600;
    /**
     * Min window resolution in y.
     */
    private static double minResolutionY = 400;
    /**
     * Boolean wether the game should launch in full screen.
     */
    private static boolean fullScreen = false;
    /**
     * Starting tileSize of the game.
     */
    private static double tileSize = 50;

    /**
     * Boolean wether the ArrowPad should be displayed.
     */
    private static boolean mouseControl = true;
    /**
     * Boolean wether the mouse tooltips are enabled.
     */
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

    /**
     * Number of lines stored in the game log.
     */
    private static int logHistory = 100;
    /**
     * Height of the game log in rows.
     */
    private static int logHeight = 6;

    /**
     * File name for the arrowDiag image.
     */
    private static String arrowDiag = "arrowDiagonal.png";
    /**
     * File name for the arrowStraight image.
     */
    private static String arrowStraight = "arrowStraight.png";
    /**
     * File name for the centerSquare image.
     */
    private static String centerSquare = "square.png";
    /**
     * File name for the stairsUp image.
     */
    private static String stairsUp = "stairsUp.png";
    /**
     * File name for the stairsDown image.
     */
    private static String stairsDown = "stairsDown.png";
    /**
     * File name for the voidTile image.
     */
    private static String voidTile = "voidtile.png";

    /**
     * Delay between the player inputs in milliseconds.
     */
    private static int playerMovementDelay = 150;

    /**
     * Getter for game
     *
     * @return value of game
     */
    public static String getGame() {
        return game;
    }

    /**
     * Sets game
     *
     * @param game new value
     */
    public static void setGame(String game) {
        GameConfig.game = game;
    }

    /**
     * Getter for resolutionX
     *
     * @return value of resolutionX
     */
    public static double getResolutionX() {
        return resolutionX;
    }

    /**
     * Sets resolutionX
     *
     * @param resolutionX new value
     */
    public static void setResolutionX(double resolutionX) {
        GameConfig.resolutionX = resolutionX;
    }

    /**
     * Getter for resolutionY
     *
     * @return value of resolutionY
     */
    public static double getResolutionY() {
        return resolutionY;
    }

    /**
     * Sets resolutionY
     *
     * @param resolutionY new value
     */
    public static void setResolutionY(double resolutionY) {
        GameConfig.resolutionY = resolutionY;
    }

    /**
     * Getter for minResolutionX
     *
     * @return value of minResolutionX
     */
    public static double getMinResolutionX() {
        return minResolutionX;
    }

    /**
     * Sets minResolutionX
     *
     * @param minResolutionX new value
     */
    public static void setMinResolutionX(double minResolutionX) {
        GameConfig.minResolutionX = minResolutionX;
    }

    /**
     * Getter for minResolutionY
     *
     * @return value of minResolutionY
     */
    public static double getMinResolutionY() {
        return minResolutionY;
    }

    /**
     * Sets minResolutionY
     *
     * @param minResolutionY new value
     */
    public static void setMinResolutionY(double minResolutionY) {
        GameConfig.minResolutionY = minResolutionY;
    }

    /**
     * Getter for fullScreen
     *
     * @return value of fullScreen
     */
    public static boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * Sets fullScreen
     *
     * @param fullScreen new value
     */
    public static void setFullScreen(boolean fullScreen) {
        GameConfig.fullScreen = fullScreen;
    }

    /**
     * Getter for tileSize
     *
     * @return value of tileSize
     */
    public static double getTileSize() {
        return tileSize;
    }

    /**
     * Sets tileSize
     *
     * @param tileSize new value
     */
    public static void setTileSize(double tileSize) {
        GameConfig.tileSize = tileSize;
    }

    /**
     * Getter for mouseControl
     *
     * @return value of mouseControl
     */
    public static boolean isMouseControl() {
        return mouseControl;
    }

    /**
     * Sets mouseControl
     *
     * @param mouseControl new value
     */
    public static void setMouseControl(boolean mouseControl) {
        GameConfig.mouseControl = mouseControl;
    }

    /**
     * Getter for toolTips
     *
     * @return value of toolTips
     */
    public static boolean isToolTips() {
        return toolTips;
    }

    /**
     * Sets toolTips
     *
     * @param toolTips new value
     */
    public static void setToolTips(boolean toolTips) {
        GameConfig.toolTips = toolTips;
    }

    /**
     * Getter for failPicture
     *
     * @return value of failPicture
     */
    public static String getFailPicture() {
        return failPicture;
    }

    /**
     * Sets failPicture
     *
     * @param failPicture new value
     */
    public static void setFailPicture(String failPicture) {
        GameConfig.failPicture = failPicture;
    }

    /**
     * Getter for graphicsFolder
     *
     * @return value of graphicsFolder
     */
    public static String getGraphicsFolder() {
        return graphicsFolder;
    }

    /**
     * Sets graphicsFolder
     *
     * @param graphicsFolder new value
     */
    public static void setGraphicsFolder(String graphicsFolder) {
        GameConfig.graphicsFolder = graphicsFolder;
    }

    /**
     * Getter for monsterFolder
     *
     * @return value of monsterFolder
     */
    public static String getMonsterFolder() {
        return monsterFolder;
    }

    /**
     * Sets monsterFolder
     *
     * @param monsterFolder new value
     */
    public static void setMonsterFolder(String monsterFolder) {
        GameConfig.monsterFolder = monsterFolder;
    }

    /**
     * Getter for mapFolder
     *
     * @return value of mapFolder
     */
    public static String getMapFolder() {
        return mapFolder;
    }

    /**
     * Sets mapFolder
     *
     * @param mapFolder new value
     */
    public static void setMapFolder(String mapFolder) {
        GameConfig.mapFolder = mapFolder;
    }

    /**
     * Getter for logHistory
     *
     * @return value of logHistory
     */
    public static int getLogHistory() {
        return logHistory;
    }

    /**
     * Sets logHistory
     *
     * @param logHistory new value
     */
    public static void setLogHistory(int logHistory) {
        GameConfig.logHistory = logHistory;
    }

    /**
     * Getter for logHeight
     *
     * @return value of logHeight
     */
    public static int getLogHeight() {
        return logHeight;
    }

    /**
     * Sets logHeight
     *
     * @param logHeight new value
     */
    public static void setLogHeight(int logHeight) {
        GameConfig.logHeight = logHeight;
    }

    /**
     * Getter for arrowDiag
     *
     * @return value of arrowDiag
     */
    public static String getArrowDiag() {
        return arrowDiag;
    }

    /**
     * Sets arrowDiag
     *
     * @param arrowDiag new value
     */
    public static void setArrowDiag(String arrowDiag) {
        GameConfig.arrowDiag = arrowDiag;
    }

    /**
     * Getter for arrowStraight
     *
     * @return value of arrowStraight
     */
    public static String getArrowStraight() {
        return arrowStraight;
    }

    /**
     * Sets arrowStraight
     *
     * @param arrowStraight new value
     */
    public static void setArrowStraight(String arrowStraight) {
        GameConfig.arrowStraight = arrowStraight;
    }

    /**
     * Getter for centerSquare
     *
     * @return value of centerSquare
     */
    public static String getCenterSquare() {
        return centerSquare;
    }

    /**
     * Sets centerSquare
     *
     * @param centerSquare new value
     */
    public static void setCenterSquare(String centerSquare) {
        GameConfig.centerSquare = centerSquare;
    }

    /**
     * Getter for stairsUp
     *
     * @return value of stairsUp
     */
    public static String getStairsUp() {
        return stairsUp;
    }

    /**
     * Sets stairsUp
     *
     * @param stairsUp new value
     */
    public static void setStairsUp(String stairsUp) {
        GameConfig.stairsUp = stairsUp;
    }

    /**
     * Getter for stairsDown
     *
     * @return value of stairsDown
     */
    public static String getStairsDown() {
        return stairsDown;
    }

    /**
     * Sets stairsDown
     *
     * @param stairsDown new value
     */
    public static void setStairsDown(String stairsDown) {
        GameConfig.stairsDown = stairsDown;
    }

    /**
     * Getter for voidTile
     *
     * @return value of voidTile
     */
    public static String getVoidTile() {
        return voidTile;
    }

    /**
     * Sets voidTile
     *
     * @param voidTile new value
     */
    public static void setVoidTile(String voidTile) {
        GameConfig.voidTile = voidTile;
    }

    /**
     * Getter for playerMovementDelay
     *
     * @return value of playerMovementDelay
     */
    public static int getPlayerMovementDelay() {
        return playerMovementDelay;
    }

    /**
     * Sets playerMovementDelay
     *
     * @param playerMovementDelay new value
     */
    public static void setPlayerMovementDelay(int playerMovementDelay) {
        GameConfig.playerMovementDelay = playerMovementDelay;
    }
}
