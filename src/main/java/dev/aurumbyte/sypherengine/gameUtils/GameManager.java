package dev.aurumbyte.sypherengine.gameUtils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public abstract class GameManager<T extends GameManager<T>> extends Scene {
    public List<GameObject<T>> gameObjects = new ArrayList<>();
    public T gameManager;
    public Scene currentScene;
    //public Camera<T> camera;

    public void gameInit(SypherEngine engine){
        if(currentScene == null){
            currentScene = this;
        }

        currentScene.init(engine);
    }
    public void gameUpdate(SypherEngine engine, float deltaTime){
        currentScene.update(engine, deltaTime);
    }
    public void gameRender(SypherEngine engine, Renderer renderer){
        currentScene.render(engine, renderer);
    }


    public List<GameObject<T>> getGameObjects() { return gameObjects; }

    public void setCurrentScene(Scene scene){
        this.currentScene = scene;
    }
    //public Camera<T> getCamera(){ return camera; }
}
