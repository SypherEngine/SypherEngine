/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.config;

import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.image.Image;

public class EngineConfig {
    String title;
    int width = 1280, height = 720;

    Image icon;

    float fixedUpdate = 60;
    boolean resizable = false;

    public EngineConfig withTitle(String title) {
        this.title = title;
        return this;
    }

    public EngineConfig withWindowResolution(int width, int height) {
        this.width = width;
        this.height = height;

        return this;
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

    public EngineConfig withFixedUpdate(float fixedUpdate) {
        this.fixedUpdate = fixedUpdate;
        return this;
    }

    @Deprecated
    public EngineConfig isResizable(boolean resizable){
        this.resizable = resizable;
        return this;
    }

    public boolean getResizable(){
        return resizable;
    }

    public float getFixedUpdate() {
        return fixedUpdate;
    }

    public Vector2 getWindowResolution() {
        return new Vector2(width, height);
    }

    public EngineConfig withIcon(Image icon) {
        this.icon = icon;
        return this;
    }

    public Image getIcon() {
        return icon;
    }
}
