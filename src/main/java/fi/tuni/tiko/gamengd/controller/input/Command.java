package fi.tuni.tiko.gamengd.controller.input;

import java.util.ArrayList;
import java.util.Arrays;

public class Command {
    private ArrayList<String> inputs;
    private CommandTarget target;
    private String message;

    public Command(CommandTarget target, String message, String ... inputs) {
        this.inputs = new ArrayList<>();
        this.inputs.addAll(Arrays.asList(inputs));
        setTarget(target);
        setMessage(message);
    }

    public void triggerCommand() {
        target.receiveCommand(message);
    }

    public ArrayList<String> getInputs() {
        return inputs;
    }

    public void setInputs(ArrayList<String> inputs) {
        this.inputs = inputs;
    }

    public CommandTarget getTarget() {
        return target;
    }

    public void setTarget(CommandTarget target) {
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}