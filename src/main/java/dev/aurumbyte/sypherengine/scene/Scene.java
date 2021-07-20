package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.graphics.renderer.Renderer;
import dev.aurumbyte.sypherengine.scene.camera.Camera;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    protected Camera camera;
    protected Renderer renderer = new Renderer();
    private boolean isRunning;
    protected List<GameObject> gameObjects = new ArrayList<>();

    public Scene(){

    }

    public void init(){

    }

    public void start(){
        /*
        for(GameObject gameObject : gameObjects){
            gameObject.start();
            this.renderer.add(gameObject);
        }
         */

        gameObjects.forEach(gameObject -> {
            gameObject.start();
            this.renderer.add(gameObject);
        });

        isRunning = true;
    }

    public abstract void update(float deltaTime);

    public void addGameObjectToScene(GameObject gameObject){
        if(!isRunning){
            gameObjects.add(gameObject);
        } else {
            gameObjects.add(gameObject);
            gameObject.start();
            this.renderer.add(gameObject);
        }
    }

    public Camera camera(){
        return this.camera;
    }
}
