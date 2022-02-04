package dev.aurumbyte.sypherengine.core.logic;

import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.SypherEngine;

public abstract class GameManager extends Scene {
    Scene currentScene = this;

    public void gameInit(SypherEngine engine){
        engine.getRenderer().addEntities(currentScene.entities);
        currentScene.init(engine);
    }

    public void gameUpdate(SypherEngine engine) {
        currentScene.update(engine);

        currentScene.entities.forEach(entity -> {
            if(entity.isDead()) currentScene.entities.remove(entity);
            entity.update(engine);
        });

        currentScene.renderables.forEach(iRenderable -> iRenderable.update(engine));
    }

    public void gameRender(SypherEngine engine){
        currentScene.render(engine);

        currentScene.entities.forEach(entity -> {
            engine.getRenderer().transformContext(entity);
            entity.render(engine);
        });

        currentScene.renderables.forEach(iRenderable -> iRenderable.render(engine));
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
}