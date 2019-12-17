package fi.tuni.tiko.gamengd.controller.input;

/**
 * InputEvent is a class containing info a button press or similar.
 *
 * InputEvent contains info of a button press and lets the InputController
 * treat it like a key board press.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class InputEvent {
    /**
     * type of the Input.
     */
    private String type;

    /**
     * InputEvent constructor.
     * @param type String type
     */
    public InputEvent(String type) {
        setType(type);
    }

    /**
     * setter for type.
     * @param type new type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * getter for type.
     * @return String type.
     */
    public String getType() {
        return type;
    }
}
