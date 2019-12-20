package fi.tuni.tiko.gamengd.ui;

import fi.tuni.tiko.gamengd.GameConfig;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import org.apache.commons.lang.StringUtils;

import java.util.LinkedList;

/**
 * GameLog is the UI element of text log.
 *
 * @author Viljami Pietarila
 * @version 2019.1220
 */
public class GameLog extends ScrollPane {
    /**
     * Max length of logged history.
     */
    private int logHistory;
    /**
     * Displayed height of the log in rows.
     */
    private int logHeight;
    /**
     * Logged history.
     */
    private LinkedList<String> textLog;
    /**
     * TextArea.
     */
    private TextArea textArea;

    /**
     * GameLog constructor.
     */
    public GameLog() {
        setLogHeight(GameConfig.getLogHeight());
        setLogHistory(GameConfig.getLogHistory());
        textLog = new LinkedList<>();
        textArea = createTextArea();
        setContent(textArea);
        setFitToWidth(true);
        updateTextArea();
    }

    /**
     * updateTextArea updates the displayed text within the log.
     */
    private void updateTextArea() {
        String text = "";
        for (String s : textLog) {
            text += s + "\n";
        }

        text = StringUtils.chomp(text);

        textArea.setText(text);
        textArea.setScrollTop(Double.MAX_VALUE);
    }

    /**
     * createTextArea creates a new TextArea UI element.
     * @return TextArea
     */
    private TextArea createTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefRowCount(logHeight);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setFocusTraversable(false);
        return textArea;
    }

    /**
     * updateGameLog adds a new line of text to the log.
     * @param message String to be added to the log.
     */
    public void updateGameLog(String message) {
        updateLog(message);
        updateTextArea();
    }

    /**
     * updateLog adds the new line of text to the log.
     *
     * updateLog also makes sure that the textLog's size remains within the
     * bounds.
     * @param message new log line to be added.
     */
    private void updateLog(String message) {
        if (textLog.size() >= logHistory) {
            textLog.remove(0);
        }
        textLog.add(message);
    }

    /**
     * getter for logHistory.
     * @return logHistory
     */
    public int getLogHistory() {
        return logHistory;
    }

    /**
     * setter for logHistory.
     * @param logHistory new logHistory
     */
    public void setLogHistory(int logHistory) {
        this.logHistory = logHistory;
    }

    /**
     * getter for logHeight.
     * @return logHeight
     */
    public int getLogHeight() {
        return logHeight;
    }

    /**
     * setter for logHeight.
     * @param logHeight new logHeight
     */
    public void setLogHeight(int logHeight) {
        this.logHeight = logHeight;
    }
}
