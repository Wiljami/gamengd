package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.controller.SpriteController;
import fi.tuni.tiko.gamengd.controller.TurnController;
import fi.tuni.tiko.gamengd.ui.*;
import fi.tuni.tiko.gamengd.entity.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.ArrayList;

public class GameCore extends Application {
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static double minResolutionX = 600;
    private static double minResolutionY = 400;
    private static boolean fullScreen = false;
    private static String windowTitle = "GamEngD Game Engine";

    private GameView gameView;
    private ArrayList<InputListener> inputListeners = new ArrayList<>();
    private ArrayList<String> input = new ArrayList<>();
    private long lastNanoTime;

    private Level level;

    private CameraController cameraController;
    private SpriteController spriteController;
    private TurnController turnController;

    @Override
    public void init() {
        System.out.println("Author: Viljami Pietarila");
        System.out.println("This is GameCore::Init");
        //TODO: Change these to a check in Entity or something like that.
        Floor.setup();
        Wall.setup();
        Monster.setup();
        spriteController = new SpriteController();
        turnController = new TurnController();
        gameView = new GameView();
        cameraController = new CameraController(gameView.getCanvas(), spriteController);
        inputListeners.add(cameraController);
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

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
            cameraController.setCameraChanged(true);

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

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
        for (InputListener listener : inputListeners) {
            listener.receiveInput(input, elapsedTime);
        }
    }

    private Scene createScene() {
        Scene scene = new Scene(new UI(gameView), resolutionX, resolutionY);

        scene.setOnKeyPressed(keyEvent -> {
            String key = keyEvent.getCode().toString();
            if (!input.contains(key)) {
                input.add(key);
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            String key = keyEvent.getCode().toString();
            input.remove(key);
            for (InputListener listener : inputListeners) {
                listener.receiveInput(key);
            }
        });

        return scene;
    }

    void addPlayer(Player player) {
        player.setupCamera(cameraController);
        cameraController.setXY(player.getX() + 0.5, player.getY() + 0.5);
        inputListeners.add(player);
        turnController.addTurn(player);
        level.addUnit(player);
    }

    void addMonster(Monster monster) {
        turnController.addTurn(monster);
        level.addUnit(monster);
    }

    void addLevel(Level level) {
        this.level = level;
        cameraController.setLevel(level);
    }

    static void setResolution(double x, double y) {
        resolutionX = x;
        resolutionY = y;
    }

    static void setMinResolution(double x, double y) {
        minResolutionX = x;
        minResolutionY = y;
    }

    static void setWindowTitle(String title) {
        windowTitle = title;
    }
}