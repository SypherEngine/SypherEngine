/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.components.scene;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.components.camera.Camera2D;
import dev.aurumbyte.sypherengine.core.ecs.GameObject;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import javafx.scene.AmbientLight;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * The base scene class, used throughout...
 * @author AurumByte
 * @since v0.3.0
 */
public abstract class Scene {
    /**
     * The name of the scene, makes it easier for scene switching and management.
     */
    String sceneName = "Scene";

    /**
     * The entities present in the scene.
     */
    public List<GameObject> entities = new ArrayList<>();

    // TODO: Make this a map to render other entities to
    public List<GameObject> renderOnlyEntities = new ArrayList<>();

    /**
     * The scene camera, there's one present for each scene, for custom scene camera transformations.
     */
    Camera2D camera = new Camera2D();

    AmbientLight ambientLight;

    /**
     * <p>The abstract initialization method, to be overridden by the user</p>
     * @param engine The main engine, used for initializing the game
     * @since 0.3.0
     */
    public abstract void init(SypherEngine engine);

    /**
     * <p>The abstract update method, to be overridden by the user</p>
     * @param deltaTime The deltaTime of the game, kinda useless as the update rates are fixed, but nice to have just in case
     * @since 0.3.0
     */
    public abstract void update(float deltaTime);

    /**
     * <p>The abstract render method, to be overridden by the user</p>
     * @param renderer The renderer, used for rendering purposes the game
     * @since 0.3.0
     */
    public abstract void render(Renderer renderer);

    /**
     * <p>Getting the scene's name.</p>
     * @since 0.3.0
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * <p>Setting a new name for a scene</p>
     * @param sceneName The new scene name
     * @since 0.3.0
     */
    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * <p>Getting the scene's camera.</p>
     * @since 0.3.0
     */
    public Camera2D getCamera() {
        return camera;
    }

    /**
     * <p>Setting the scene's camera.</p>
     * @param camera The new scene camera.
     * @since 0.3.0
     */
    public void setCamera(Camera2D camera) {
        this.camera = camera;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(Color color) {
        this.ambientLight = new AmbientLight();
        this.ambientLight.setColor(color);
    }

    public void switchAmbientLightProperty(boolean shouldAmbientLightBeOn){
        this.ambientLight.setLightOn(shouldAmbientLightBeOn);
    }
}
