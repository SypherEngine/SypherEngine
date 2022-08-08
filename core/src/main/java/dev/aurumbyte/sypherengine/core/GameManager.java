/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.core.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameManager extends Scene {

    Scene currentScene = this;
    InputHandler inputHandler = new InputHandler(SypherEngine.keyListener, SypherEngine.mouseListener);

    Map<String, Scene> scenes = new HashMap<>();

    public void gameInit(SypherEngine engine) {
        this.init(engine);

        engine.getScene().setCamera(currentScene.getCamera().getPerspectiveCamera());

        if (currentScene != this) currentScene.init(engine);
    }

    public void gameUpdate(float deltaTime) {
        if (currentScene != this) this.update(deltaTime);
        currentScene.update(deltaTime);
        currentScene.getCamera().update();

        currentScene.entities.forEach(entity -> {
            if (entity.isDead()) currentScene.entities.remove(entity);
            entity.update(deltaTime);
            /*
            entity.getComponents().forEach(component -> {
                component.update(deltaTime);
            });

              */
        });
    }

    public void gameRender(Renderer renderer) {
        if(getAmbientLight() != null) renderer.drawAmbientLight(this.getAmbientLight());
        currentScene.render(renderer);

        currentScene.entities.forEach(entity -> {
            renderer.getGraphicsContext().save();
            renderer.transformContext(entity);
            entity.render(renderer);
            /*
            entity.getComponents().forEach(component -> {
                component.render(engine);
            });

             */
            renderer.getGraphicsContext().restore();
        });
    }

    public void dispose() {
        // nothing by default, can be overridden
    }

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

    /*
    public KeyListener getKeyListener() {
        return keyListener;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

     */

    public InputHandler getInputHandler() {
        return inputHandler;
    }
}
