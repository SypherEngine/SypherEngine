/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.ecs.Entity;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

/**
 * Axis Aligned Bounding Box collider, also a component that can be added.
 * @author AurumByte
 * @since v0.3.0
 * @see dev.aurumbyte.sypherengine.components.Component
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
    Entity parent;

    /**
     * <p>A new AABB, attached to a parent</p>
     * @param parent The entity to which the AABB acts as a collider
     * @since 0.3.0
     */
    public AABB(Entity parent) {
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.height;
        this.position = parent.getPosition();
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
        this.position = parent.getPosition();
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

    // Just a quick and convenient wrapper over Rectangle2D for collision detection

    /**
     * <p>check if the current entity, over which the AABB acts as a collider, collides with another entity</p>
     * @param other the other entity
     * @since 0.3.0
     */
    public boolean isCollidingWith(Entity other) {
        AABB otherAABB = getAABBComponent(other.getComponents());
        return boundingBox.intersects(otherAABB.boundingBox);
    }

    /**
     * <p>check if the current entity, over which the AABB acts as a collider, is inside another entity</p>
     * @param other the entity, in which the current entity may or may not be inside of
     * @since 0.3.0
     */
    public boolean isInsideOf(Entity other) {
        AABB otherAABB = getAABBComponent(other.getComponents());
        return otherAABB.boundingBox.contains(boundingBox);
    }

    /**
     * <p>check if anything is inside the current entity</p>
     * @param other the entity which may or may not be inside the current entity
     * @since 0.3.0
     */
    public boolean isEntityInside(Entity other) {
        AABB otherAABB = getAABBComponent(other.getComponents());
        return boundingBox.contains(otherAABB.boundingBox);
    }

    /**
     * <p>check if the current x and y coords of the point lies within the boundaries of the current entity</p>
     * @param point the coords to be checked
     * @since 0.3.0
     */
    public boolean isPointInsideOf(Vector2 point) {
        return boundingBox.contains(new Point2D(point.xPos, point.yPos));
    }

    /**
     * <p>Toggle the debug mode of the AABB</p>
     * @param shouldDebug value for the debug field.
     * @since 0.3.0
     */
    public void setIfDebug(boolean shouldDebug) {
        this.shouldDebug = shouldDebug;
    }

    /**
     * <p>Setting the AABB to a specific location, width and height</p>
     * @param position The position of the AABB
     * @param width The width of the AABB
     * @param height The height of the AABB
     * @since 0.3.0
     */
    public void setBoundingBox(Vector2 position, int width, int height) {
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
    }
}
