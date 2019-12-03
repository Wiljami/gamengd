package fi.tuni.tiko.gamengd.controller.input;

import fi.tuni.tiko.gamengd.controller.CameraController;
import fi.tuni.tiko.gamengd.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InputController {
    private ArrayList<Command> commands;

    public InputController() {
        commands = new ArrayList<>();
    }

    public void receiveInputEvent(InputEvent event) {
        receiveInput(event.getType());
    }

    public void registerPlayer(Player player) {
        commands.add(new Command(player, "NW", "HOME", "NUMPAD7", "button:NW"));
        commands.add(new Command(player, "N", "UP", "NUMPAD8", "button:N"));
        commands.add(new Command(player, "NE", "PAGE_UP", "NUMPAD9", "button:NE"));
        commands.add(new Command(player, "E", "RIGHT", "NUMPAD6", "button:E"));
        commands.add(new Command(player, "SE", "PAGE_DOWN", "NUMPAD3", "button:SE"));
        commands.add(new Command(player, "S", "DOWN", "NUMPAD2", "button:S"));
        commands.add(new Command(player, "SW", "END", "NUMPAD1", "button:SW"));
        commands.add(new Command(player, "W", "LEFT", "NUMPAD4", "button:W"));
        commands.add(new Command(player, "NONE", "CLEAR", "NUMPAD5", "button:NONE"));
    }

    public void registerCamera(CameraController camera) {
        commands.add(new Command(camera, "ZOOMIN","PLUS", "ADD"));
        commands.add(new Command(camera, "ZOOMOUT","MINUS", "SUBTRACT"));
    }

    public void receiveInput(List<String> input, double elapsedTime) {
        for (String s : input) receiveInput(s);
    }

    public void receiveInput(String input) {
        for (Command c : commands) {
            if (c.getInputs().contains(input)) c.triggerCommand();
        }
    }
}