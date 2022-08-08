/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.event;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

/**
 * The class that polls for key events
 * @author AurumByte
 * @since v0.3.0
 */
public class KeyListener {
    /**
     * The scene polled for key events
     */
    private static Scene scene;

    /**
     * The keyset, containing keys pressed
     */
    private static final Set<KeyCode> keysDown = new HashSet<>();

    /**
     * the vector direction value for x (for the {@code horizontalAxis} method)
     */
    int xDirection = 0;

    /**
     * the vector direction value for y (for the {@code verticalAxis} method)
     */
    int yDirection = 0;

    /**
     * <p>Method for creating a KeyListener singleton</p>
     * @since 0.3.0
     */
    public static KeyListener getInstance() {
        return new KeyListener();
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
     * <p>Clears {@code keysDown} of all values</p>
     * @since 0.3.0
     */
    private void clearKeys() {
        keysDown.clear();
    }

    /**
     * <p>Stops the addition of more values to {@code keysDown}</p>
     * @since 0.3.0
     */
    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            KeyListener.scene.setOnKeyPressed(null);
            KeyListener.scene.setOnKeyReleased(null);
        }
    }

    /**
     * <p>Resets the scene after keyHandlers are removed</p>
     * @param scene Scene to be polled
     * @since 0.3.0
     */
    private void setScene(Scene scene) {
        KeyListener.scene = scene;
        KeyListener.scene.setOnKeyPressed((keyEvent -> {
            keysDown.add(keyEvent.getCode());
        }));
        KeyListener.scene.setOnKeyReleased((keyEvent -> {
            keysDown.remove(keyEvent.getCode());
        }));
    }

    /**
     * <p>Checks whether a given Key is being pressed or not</p>
     * @param keyCode The Keycode to be checked
     * @since 0.3.0
     */
    public boolean isDown(KeyCode keyCode) {
        return keysDown.contains(keyCode);
    }

    /**
     * <p>Keyhandling for the left and right directions, like Unity's {@code horizontalAxis}</p>
     * @since 0.3.0
     */
    public boolean horizontalAxis() {
        if (isDown(KeyCode.LEFT) || isDown(KeyCode.A)) {
            xDirection = -1;
            return true;
        } else if (isDown(KeyCode.RIGHT) || isDown(KeyCode.D)) {
            xDirection = 1;
            return true;
        } else return false;
    }

    /**
     * <p>Keyhandling for the up and down directions, like Unity's {@code vertcialAxis}</p>
     * @since 0.3.0
     */
    public boolean verticalAxis() {
        if (isDown(KeyCode.UP) || isDown(KeyCode.W)) {
            yDirection = -1;
            return true;
        } else if (isDown(KeyCode.DOWN) || isDown(KeyCode.S)) {
            yDirection = 1;
            return true;
        } else return false;
    }

    /**
     * <p>Gets the {@code horizontalDirection}</p>
     * @since 0.3.0
     */
    public int getHorizontalVector() {
        return xDirection;
    }

    /**
     * <p>Gets the {@code verticalDirection}</p>
     * @since 0.3.0
     */
    public int getVerticalVector() {
        return yDirection;
    }

    @Override
    public String toString() {
        StringBuilder keys =
                new StringBuilder("KeyPolling on scene (").append(scene).append(")");
        for (KeyCode code : keysDown) {
            keys.append(code.getName()).append(" ");
        }
        return keys.toString();
    }
}
