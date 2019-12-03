package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.scripts.Util;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class UI extends BorderPane {

    public UI(GameView gameView) {
        setCenter(gameView);
        setTop(topBar());
        setBottom(bottomBar());
        setRight(rightColumn());
        setLeft(leftColumn());
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

    private Pane rightColumn() {
        GridPane pane = new GridPane();

        Button upLeft = new Button("", buttonImage(270, false));
        Button up = new Button("", buttonImage(0, true));
        Button upRight = new Button("", buttonImage(0, false));
        Button left = new Button("", buttonImage(270, true));
        Button center = new Button("", new ImageView(Util.loadImage("square.png")));
        Button right = new Button("", buttonImage(90, true));
        Button bottomLeft = new Button("", buttonImage(180, false));
        Button down = new Button("", buttonImage(180, true));
        Button bottomRight = new Button("", buttonImage(90, false));

        pane.add(upLeft, 0,0);
        pane.add(up, 1,0);
        pane.add(upRight, 2,0);
        pane.add(left, 0,1);
        pane.add(center, 1,1);
        pane.add(right, 2,1);
        pane.add(bottomLeft, 0,2);
        pane.add(down, 1,2);
        pane.add(bottomRight, 2,2);

        pane.setAlignment(Pos.BASELINE_CENTER);

        HBox rightColumn = new HBox(pane);
        return rightColumn;
    }

    private ImageView buttonImage (double rotation, boolean isStraight) {
        Image image;
        if (isStraight) {
            image = Util.loadImage("arrowStraight.png");
        } else {
            image = Util.loadImage("arrowCorner.png");
        }

        ImageView imageView = new ImageView(image);
        imageView.setRotate(rotation);
        return imageView;
    }

    private VBox leftColumn() {
        Label label = new Label("Hi, I am the leftColumn");
        VBox topBar = new VBox(label);
        return topBar;
    }

}
