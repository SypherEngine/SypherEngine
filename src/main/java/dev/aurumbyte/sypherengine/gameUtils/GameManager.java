package dev.aurumbyte.sypherengine.gameUtils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class GameManager<T extends GameManager<T>> {
    public List<GameObject<T>> gameObjects = new ArrayList<>();
    public T gameManager;
    //public Camera<T> camera;

    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(SypherEngine engine, Renderer renderer);


    public List<GameObject<T>> getGameObjects() { return gameObjects; }
    //public Camera<T> getCamera(){ return camera; }
}
