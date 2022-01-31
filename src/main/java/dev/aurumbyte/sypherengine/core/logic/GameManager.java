package dev.aurumbyte.sypherengine.core.logic;

import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.SypherEngine;

public abstract class GameManager extends Scene {
    Scene currentScene = this;

    public void gameInit(SypherEngine engine){
        engine.getRenderer().addEntities(currentScene.entities);
        currentScene.init(engine);
    }

    public void gameUpdate(SypherEngine engine, float deltaTime){
        currentScene.entities.forEach(entity -> {
            if(entity.isDead()) currentScene.entities.remove(entity);
            entity.update(engine, deltaTime);
        });

        currentScene.update(engine, deltaTime);
    }

    public void gameRender(Renderer renderer){
        currentScene.entities.forEach(entity -> {
            renderer.transformContext(entity);
            entity.render(renderer);
        });

        currentScene.render(renderer);
    }

    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(Renderer renderer);

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}