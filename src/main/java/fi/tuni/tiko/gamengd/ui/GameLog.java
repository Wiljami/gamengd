package fi.tuni.tiko.gamengd.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.util.LinkedList;

public class GameLog extends ScrollPane {
    private final int MAXLOGSIZE = 100;
    private final int LOGSIZE = 6;

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
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(LOGSIZE);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        return textArea;
    }

    public void updateGameLog(String message) {
        updateLog(message);
        updateTextArea();
    }

    private void updateLog(String message) {
        if (textLog.size() >= MAXLOGSIZE) {
            textLog.remove(0);
        }
        textLog.add(message);
    }
}
