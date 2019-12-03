package fi.tuni.tiko.gamengd.controller.input;

public class InputEvent {
    private String type;
    public InputEvent(String type) {
        setType(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}