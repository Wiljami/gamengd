package fi.tuni.tiko.gamengd.controller.input;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Command is an object containing a trigger and the triggering inputs.
 *
 * Command holds List of inputs that trigger it. It holds the target of the
 * command and a message for the target.
 */
public class Command {
    /**
     * List of inputs. They can refer to keys or buttons.
     */
    private ArrayList<String> inputs;
    /**
     * target of the commands trigger.
     */
    private CommandTarget target;
    /**
     * message of this command to the target
     */
    private String message;

    /**
     * Command constructor
     * @param target CommandTarget target of the command
     * @param message String message for the target
     * @param inputs Array of inputs that trigger this command
     */
    public Command(CommandTarget target, String message, String ... inputs) {
        this.inputs = new ArrayList<>();
        this.inputs.addAll(Arrays.asList(inputs));
        setTarget(target);
        setMessage(message);
    }

    /**
     * triggerCommand method triggers the target's receiveCommand.
     */
    public void triggerCommand() {
        target.receiveCommand(message);
    }

    /**
     * getter for inputs
     * @return List of inputs
     */
    public ArrayList<String> getInputs() {
        return inputs;
    }

    /**
     * setter for inputs
     * @param inputs new List of inputs
     */
    public void setInputs(ArrayList<String> inputs) {
        this.inputs = inputs;
    }

    /**
     * getter for target.
     * @return CommandTarget target
     */
    public CommandTarget getTarget() {
        return target;
    }

    /**
     * setter for target.
     * @param target new CommandTarget.
     */
    public void setTarget(CommandTarget target) {
        this.target = target;
    }

    /**
     * getter for the message.
     * @return String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * setter for the message
     * @param message new message String
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
