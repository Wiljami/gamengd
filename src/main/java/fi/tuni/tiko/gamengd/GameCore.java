package fi.tuni.tiko.gamengd;

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
        //TODO: Change these to a check in Entity or something like that.
        Floor.setup();
        Wall.setup();
        Monster.setup();
        spriteController = new SpriteController();
        gameView = new GameView();
        camera = new Camera(0,0);
        keyListeners.add(camera);
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
            camera.setCameraChanged(true);

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

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
        spriteController.render(gc, elapsedTime, camera.getTileSize());
    }

    //TODO: Should this maybe be Camera's job?
    //TODO: This method is big. Cut it to pieces
    private void updateSprites() {
        spriteController.clear();
        Canvas canvas = gameView.getCanvas();

        double tileSize = camera.getTileSize();

        int horizontalTiles = (int) Math.ceil(canvas.getWidth() / tileSize) + 1;
        if (horizontalTiles % 2 == 0) horizontalTiles++;

        int verticalTiles = (int) Math.ceil(canvas.getHeight() / tileSize) + 1;
        if (verticalTiles % 2 == 0) verticalTiles++;

        double centerSpriteX = canvas.getWidth() / 2 - (tileSize / 2);
        double centerSpriteY = canvas.getHeight() / 2 - (tileSize / 2);

        int centerTileX = (int) camera.getX();
        int centerTileY = (int) camera.getY();

        for (int x = -horizontalTiles/2; x <= horizontalTiles/2; x++) {
            for (int y = -verticalTiles/2; y <= verticalTiles/2; y++) {
                Tile tile = level.getTileAt(centerTileX + x, centerTileY + y);
                Sprite floor = tile.getFloor().getSprite();

                double positionX = centerSpriteX + x * tileSize;
                double positionY = centerSpriteY + y * tileSize;

                floor.setPositionX(positionX);
                floor.setPositionY(positionY);

                spriteController.addFloorSprite(floor);
                if (tile.hasWall()) {
                    Sprite wall = tile.getWall().getSprite();
                    wall.setPositionX(positionX);
                    wall.setPositionY(positionY);
                    spriteController.addWallSprite(wall);
                }
            }
        }

        //TODO: Do this properly
        player.getSprite().setPositionX(centerSpriteX + (centerTileX - player.getX()) * tileSize);
        player.getSprite().setPositionY(centerSpriteY + (centerTileY - player.getY()) * tileSize);

        spriteController.addUnitSprite(player.getSprite());
        camera.setCameraChanged(false);
    }

    private void handleInput(double elapsedTime) {
        for (KeyListener listener : keyListeners) {
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
            for (KeyListener listener : keyListeners) {
                listener.receiveInput(key);
            }
        });

        return scene;
    }

    void addPlayer(Player player) {
        this.player = player;
        player.setupCamera(camera);
        player.setLevel(level);
        camera.setXY(player.getX() + 0.5, player.getY() + 0.5);
        spriteController.addUnitSprite(player.getSprite());
        keyListeners.add(player);
    }

    void addLevel(Level level) {
        this.level = level;
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