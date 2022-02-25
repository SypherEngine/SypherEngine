/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.config;

import dev.aurumbyte.sypherengine.math.Vector2;

public class EngineConfig {
    String title;
    int width = 1280, height = 720;

    float fixedUpdate = 60;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWindowResolution(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public int getScreenHeight() {
        return height;
    }

    public int getScreenWidth() {
        return width;
    }

    public void setFixedUpdate(float fixedUpdate) {
        this.fixedUpdate = fixedUpdate;
    }

    public float getFixedUpdate() {
        return fixedUpdate;
    }

    public Vector2 getWindowResolution() {
        return new Vector2(width, height);
    }
}
