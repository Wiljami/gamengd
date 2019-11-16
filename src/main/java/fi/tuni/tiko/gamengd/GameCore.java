package fi.tuni.tiko.gamengd;

import fi.tuni.tiko.gamengd.ui.GameView;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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

    private static double tileSize = 100;

    private GameView gameView;
    private SpriteController spriteController;
    private ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private ArrayList<String> input = new ArrayList<>();
    private long lastNanoTime;

    private Player player;
    private Level level;
    private Camera camera;

    @Override
    public void init() {
        System.out.println("Author: Viljami Pietarila");
        System.out.println("This is GameCore::Init");
        Tile.setupTiles();
        spriteController = new SpriteController();
        gameView = new GameView();
        camera = new Camera(0,0);
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
        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            camera.setCameraChanged(true);
        });
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            camera.setCameraChanged(true);
        });
        stage.show();
        stage.setMinWidth(minResolutionX);
        stage.setMinHeight(minResolutionY);
        stage.setFullScreen(fullScreen);
        startAnimationTimer();
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
        if (camera.isCameraChanged()) updateSprites();
        spriteController.render(gc, elapsedTime);
    }

    private void updateSprites() {
        camera.setCameraChanged(false);
        spriteController.clear();
        Canvas canvas = gameView.getCanvas();

        int horizontalTiles = (int) Math.ceil(canvas.getWidth() / tileSize) + 1;
        if (horizontalTiles % 2 == 0) horizontalTiles++;

        int verticalTiles = (int) Math.ceil(canvas.getHeight() / tileSize) + 1;
        if (verticalTiles % 2 == 0) verticalTiles++;

        double centerSpriteX = canvas.getWidth() / 2 - (tileSize / 2);
        double centerSpriteY = canvas.getHeight() / 2 - (tileSize / 2);

        int centerTileX = (int) camera.getX();
        int centerTileY = (int) camera.getY();

        for (int x = -horizontalTiles/2; x < horizontalTiles/2; x++) {
            for (int y = -verticalTiles/2; y < verticalTiles/2; y++) {
                Sprite tile = level.getTileAt(centerTileX + x, centerTileY + y);
                tile.setPositionX(centerSpriteX + x * tileSize);
                tile.setPositionY(centerSpriteY + y * tileSize);
                spriteController.addTileSprite(tile);
            }
        }

        player.getSprite().setPositionX(centerSpriteX + (centerTileX - player.getX()) * tileSize);
        player.getSprite().setPositionY(centerSpriteY + (centerTileY - player.getY()) * tileSize);

        spriteController.addEntitySprite(player.getSprite());
    }

    private void handleInput(double elapsedTime) {
        for (KeyListener listener : keyListeners) {
            listener.receiveInput(input, elapsedTime);
        }
    }

    private Scene createScene() {
        Scene scene = new Scene(createRoot(), resolutionX, resolutionY);

        scene.setOnKeyPressed(keyEvent -> {
            String key = keyEvent.getCode().toString();
            if (!input.contains(key)) {
                input.add(key);
            }
        });

        scene.setOnKeyReleased(keyEvent -> {
            String key = keyEvent.getCode().toString();
            input.remove(key);
            for (KeyListener listener : keyListeners) {
                listener.receiveInput(key);
            }
        });

        return scene;
    }

    void addPlayer(Player player) {
        this.player = player;
        player.setupCamera(camera);
        camera.setXY(player.getX() + 0.5, player.getY() + 0.5);
        spriteController.addEntitySprite(player.getSprite());
        keyListeners.add(player);
    }

    void addLevel(Level level) {
        //level.addTilesToSpriteController(sc);
        this.level = level;
    }

    private BorderPane createRoot () {
        BorderPane root = new BorderPane();
        root.setCenter(gameView);
        root.setTop(topBar());
        root.setBottom(bottomBar());
        root.setRight(rightColumn());
        root.setLeft(leftColumn());
        return root;
    }

    private VBox topBar() {
        MenuBar menuBar = new MenuBar();
        Label label = new Label("Hi, I am the topBar");
        VBox topBar = new VBox(menuBar, label);
        return topBar;
    }

    private VBox bottomBar() {
        Label label = new Label("Hi, I am the bottomBar");
        VBox topBar = new VBox(label);
        return topBar;
    }

    private VBox rightColumn() {
        Label label = new Label("Hi, I am the rightColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }

    private VBox leftColumn() {
        Label label = new Label("Hi, I am the leftColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }

    static void setResolution(double x, double y) {
        resolutionX = x;
        resolutionY = y;
    }

    static void setWindowTitle(String title) {
        windowTitle = title;
    }
}