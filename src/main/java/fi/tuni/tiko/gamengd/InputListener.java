package fi.tuni.tiko.gamengd;

import java.util.List;

public interface InputListener {
    void receiveInput(List<String> input, double elapsedTime);
    void receiveInput(String input);
}