package fi.tuni.tiko.gamengd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameCore extends Application {
    private static double resolutionX = 1200;
    private static double resolutionY = 800;
    private static String windowTitle = "GamEngD Game Engine";

    @Override
    public void init() {
        System.out.println("This is GameCore::Init");
    }

    @Override
    public void stop() {
        System.out.println("This is GameCore::Stop");
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle(windowTitle);
        Canvas canvas = canvas();
        StackPane root = new StackPane(canvas);
        Scene content = new Scene(root, resolutionX, resolutionY);
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(content);
        stage.show();

        Sprite dude = new Sprite("dude.png");
        dude.setVelocityX(10);
        dude.setVelocityY(10);

        Sprite dude2 = new Sprite("fasdail.png", 10, 10);

        SpriteController sc = new SpriteController(canvas);
        sc.addSprite(dude);
        sc.addSprite(dude2);
        sc.start();
    }

    private Canvas canvas() {
        return new Canvas(1200,800);
    }

    static void setResolution(double x, double y) {
        resolutionX = x;
        resolutionY = y;
    }

    static void setWindowTitle(String title) {
        windowTitle = title;
    }
}