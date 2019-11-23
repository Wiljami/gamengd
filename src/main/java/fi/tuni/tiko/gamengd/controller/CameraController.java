package fi.tuni.tiko.gamengd.controller;

import fi.tuni.tiko.gamengd.InputListener;
import javafx.scene.canvas.Canvas;

import java.util.List;

public class CameraController implements InputListener {
    private double x;
    private double y;
    boolean cameraChanged;
    private double tileSize = 100;
    private Canvas canvas;

    public CameraController(Canvas canvas) {
        this.canvas = canvas;
    }

    public boolean isCameraChanged() {
        return cameraChanged;
    }

    public void setX(double x) {
        this.x = x;
        setCameraChanged(true);
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
        setCameraChanged(true);
    }

    public void setXY(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getY() {
        return y;
    }

    public void setTileSize(double tileSize) {
        if (tileSize < 10) tileSize = 10;
        if (tileSize > 100) tileSize = 100;
        this.tileSize = tileSize;
        setCameraChanged(true);
    }

    public double getTileSize() {
        return tileSize;
    }

    //TODO: Smooth out the zoom with some sort of algorithm. Currently it zooms too fast once close.
    private void zoomIn() {
        setTileSize(getTileSize()+1);
    }

    private void zoomOut() {
        setTileSize(getTileSize()-1);
    }

    public void setCameraChanged(boolean cameraChanged) {
        this.cameraChanged = cameraChanged;
    }

    @Override
    public void receiveInput(List<String> input, double elapsedTime) {
        if (input.contains("ADD") || input.contains(("PLUS"))) {
            zoomIn();
        } else if (input.contains("SUBTRACT") || input.contains("MINUS")) {
            zoomOut();
        }
    }

    @Override
    public void receiveInput(String input) {
    }
}