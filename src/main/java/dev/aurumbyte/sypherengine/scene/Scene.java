package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.scene.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Camera camera;
    private boolean isRunning;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene(){

    }

    public void init(){

    }

    public void start(){
        for(GameObject gameObject : gameObjects){
            gameObject.start();
        }
        isRunning = true;
    }

    public abstract void update(float deltaTime);

    public void adGameObjectToScene(GameObject gameObject){
        if(!isRunning){
            gameObjects.add(gameObject);
        } else {
            gameObjects.add(gameObject);
            gameObject.start();
        }
    }
}
