package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.input.InputEvent;
import fi.tuni.tiko.gamengd.scripts.Util;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class UI extends BorderPane {
    private InputController inputController;

    public UI(GameView gameView, InputController inputController) {
        this.inputController = inputController;
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

    private Button movementButton(ImageView image) {
        Button b = new Button("", image);
        b.setFocusTraversable(false);
        return b;
    }

    private Pane rightColumn() {
        GridPane pane = new GridPane();
        Button nw = movementButton(buttonImage(270, false));
        nw.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NW")));
        Button n = movementButton(buttonImage(0, true));
        n.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:N")));
        Button ne = movementButton(buttonImage(0, false));
        ne.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NE")));
        Button w = movementButton(buttonImage(270, true));
        w.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:W")));
        Button none = movementButton(new ImageView(Util.loadImage("square.png")));
        none.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NONE")));
        Button e = movementButton(buttonImage(90, true));
        e.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:E")));
        Button sw = movementButton(buttonImage(180, false));
        sw.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:SW")));
        Button s = movementButton(buttonImage(180, true));
        s.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:S")));
        Button se = movementButton(buttonImage(90, false));
        se.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:SE")));

        pane.add(nw, 0,0);
        pane.add(n, 1,0);
        pane.add(ne, 2,0);
        pane.add(w, 0,1);
        pane.add(none, 1,1);
        pane.add(e, 2,1);
        pane.add(sw, 0,2);
        pane.add(s, 1,2);
        pane.add(se, 2,2);

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
