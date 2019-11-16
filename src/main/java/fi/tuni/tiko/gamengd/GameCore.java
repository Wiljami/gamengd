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
    private static String windowTitle = "GamEngD Game Engine";

    private static double tileSize = 100;

    private GameView gameView;
    private SpriteController sc;
    private ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private ArrayList<String> input = new ArrayList<>();
    private long lastNanoTime;

    private Player player;
    private Level level;

    @Override
    public void init() {
        System.out.println("Author: Viljami Pietarila");
        System.out.println("This is GameCore::Init");
        Tile.setupTiles();
        sc = new SpriteController();
        gameView = new GameView();
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
        updateSprites();
        sc.render(gc, elapsedTime);
    }

    private void updateSprites() {
        sc.clear();
        Canvas canvas = gameView.getCanvas();
        int width = (int) Math.ceil (canvas.getWidth() / tileSize);
        int height = (int) Math.ceil (canvas.getHeight() / tileSize);
        System.out.println("Width: " + width + " Height: " + height);
        double centerSpriteX = canvas.getWidth()-tileSize;
        double centerSpriteY = canvas.getHeight()-tileSize;
        System.out.println("Width: " + centerSpriteX + " Height: " + centerSpriteY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Sprite s = level.getTileAt(x, y);
                s.setPositionX(tileSize*x);
                s.setPositionY(tileSize*y);
                sc.addTileSprite(s);
            }
        }
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
        sc.addEntitySprite(player.getSprite());
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