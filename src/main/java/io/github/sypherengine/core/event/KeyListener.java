package io.github.sypherengine.core.event;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;
import java.util.Set;

public class KeyListener {
    private static Scene scene;
    private static final Set<KeyCode> keysDown = new HashSet<>();

    int xDirection = 0;
    int yDirection = 0;

    public static KeyListener getInstance() {
        return new KeyListener();
    }

    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }

    private void clearKeys() {
        keysDown.clear();
    }

    private void removeCurrentKeyHandlers() {
        if (scene != null) {
            KeyListener.scene.setOnKeyPressed(null);
            KeyListener.scene.setOnKeyReleased(null);
        }
    }

    private void setScene(Scene scene) {
        KeyListener.scene = scene;
        KeyListener.scene.setOnKeyPressed((keyEvent -> {
            keysDown.add(keyEvent.getCode());
        }));
        KeyListener.scene.setOnKeyReleased((keyEvent -> {
            keysDown.remove(keyEvent.getCode());
        }));
    }

    public boolean isDown(KeyCode keyCode) {
        return keysDown.contains(keyCode);
    }

    public boolean horizontalAxis(){
        if(isDown(KeyCode.LEFT) || isDown(KeyCode.A)) {
            xDirection = -1;
            return true;
        }

        else if(isDown(KeyCode.RIGHT) || isDown(KeyCode.D)) {
            xDirection = 1;
            return true;
        }

        else return false;
    }

    public boolean verticalAxis(){
        if(isDown(KeyCode.UP) || isDown(KeyCode.W)) {
            yDirection = -1;
            return true;
        }

        else if(isDown(KeyCode.DOWN) || isDown(KeyCode.S)) {
            yDirection = 1;
            return true;
        }

        else return false;
    }

    public int getHorizontalVector() {
        return xDirection;
    }

    public int getVerticalVector() {
        return yDirection;
    }

    @Override
    public String toString() {
        StringBuilder keys = new StringBuilder("KeyPolling on scene (").append(scene).append(")");
        for (KeyCode code : keysDown) {
            keys.append(code.getName()).append(" ");
        }
        return keys.toString();
    }
}
