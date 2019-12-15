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
import fi.tuni.tiko.gamengd.util.json.JSONLoader;
import fi.tuni.tiko.gamengd.util.json.JacksonGame;
import fi.tuni.tiko.gamengd.util.json.JacksonLevel;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.HashMap;

public class GameCore extends Application {
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static double minResolutionX = 600;
    private static double minResolutionY = 400;
    private static boolean fullScreen = false;
    private static String windowTitle = "GamEngD Game Engine";
    private static String defaultGame = "game.json";

    private GameView gameView;
    private ArrayList<String> input = new ArrayList<>();
    private HashMap<String, Level> levels = new HashMap<>();
    private long lastNanoTime;

    private Level currentLevel;

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
        System.out.println("This is GameCore::Init");
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
        sortGameFile(JSONLoader.loadGameFile(defaultGame));
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

    @Override
    public void stop() {
        System.out.println("This is GameCore::Stop");
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(windowTitle);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(createScene());

        stage.show();
        stage.setMinWidth(minResolutionX);
        stage.setMinHeight(minResolutionY);
        stage.setFullScreen(fullScreen);
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
        Scene scene = new Scene(ui, resolutionX, resolutionY);

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

    public static void setResolution(double x, double y) {
        resolutionX = x;
        resolutionY = y;
    }

    public static void setMinResolution(double x, double y) {
        minResolutionX = x;
        minResolutionY = y;
    }

    public static void setWindowTitle(String title) {
        windowTitle = title;
    }

    public static void setDefaultGame(String defaultGame) {
        GameCore.defaultGame = defaultGame;
    }

    public static String getDefaultGame() {
        return defaultGame;
    }

    public CrisisController getCrisisController() {
        return crisisController;
    }

    public TurnController getTurnController() {
        return turnController;
    }

    public static boolean isFullScreen() {
        return fullScreen;
    }

    public static void setFullScreen(boolean fullScreen) {
        GameCore.fullScreen = fullScreen;
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
