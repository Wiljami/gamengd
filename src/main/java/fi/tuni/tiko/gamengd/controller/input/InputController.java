package fi.tuni.tiko.gamengd.controller.input;

import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * InputController attempts to centralize all the game inputs to one location.
 *
 * InputController receives different inputs such as key presses and button
 * presses. It then relays them to possible registered destinations.
 *
 * @author Viljami Pietarila
 * @version 2019.1217
 */
public class InputController {
    /**
     * List of registered Commands.
     */
    private ArrayList<Command> commands;

    /**
     * InputController constructor.
     */
    public InputController() {
        commands = new ArrayList<>();
    }

    /**
     * receiveInputEvent takes an Input event and calls receiveInput with it.
     *
     * receiveInputEvent is used with buttons and such.
     * @param event InputEvent received.
     */
    public void receiveInputEvent(InputEvent event) {
        receiveInput(event.getType());
    }

    /**
     * registerPlayer registers the player controls.
     *
     * Player element holds large amount of controls. Reference to the player
     * is needed to create link between the controls and the player.
     * The mehtod currently defines the key and mouse controls.
     * @param player Player object
     */
    public void registerPlayer(Player player) {
        commands.add(new Command(
                player, "NW", "HOME", "NUMPAD7", "button:NW"));
        commands.add(new Command(
                player, "N", "UP", "NUMPAD8", "button:N"));
        commands.add(new Command(
                player, "NE", "PAGE_UP", "NUMPAD9", "button:NE"));
        commands.add(new Command(
                player, "E", "RIGHT", "NUMPAD6", "button:E"));
        commands.add(new Command(
                player, "SE", "PAGE_DOWN", "NUMPAD3", "button:SE"));
        commands.add(new Command(
                player, "S", "DOWN", "NUMPAD2", "button:S"));
        commands.add(new Command(
                player, "SW", "END", "NUMPAD1", "button:SW"));
        commands.add(new Command(
                player, "W", "LEFT", "NUMPAD4", "button:W"));
        commands.add(new Command(
                player, "NONE", "CLEAR", "NUMPAD5", "button:NONE"));
    }

    /**
     * registerCamera registers the CameraController's keys
     *
     * registerCamera creates a link between the CameraController and the keys
     * being used to control it.
     * @param camera CameraController of the game.
     */
    public void registerCamera(CameraController camera) {
        commands.add(new Command(camera, "ZOOMIN","PLUS", "ADD"));
        commands.add(new Command(camera, "ZOOMOUT","MINUS", "SUBTRACT"));
    }

    /**
     * receiveInput deals with key being held down.
     * @param input a List of all keys being held down.
     * @param elapsedTime time elapsed since last frame.
     */
    public void receiveInput(List<String> input, double elapsedTime) {
        for (String s : input) receiveInput(s);
    }

    /**
     * receiveInput sorts through the inputs received.
     *
     * receivedInput iterates through the commands and compares the received
     * input to the command parameters and triggers the command if they match.
     * @param input input received.
     */
    public void receiveInput(String input) {
        for (Command c : commands) {
            if (c.getInputs().contains(input)) c.triggerCommand();
        }
    }
}
