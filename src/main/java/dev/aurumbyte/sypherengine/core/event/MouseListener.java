package dev.aurumbyte.sypherengine.core.event;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;

import java.util.HashSet;
import java.util.Set;

public class MouseListener {
    private int mouseX, mouseY;
    private static Scene scene;
    private static final Set<MouseButton> buttonsDown = new HashSet<>();

    private MouseListener() {
    }

    public static MouseListener getInstance() {
        return new MouseListener();
    }

    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    private void clearKeys() {
        buttonsDown.clear();
    }

    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            MouseListener.scene.setOnMouseMoved(null);
            MouseListener.scene.setOnMouseClicked(null);
        }
    }

    private void setScene(Scene scene) {
        MouseListener.scene = scene;
        MouseListener.scene.setOnMousePressed((mouseEvent -> {
            buttonsDown.add(mouseEvent.getButton());
        }));
        MouseListener.scene.setOnMouseReleased((mouseEvent -> {
            buttonsDown.remove(mouseEvent.getButton());
        }));
        MouseListener.scene.setOnMouseMoved((mouseEvent -> {
            mouseX = (int)mouseEvent.getX();
            mouseY = (int)mouseEvent.getY();
        }));
    }

    public Point2D getMousePos(){
        return new Point2D(mouseX, mouseY);
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isDown(MouseButton button) {
        return buttonsDown.contains(button);
    }

    @Override
    public String toString() {
        StringBuilder buttons = new StringBuilder("MouseListener on scene (").append(scene).append(")");
        for (MouseButton button : buttonsDown) {
            buttons.append(button.toString()).append(" ");
        }
        return buttons.toString();
    }
}
