package fi.tuni.tiko.gamengd.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class GameLog extends ScrollPane {
    public GameLog() {
        setContent(createTextArea());
        setFitToWidth(true);
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(8);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        return textArea;
    }
}
