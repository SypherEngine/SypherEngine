/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.event;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;

import java.util.HashSet;
import java.util.Set;

/**
 * The class that polls for mouse listening events
 * @author AurumByte
 * @since v0.3.0
 */
public class MouseListener {
    /**
     * mouse position
     */
    private int mouseX, mouseY;

    /**
     * The scene to be polled for mouse events
     */
    private Scene scene;

    /**
     * the set containing mouse buttons down
     */
    private static final Set<MouseButton> buttonsDown = new HashSet<>();

    /**
     * <p>Method for creating a MouseListener singleton</p>
     * @since 0.3.0
     */
    public static MouseListener getInstance() {
        return new MouseListener();
    }

    /**
     * <p>Polling the scene</p>
     * @param scene Scene to be polled
     * @since 0.3.0
     */
    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    /**
     * <p>Clears {@code buttonsDown} of all values</p>
     * @since 0.3.0
     */
    private void clearKeys() {
        buttonsDown.clear();
    }

    /**
     * <p>Stops the addition of more values to {@code buttonsDown}</p>
     * @since 0.3.0
     */
    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            this.scene.setOnMouseMoved(null);
            this.scene.setOnMouseClicked(null);
        }
    }

    /**
     * <p>Resets the scene after keyHandlers are removed</p>
     * @param scene Scene to be polled
     * @since 0.3.0
     */
    private void setScene(Scene scene) {
        this.scene = scene;
        this.scene.setOnMousePressed((mouseEvent -> {
            buttonsDown.add(mouseEvent.getButton());
        }));

        this.scene.setOnMouseReleased((mouseEvent -> {
            buttonsDown.remove(mouseEvent.getButton());
        }));

        this.scene.setOnMouseMoved((mouseEvent -> {
            mouseX = (int) mouseEvent.getX();
            mouseY = (int) mouseEvent.getY();
        }));
    }

    /**
     * <p>Gets the mouse position</p>
     * @since 0.3.0
     */
    public Point2D getMousePos() {
        return new Point2D(mouseX, mouseY);
    }

    /**
     * <p>Gets the mouse position (x coord)</p>
     * @since 0.3.0
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * <p>Gets the mouse position (y coord)</p>
     * @since 0.3.0
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * <p>Checks whether a given mouse button is being pressed or not</p>
     * @param button The button to be checked
     * @since 0.3.0
     */
    public boolean isDown(MouseButton button) {
        return buttonsDown.contains(button);
    }

    @Override
    public String toString() {
        StringBuilder buttons =
                new StringBuilder("MouseListener on scene (").append(scene).append(")");
        for (MouseButton button : buttonsDown) {
            buttons.append(button.toString()).append(" ");
        }
        return buttons.toString();
    }
}
