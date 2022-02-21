package dev.aurumbyte.sypherengine.core.logic;

import dev.aurumbyte.sypherengine.components.camera.Camera2D;
import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.SypherEngine;

public abstract class GameManager extends Scene {
    Scene currentScene = this;
    public Camera2D camera2D = new Camera2D();

    public void gameInit(SypherEngine engine){
        if(currentScene != this) this.init(engine);

        engine.getRenderer().addEntities(currentScene.entities);
        engine.getScene().setCamera(camera2D.getCamera());
        currentScene.init(engine);
    }

    public void gameUpdate(SypherEngine engine) {
        if(currentScene != this) this.update(engine);
        currentScene.update(engine);

        currentScene.entities.forEach(entity -> {
            if(entity.isDead()) currentScene.entities.remove(entity);
            entity.entityUpdate(engine);
        });

        currentScene.renderables.forEach(iRenderable -> iRenderable.update(engine));
    }

    public void gameRender(SypherEngine engine){
        if(currentScene != this) this.render(engine);

        currentScene.render(engine);

        currentScene.entities.forEach(entity -> {
            engine.getRenderer().getGraphicsContext().save();
            engine.getRenderer().transformContext(entity);
            entity.entityRender(engine);
            engine.getRenderer().getGraphicsContext().restore();
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