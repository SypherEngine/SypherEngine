/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.ecs.GameObject;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

/**
 * Axis Aligned Bounding Box collider, also a component that can be added.
 * @author AurumByte
 * @since v0.3.0
 * @see Component
 */
public class AABB extends Component {
    /**
     * Position of the AABB
     */
    public Vector2 position;

    /**
     * Width and height of the AABB
     */
    float width, height;

    /**
     * Field to show whether the AABB is in debug mode or not
     */
    boolean shouldDebug = false;

    /**
     * The base JavaFX class, the core of the AABB functionality
     */
    public Rectangle2D boundingBox;

    /**
     * The parent entity of the AABB
     */
    GameObject parent;

    /**
     * <p>A new AABB, attached to a parent</p>
     * @param parent The entity to which the AABB acts as a collider
     * @since 0.3.0
     */
    public AABB(GameObject parent) {
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.height;
        this.position = parent.getTransform().getPosition();
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);

        this.componentTag = "AABB - " + parent;
    }

    /**
     * <p>A new AABB, with a specified location, width and height</p>
     * @param position The position of the AABB
     * @param width The width of the AABB
     * @param height The height of the AABB
     * @since 0.3.0
     */
    public AABB(Vector2 position, float width, float height) {
        this.width = width;
        this.height = height;
        this.position = position;
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);

        this.componentTag = "AABB";
    }

    /**
     * <p>Updating the AABB</p>
     * @param deltaTime DeltaTime, the duration taken to update a frame
     * @since 0.3.0
     */
    @Override
    public void update(float deltaTime) {
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
        this.position = parent.getTransform().getPosition();
    }

    /**
     * <p>Render the AABB borders, if the AABB is in debug mode</p>
     * @param engine the base engine, which runs the game
     * @since 0.3.0
     */
    @Override
    public void render(SypherEngine engine) {
        if (shouldDebug) engine.getRenderer().drawRectangle(position, width, height, false, Color.BLACK);
    }
}
