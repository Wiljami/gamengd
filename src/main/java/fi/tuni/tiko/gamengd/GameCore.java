package fi.tuni.tiko.gamengd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameCore extends Application {
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static String windowTitle = "GamEngD Game Engine";
    private Canvas canvas;
    SpriteController sc;

    @Override
    public void init() {
        canvas();
        sc = new SpriteController(canvas);
        System.out.println("This is GameCore::Init");
    }

    @Override
    public void stop() {
        System.out.println("This is GameCore::Stop");
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(windowTitle);
        Scene scene = new Scene(createRoot(), resolutionX, resolutionY);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.show();
        sc.start();
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