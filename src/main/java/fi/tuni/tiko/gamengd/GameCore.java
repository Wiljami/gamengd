package fi.tuni.tiko.gamengd;


import com.fasterxml.jackson.databind.ObjectMapper;
import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.controller.SpriteController;
import fi.tuni.tiko.gamengd.controller.crisis.CrisisController;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.turn.TurnController;
import fi.tuni.tiko.gamengd.controller.ui.UIController;
import fi.tuni.tiko.gamengd.elements.Level;
import fi.tuni.tiko.gamengd.entity.*;
import fi.tuni.tiko.gamengd.ui.GameView;
import fi.tuni.tiko.gamengd.ui.UI;
import fi.tuni.tiko.gamengd.util.json.JacksonLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonGame;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * GameCore is the core of the game engine.
 *
 * GameCore is the heart of the engine. It initiates all the other elements
 * and objects of the game engine. It extends the Application from javaFX.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class GameCore extends Application {
    /**
     * gameView is the displayed game elements view.
     */
    private GameView gameView;
    /**
     * input is the List of inputs sent to the javafx.
     */
    private ArrayList<String> input = new ArrayList<>();
    /**
     * levels is hashMap of levels within the game.
     */
    private HashMap<String, Level> levels;
    /**
     * time of last frame in nanoseconds
     */
    private long lastNanoTime;

    /**
     * currentLevel is the level currently in use.
     */
    private Level currentLevel;
    /**
     * windowTitle is the title of the javafx window.
     */
    private static String windowTitle;

    /**
     * Reference to cameraController.
     */
    private CameraController cameraController;
    /**
     * Reference to spriteController.
     */
    private SpriteController spriteController;
    /**
     * Reference to turnController.
     */
    private TurnController turnController;
    /**
     * Reference to inputController.
     */
    private InputController inputController;
    /**
     * Reference to crisisController.
     */
    private CrisisController crisisController;
    /**
     * Reference to uiController.
     */
    private UIController uiController;

    /**
     * main method. Only prints out the author.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Author: Viljami Pietarila");
        launch();
        System.exit(0);
    }

    /**
     * init method setups the game engine.
     *
     * init method is overridden from the Application. It is used to setup
     * different controllers within the game engine and load the game file.
     */
    @Override
    public void init() {
        Entity.setup(this);
        spriteController = new SpriteController();
        crisisController = new CrisisController();
        turnController = new TurnController(crisisController);
        inputController = new InputController();
        uiController = new UIController();
        Unit.registerUIController(uiController);
        gameView = new GameView();
        cameraController = new CameraController(gameView.getCanvas(), spriteController);
        inputController.registerCamera(cameraController);
        turnController.registerTurnListener(cameraController);
        sortGameFile(JacksonLoader.loadGameFile(GameConfig.getGame()));
    }

    /**
     * sortGameFile sorts the JacksonGame file for game data.
     * @param game JacksonGame file to sort
     */
    private void sortGameFile(JacksonGame game) {
        levels = new HashMap<>();
        for (JacksonLevel levelData: game.getLevels()) {
            levels.put(levelData.getId(), new Level(levelData, this));
        }

        currentLevel = levels.get(game.getPlayer().getLevelId());

        addPlayer(new Player(game.getPlayer(), currentLevel));

        setWindowTitle(game.getGameTitle());
    }

    /**
     * changeLevel changes the current active level.
     * @param id id of the new level
     * @param player Player moving between the levels
     */
    public void changeLevel(String id, Player player) {
        currentLevel.removeUnit(player);
        currentLevel = levels.get(id);
        updatePlayerOnLevel(player);
    }

    /**
     * addPlayer adds the player entity.
     *
     * addPlayer registers the given player to the inputController.
     * @param player Player to be added
     */
    private void addPlayer(Player player) {
        updatePlayerOnLevel(player);
        inputController.registerPlayer(player);
    }

    /**
     * updatePlayerOnLevel updates the player positioning.
     *
     * Sets the cameraController to be centered on the player position and adds
     * the player to currentLevel's unit list and sets player to be its player.
     * @param player Player to be adjusted
     */
    private void updatePlayerOnLevel(Player player) {
        player.setupCamera(cameraController);
        cameraController.setXY(player.getX() + 0.5, player.getY() + 0.5);
        cameraController.setLevel(currentLevel);
        currentLevel.setPlayer(player);
    }

    /**
     * loadGame loads a saved game.
     *
     * loadGame opens a fileChooser dialog and from its file loads a previous
     * game state. It clears the controllers and essentially starts a new game
     * using the loaded save as a game file.
     */
    public void loadGame() {
        File loadFile = fileChooser().showOpenDialog(null);
        if (loadFile != null) {
            crisisController.clear();
            inputController.clear();
            turnController.clear();
            sortGameFile(JacksonLoader.loadGameFile(loadFile));
            startTurnController();
        }
    }

    /**
     * saveGame saves the current game state to a file.
     *
     * saveGame reads the current game state and creates a JacksonGame object
     * out of it. It then saves the file to the disk.
     */
    public void saveGame() {
        File saveFile = fileChooser().showSaveDialog(null);
        JacksonGame saveGame = new JacksonGame();
        saveGame.setGameTitle(windowTitle);
        saveGame.setLevels(new JacksonLevel[levels.size()]);
        int index = 0;
        for (Map.Entry<String, Level> entry : levels.entrySet()) {
            JacksonLevel level = entry.getValue().createJacksonLevel();
            saveGame.getLevels()[index] = level;
            index++;
        }
        saveGame.setPlayer(currentLevel.getPlayer().createJacksonPlayer());
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(saveFile, saveGame);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * fileChooser creates a FileChooser dialogue.
     *
     * fileChooser is used for both saving and loading. fileChooser sets up
     * the common settings for both saving and loading.
     * @return FileChooser
     */
    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();
        File directory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(directory);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Gamengd save files", "*.sav")
        );
        return fileChooser;
    }

    /**
     * stop is an overridden method from Application.
     *
     * stop is called when the Application window closes.
     */
    @Override
    public void stop() {
    }

    /**
     * start is an overridden method from Application.
     *
     * start begins the Application and it displays the visible elements.
     * @param stage the displayed window
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle(windowTitle);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(createScene());

        stage.show();
        stage.setMinWidth(GameConfig.getMinResolutionX());
        stage.setMinHeight(GameConfig.getMinResolutionY());
        stage.setFullScreen(GameConfig.isFullScreen());

        startAnimationTimer();
        startTurnController();
    }

    /**
     * startTurnController starts the turnController.
     */
    private void startTurnController() {
        turnController.doTurn();
    }

    /**
     * startAnimationTimer handles time between frames.
     */
    private void startAnimationTimer() {
        lastNanoTime = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                double elapsedTime = (currentNanoTime - lastNanoTime) / 1000000000.0;
                lastNanoTime = currentNanoTime;
                handleInput(elapsedTime);
                handleGraphics(elapsedTime);
            }
        }.start();
    }

    /**
     * handleGraphics creates handles graphics.
     *
     * handleGraphics gives the SpriteController the GraphicsContext and the
     * time since last frame to let the SpriteController draw different
     * Sprites on the canvas. If CameraController has changed, it tells it to
     * calculate new List of Sprites that are displayed on the Canvas.
     * @param elapsedTime time since last frame.
     */
    private void handleGraphics(double elapsedTime) {
        Canvas canvas = gameView.getCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (cameraController.isCameraChanged()) updateSprites();
        spriteController.render(gc, elapsedTime, cameraController.getTileSize());
    }

    /**
     * updateSprites tells updates the list of displayed Sprites.
     */
    private void updateSprites() {
        spriteController.clear();
        cameraController.updateSprites();
    }

    /**
     * handleInput gives inputController the inputs.
     * @param elapsedTime time since last frame.
     */
    private void handleInput(double elapsedTime) {
        inputController.receiveInput(input, elapsedTime);
    }

    /**
     * createScene creates the displayed portions of the game engine.
     *
     * createScene creates the UI for the game and adds it as to the scene's root.
     * @return created Scene
     */
    private Scene createScene() {
        UI ui = new UI(this);
        Scene scene = new Scene(ui, GameConfig.getResolutionX(), GameConfig.getResolutionX());

        scene.setOnKeyPressed(keyEvent -> {
            String key = keyEvent.getCode().toString();
            if (!input.contains(key)) {
                input.add(key);
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            String key = keyEvent.getCode().toString();
            input.remove(key);
            inputController.receiveInput(key);
        });

        return scene;
    }

    /**
     * Getter for a specific level using a id key.
     * @param id id of the level
     * @return Level with matching id
     */
    public Level getLevel(String id) {
        return levels.get(id);
    }

    /**
     * Getter for gameView
     *
     * @return value of gameView
     */
    public GameView getGameView() {
        return gameView;
    }

    /**
     * Sets gameView
     *
     * @param gameView new value
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Getter for currentLevel
     *
     * @return value of currentLevel
     */
    public Level getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Sets currentLevel
     *
     * @param currentLevel new value
     */
    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    /**
     * Getter for windowTitle
     *
     * @return value of windowTitle
     */
    public static String getWindowTitle() {
        return windowTitle;
    }

    /**
     * Sets windowTitle
     *
     * @param windowTitle new value
     */
    public static void setWindowTitle(String windowTitle) {
        GameCore.windowTitle = windowTitle;
    }

    /**
     * Getter for cameraController
     *
     * @return value of cameraController
     */
    public CameraController getCameraController() {
        return cameraController;
    }

    /**
     * Sets cameraController
     *
     * @param cameraController new value
     */
    public void setCameraController(CameraController cameraController) {
        this.cameraController = cameraController;
    }

    /**
     * Getter for spriteController
     *
     * @return value of spriteController
     */
    public SpriteController getSpriteController() {
        return spriteController;
    }

    /**
     * Sets spriteController
     *
     * @param spriteController new value
     */
    public void setSpriteController(SpriteController spriteController) {
        this.spriteController = spriteController;
    }

    /**
     * Getter for turnController
     *
     * @return value of turnController
     */
    public TurnController getTurnController() {
        return turnController;
    }

    /**
     * Sets turnController
     *
     * @param turnController new value
     */
    public void setTurnController(TurnController turnController) {
        this.turnController = turnController;
    }

    /**
     * Getter for inputController
     *
     * @return value of inputController
     */
    public InputController getInputController() {
        return inputController;
    }

    /**
     * Sets inputController
     *
     * @param inputController new value
     */
    public void setInputController(InputController inputController) {
        this.inputController = inputController;
    }

    /**
     * Getter for crisisController
     *
     * @return value of crisisController
     */
    public CrisisController getCrisisController() {
        return crisisController;
    }

    /**
     * Sets crisisController
     *
     * @param crisisController new value
     */
    public void setCrisisController(CrisisController crisisController) {
        this.crisisController = crisisController;
    }

    /**
     * Getter for uiController
     *
     * @return value of uiController
     */
    public UIController getUiController() {
        return uiController;
    }

    /**
     * Sets uiController
     *
     * @param uiController new value
     */
    public void setUiController(UIController uiController) {
        this.uiController = uiController;
    }
}
