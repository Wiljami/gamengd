package fi.tuni.tiko.gamengd;

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

    private Canvas canvas;
    SpriteController sc;
    ArrayList<KeyListener> keyListeners = new ArrayList<>();
    ArrayList<String> input = new ArrayList<>();
    private long lastNanoTime;

    @Override
    public void init() {
        canvas();
        sc = new SpriteController();
        System.out.println("This is GameCore::Init");
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
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        sc.render(gc, elapsedTime);
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
        });

        return scene;
    }

    private BorderPane createRoot () {
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        root.setTop(topBar());
        root.setBottom(bottomBar());
        root.setRight(rightColumn());
        root.setLeft(leftColumn());
        return root;
    }

    private Canvas canvas() {
        canvas = new Canvas(1200,800);
        return canvas;
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