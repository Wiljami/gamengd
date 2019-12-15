package fi.tuni.tiko.gamengd.controller.input;

public class InputEvent {
    private String type;
    private String payLoad;
    public InputEvent(String type) {
        setType(type);
    }

    public InputEvent(String type, String payLoad) {
        setType(type);
        setPayLoad(payLoad);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPayLoad() {
        return payLoad;
    }

    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }
}