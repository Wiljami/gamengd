package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameConfig;
import fi.tuni.tiko.gamengd.controller.input.InputController;
import fi.tuni.tiko.gamengd.controller.input.InputEvent;
import fi.tuni.tiko.gamengd.util.ImageLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ArrowPad extends GridPane {
    public ArrowPad(InputController inputController) {
        Button nw = movementButton(buttonImage(270, false));
        nw.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NW")));
        Button n = movementButton(buttonImage(0, true));
        n.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:N")));
        Button ne = movementButton(buttonImage(0, false));
        ne.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NE")));
        Button w = movementButton(buttonImage(270, true));
        w.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:W")));
        Button none = movementButton(new ImageView(ImageLoader.loadImage(GameConfig.getCenterSquare())));
        none.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:NONE")));
        Button e = movementButton(buttonImage(90, true));
        e.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:E")));
        Button sw = movementButton(buttonImage(180, false));
        sw.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:SW")));
        Button s = movementButton(buttonImage(180, true));
        s.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:S")));
        Button se = movementButton(buttonImage(90, false));
        se.setOnAction(event -> inputController.receiveInputEvent(new InputEvent("button:SE")));

        add(nw, 0,0);
        add(n, 1,0);
        add(ne, 2,0);
        add(w, 0,1);
        add(none, 1,1);
        add(e, 2,1);
        add(sw, 0,2);
        add(s, 1,2);
        add(se, 2,2);

        setAlignment(Pos.BASELINE_CENTER);
    }

    private Button movementButton(ImageView image) {
        Button b = new Button("", image);
        b.setFocusTraversable(false);
        return b;
    }

    private ImageView buttonImage (double rotation, boolean isStraight) {
        Image image;
        if (isStraight) {
            image = ImageLoader.loadImage(GameConfig.getArrowStraight());
        } else {
            image = ImageLoader.loadImage(GameConfig.getArrowDiag());
        }

        ImageView imageView = new ImageView(image);
        imageView.setRotate(rotation);
        return imageView;
    }
}
