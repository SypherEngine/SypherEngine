package dev.aurumbyte.sypherengine.gameUtils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.camera.Camera;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public abstract class GameManager<T extends GameManager<T>> extends Scene {
    public List<GameObject<T>> gameObjects = new ArrayList<>();
    public T gameManager;
    public Scene currentScene;
    public Camera<T> camera;
    public Image level;
    public static int defaultTileSize = 16;

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
    public Camera<T> getCamera(){ return camera; }

    public GameObject<T> getObject(String tag){
        for(GameObject<T> object : gameObjects){
            if(object.getTag().equals(tag)) return object;
        }

        return null;
    }

    public Image getLevel() {
        return level;
    }

    public int getLevelWidth(){
        return level.getWidth();
    }

    public int getLevelHeight(){
        return level.getHeight();
    }

    public void setDefaultTileSize(int defaultTileSize) {
        this.defaultTileSize = defaultTileSize;
    }
}
