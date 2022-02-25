/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.logic;

import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameManager extends Scene {
    Scene currentScene = this;

    Map<String, Scene> scenes = new HashMap<>();

    public void gameInit(SypherEngine engine) {
        if (currentScene != this) this.init(engine);

        engine.getRenderer().addEntities(currentScene.entities);
        engine.getScene().setCamera(currentScene.getCamera().getParallelCamera());
        currentScene.init(engine);
    }

    public void gameUpdate(SypherEngine engine) {
        if (currentScene != this) this.update(engine);
        currentScene.update(engine);

        currentScene.entities.forEach(entity -> {
            if (entity.isDead()) currentScene.entities.remove(entity);
            entity.update(engine);

            entity.getComponents().forEach(component -> {
                component.update(engine);
            });
        });
    }

    public void gameRender(SypherEngine engine) {
        if (currentScene != this) this.render(engine);

        currentScene.render(engine);

        currentScene.entities.forEach(entity -> {
            engine.getRenderer().getGraphicsContext().save();
            engine.getRenderer().transformContext(entity);
            entity.render(engine);
            entity.getComponents().forEach(component -> {
                component.render(engine);
            });
            engine.getRenderer().getGraphicsContext().restore();
        });
    }

    public abstract void init(SypherEngine engine);

    public abstract void update(SypherEngine engine);

    public abstract void render(SypherEngine engine);

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void addScene(Scene scene) {
        scenes.put(scene.getSceneName(), scene);
    }

    public void addScenes(Scene... scenes) {
        for (Scene scene : scenes) {
            addScene(scene);
        }
    }

    public void addScenes(List<Scene> sceneList) {
        for (Scene scene : sceneList) {
            addScene(scene);
        }
    }

    public void getScene(String sceneName) {
        scenes.get(sceneName);
    }
}
