package fi.tuni.tiko.gamengd;


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
import java.util.ArrayList;
import java.util.HashMap;

public class GameCore extends Application {
    private GameView gameView;
    private ArrayList<String> input = new ArrayList<>();
    private HashMap<String, Level> levels = new HashMap<>();
    private long lastNanoTime;

    private Level currentLevel;
    private static String windowTitle;

    private CameraController cameraController;
    private SpriteController spriteController;
    private TurnController turnController;
    private InputController inputController;
    private CrisisController crisisController;
    private UIController uiController;

    public static void main(String[] args) {
        System.out.println("Author: Viljami Pietarila");
        System.exit(0);
    }

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

    private void sortGameFile(JacksonGame game) {
        for (JacksonLevel levelData: game.getLevels()) {
            levels.put(levelData.getId(), new Level(levelData, this));
        }

        currentLevel = levels.get(game.getPlayer().getLevelId());

        addPlayer(new Player(game.getPlayer(), currentLevel));

        setWindowTitle(game.getGameTitle());
    }

    public void changeLevel(String id, Player player) {
        currentLevel.removeUnit(player);
        currentLevel = levels.get(id);
        updatePlayerOnLevel(player);
    }

    private void addPlayer(Player player) {
        updatePlayerOnLevel(player);
        inputController.registerPlayer(player);
    }

    private void updatePlayerOnLevel(Player player) {
        player.setupCamera(cameraController);
        cameraController.setXY(player.getX() + 0.5, player.getY() + 0.5);
        cameraController.setLevel(currentLevel);
        currentLevel.addUnit(player);
        currentLevel.setPlayer(player);
    }

    public void loadGame() {
        File loadFile = fileChooser().showOpenDialog(null);
        sortGameFile(JacksonLoader.loadGameFile(GameConfig.getGame()));
    }

    public void saveGame() {
        File saveFile = fileChooser().showSaveDialog(null);
    }

    private FileChooser fileChooser() {
        FileChooser fileChooser = new FileChooser();
        File directory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(directory);
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Gamengd save files", "*.sav")
        );
        return fileChooser;
    }

    @Override
    public void stop() {
    }

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

    private void startTurnController() {
        turnController.doTurn();
    }

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

    private void handleGraphics(double elapsedTime) {
        Canvas canvas = gameView.getCanvas();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if (cameraController.isCameraChanged()) updateSprites();
        spriteController.render(gc, elapsedTime, cameraController.getTileSize());
    }

    private void updateSprites() {
        spriteController.clear();
        cameraController.updateSprites();
    }

    private void handleInput(double elapsedTime) {
        inputController.receiveInput(input, elapsedTime);
    }

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

    public static void setWindowTitle(String title) {
        windowTitle = title;
    }

    public CrisisController getCrisisController() {
        return crisisController;
    }

    public TurnController getTurnController() {
        return turnController;
    }

    public UIController getUiController() {
        return uiController;
    }

    public GameView getGameView() {
        return gameView;
    }

    public InputController getInputController() {
        return inputController;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }

    public Level getLevel(String id) {
        return levels.get(id);
    }
}
