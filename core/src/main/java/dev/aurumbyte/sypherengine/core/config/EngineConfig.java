/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.config;

import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.image.Image;

public class EngineConfig {
    String title;
    int width = 1280, height = 720;

    Image icon;

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

    public void setIcon(Image icon) {
        this.icon = icon;
    }

    public Image getIcon() {
        return icon;
    }
}
