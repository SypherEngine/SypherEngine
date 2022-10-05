/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.core.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GameManager extends Scene {

    private SypherEngine engine;
    Scene currentScene = this;
    InputHandler inputHandler = new InputHandler(SypherEngine.keyListener, SypherEngine.mouseListener);

    Map<String, Scene> scenes = new HashMap<>();
    boolean sceneSwitched = false;

    public void gameInit(SypherEngine engine) {
        this.init(engine);
        this.engine = engine;
        engine.getScene().setCamera(currentScene.getCamera().getPerspectiveCamera());
    }

    public void gameUpdate(float deltaTime) {
        // If the scene is switched, then we initialize the scene, set the camera,
        // and then continue to update the scene.
        if(sceneSwitched){
            currentScene.init(engine);
            engine.getScene().setCamera(currentScene.getCamera().getPerspectiveCamera());
            sceneSwitched = false;
        }

        currentScene.update(deltaTime);
        currentScene.getCamera().update();

        currentScene.entities.forEach(entity -> {
            if (entity.isDead()) currentScene.entities.remove(entity);
            entity.update(deltaTime);
            /*
            TODO: we need component updates as well
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
        this.sceneSwitched = true;
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
