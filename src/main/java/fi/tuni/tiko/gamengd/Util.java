package fi.tuni.tiko.gamengd;

import javafx.scene.image.Image;

public class Util {
    public static Image loadImage(String file) {
        Image image;
        try {
            image = new Image(file);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + " " + file);
            image = new Image("fail.png");
        }
        return image;
    }
}
