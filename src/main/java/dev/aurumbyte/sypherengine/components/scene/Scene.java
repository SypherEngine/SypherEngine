/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components.scene;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.components.camera.Camera2D;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    String sceneName = "Scene";
    public List<Entity> entities = new ArrayList<>();
    Camera2D camera = new Camera2D();

    public abstract void init(SypherEngine engine);

    public abstract void update(SypherEngine engine);

    public abstract void render(SypherEngine engine);

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public Camera2D getCamera() {
        return camera;
    }

    public void setCamera(Camera2D camera) {
        this.camera = camera;
    }
}
