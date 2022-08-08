/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.components.camera;

import dev.aurumbyte.sypherengine.core.ecs.GameObject;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.PerspectiveCamera;

/**
 * Camera class for 2D camera transforms, currently still experimental
 * @author AurumByte
 * @since v0.3.2
 */
public class Camera2D {
    // still wip

    /**
     * The position of the camera
     */
    Vector2 cameraPosition;

    /**
     * The parent entity that the camera might be attached to
     */
    GameObject parent;

    /**
     * The base camera class of JavaFX, that we're wrapping around to create the camera
     */
    PerspectiveCamera camera = new PerspectiveCamera(false);

    /**
     * <p>Creating a new camera attached to an entity</p>
     * @param parent The entity to which the camera will be attached to
     * @since 0.3.2
     */
    public Camera2D(GameObject parent) {
        this.parent = parent;
        this.cameraPosition = parent.getTransform().getPosition();

        camera.setNearClip(0.1);
        camera.setFarClip(2000);

        this.cameraPosition = parent.getTransform().getPosition();
    }

    /**
     * <p>Creating a new camera, not attched to any entity</p>
     * @since 0.3.2
     */
    public Camera2D() {
        this.cameraPosition = new Vector2();

        camera.setNearClip(0.1);
        camera.setFarClip(2000);

        this.cameraPosition = new Vector2();
    }

    /**
     * <p>A method to attach the camera to an entity</p>
     * @param gameObject the Entity to which the camera will be attched to
     * @since 0.3.2
     */
    public void attachToEntity(GameObject gameObject) {
        this.parent = gameObject;
    }

    /**
     * <p>A method to move the camera to a specific location.</p>
     * @param xPos the new x position of the camera
     * @param yPos the new y position of the camera
     * @since 0.3.2
     */
    public void setCameraPos(float xPos, float yPos) {
        this.cameraPosition = new Vector2(xPos, yPos);
    }

    /**
     * <p>A method to move the camera to a specific location.</p>
     * @param cameraPos the new x and y position of the camera, using the Vector2 class
     * @since 0.3.2
     */
    public void setCameraPos(Vector2 cameraPos) {
        this.cameraPosition = cameraPos;
    }

    /**
     * <p>Getting the base camera to attach to the scene.</p>
     * @since 0.3.2
     */
    public PerspectiveCamera getPerspectiveCamera() {
        return camera;
    }

    /**
     * <p>Updating the camera's position.</p>
     * @since 0.3.2
     */
    public void update() {
        camera.translateXProperty().setValue(cameraPosition.xPos);
        camera.translateYProperty().setValue(cameraPosition.yPos);
    }

    public Vector2 getCameraPosition() {
        return cameraPosition;
    }
}
