package fi.tuni.tiko.gamengd;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameCore extends Application {
    @Override
    public void init() {
        System.out.println("This is GameCore::Init");
    }

    @Override
    public void stop() {
        System.out.println("This is GameCore::Stop");
    }

    @Override
    public void start(Stage window) {
        window.setTitle("JavaFX HelloWorld!");
        Canvas canvas = canvas();
        StackPane root = new StackPane(canvas);
        Scene content = new Scene(root, 1200, 800);
        window.initStyle(StageStyle.DECORATED);
        window.setScene(content);
        window.show();

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
        Canvas canvas = new Canvas(1200,800);
        return canvas;
    }
}