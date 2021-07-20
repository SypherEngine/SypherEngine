package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.game.Transform;
import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.graphics.renderer.components.SpriteRenderer;
import dev.aurumbyte.sypherengine.scene.camera.Camera;
import dev.aurumbyte.sypherengine.utils.AssetPool;
import org.joml.Vector2f;

public class LevelEditorScene extends Scene{
    public LevelEditorScene(){

    }

    @Override
    public void init(){
        this.camera = new Camera(new Vector2f());

        GameObject gameObject = new GameObject("Object 1", new Transform(new Vector2f(100, 100), new Vector2f(256, 256)));
        gameObject.addComponent(new SpriteRenderer(AssetPool.getTexture2D("/resources/input.png")));
        this.addGameObjectToScene(gameObject);

        System.out.println("Creating obj2");

        GameObject gameObject2 = new GameObject("Object 2", new Transform(new Vector2f(400, 100), new Vector2f(256, 256)));
        gameObject2.addComponent(new SpriteRenderer(AssetPool.getTexture2D("assets/test.png")));
        this.addGameObjectToScene(gameObject2);

        loadResources();
    }

    private void loadResources(){
        AssetPool.getShader("assets/shaders/default.glsl");
    }

    @Override
    public void update(float deltaTime) {
        gameObjects.forEach(gameObject -> gameObject.update(deltaTime));
        this.renderer.render();
    }
}
