package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.controller.ui.UIListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.util.LinkedList;

public class GameLog extends ScrollPane implements UIListener {
    private LinkedList<String> textLog;
    private TextArea textArea;

    public GameLog() {
        textLog = new LinkedList<>();
        textArea = createTextArea();
        setContent(textArea);
        setFitToWidth(true);
        updateTextArea();
    }

    private void updateTextArea() {
        String text = "";
        for (String s : textLog) {
            text += s + "\n";
        }
        textArea.setText(text);
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(6);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        return textArea;
    }

    @Override
    public void triggerUIListener(String message) {

    }
}
