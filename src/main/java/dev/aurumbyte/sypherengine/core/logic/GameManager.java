package dev.aurumbyte.sypherengine.core.logic;

import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;

public abstract class GameManager extends Scene {
    Scene currentScene = this;

    public void gameInit(){
        currentScene.init();
    }

    public void gameUpdate(float deltaTime){
        currentScene.entities.forEach(entity -> {
            if(entity.isDead()) currentScene.entities.remove(entity);
        });

        currentScene.update(deltaTime);
    }

    public void gameRender(Renderer renderer){
        renderer.addEntities(currentScene.entities);
        currentScene.render(renderer);
    }

    public abstract void init();
    public abstract void update(float deltaTime);
    public abstract void render(Renderer renderer);

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}