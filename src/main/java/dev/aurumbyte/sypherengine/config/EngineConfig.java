package dev.aurumbyte.sypherengine.config;

public class EngineConfig {
    String title;
    int width = 1280, height = 720;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setScreenResolution(int width, int height){
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
}
