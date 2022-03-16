/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs;

import dev.aurumbyte.sypherengine.components.Component;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.graphics.IRenderable;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import dev.aurumbyte.sypherengine.math.Vector2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.geometry.Rectangle2D;

/**
 * <p>The base entity class, which all game entities must extend</p>
 * @author AurumByte
 * @since 0.3.0
 * @see dev.aurumbyte.sypherengine.core.graphics.IRenderable
 */
public abstract class Entity implements IRenderable {
    /**
     * The KeyListener for the current entity, which is an instance passed by the gamemanager
     */
    public KeyListener keyListener = GameManager.getKeyListener();

    /**
     * The MouseListener for the current entity, which is an instance passed by the gamemanager
     */
    public MouseListener mouseListener = GameManager.getMouseListener();

    /**
     * Position of the entity
     */
    Vector2 position = new Vector2();

    /**
     * Rotation of the entity
     */
    public float rotation;

    /**
     * Scale of the entity
     */
    float scale = 1;

    /**
     * width of the entity
     */
    public float width;

    /**
     * height of the entity
     */
    public float height;

    /**
     * Whether the entity is no longer needed or not
     */
    boolean isDead = false;

    /**
     * Entity tag, to help identify multiple entities from one another
     */
    String tag;

    /**
     * The components of each entity
     */
    List<Component> components = new ArrayList<>();

    /**
     * Boundary of the entity
     * @deprecated
     */
    @Deprecated
    Rectangle2D boundary;

    /**
     * <p>The entity's update loop</p>
     * @param deltaTime The deltaTime
     * @since 0.3.0
     */
    public abstract void update(float deltaTime);

    /**
     * <p>The entity's render loop</p>
     * @param engine the main engine
     * @since 0.3.0
     */
    public abstract void render(SypherEngine engine);

    /* **********************************************************
     *                           MISC                           *
     ************************************************************/

    /**
     * <p>Gets the position of the entity (where its drawn)</p>
     * @since 0.3.0
     */
    public Vector2 getPosition() {
        return position;
    }

    /**
     * <p>Sets the position of the entity (where its drawn)</p>
     * @param xPos The position of the entity (x coord)
     * @param yPos The position of the entity (y coord)
     * @since 0.3.0
     */
    public void setPosition(float xPos, float yPos) {
        this.position.setPosition(xPos, yPos);
    }

    /**
     * <p>Sets the height of the entity</p>
     * @param height The new height of the entity
     * @since 0.3.0
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * <p>Sets the width of the entity</p>
     * @param width The new width of the entity
     * @since 0.3.0
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * <p>Sets the scale of the entity</p>
     * @param scale The new scale of the entity
     * @since 0.3.0
     */
    public void setScale(float scale) {
        this.scale = scale;
    }

    /**
     * <p>Sets the rotation of the entity (rotates the entity)</p>
     * @param rotation The value by which the object should be rotated
     * @since 0.3.0
     */
    public void rotate(float rotation) {
        this.position.rotate(rotation);
    }

    /**
     * <p>Gets the rotation of the entity</p>
     * @since 0.3.0
     */
    public float getRotation() {
        return this.position.getRotation();
    }

    /**
     * <p>Gets the scale of the entity</p>
     * @since 0.3.0
     */
    public float getScale() {
        return scale;
    }

    /**
     * <p>Gets the center of the entity</p>
     * @since 0.3.0
     */
    public Vector2 getCenter() {
        Vector2 pos = getPosition();
        return new Vector2((float) (pos.xPos + width / 2), (float) (pos.yPos + height / 2));
    }

    /**
     * <p>Gets the width of the entity</p>
     * @since 0.3.0
     */
    public double getWidth() {
        return this.width * getScale();
    }

    /**
     * <p>Gets the height of the entity</p>
     * @since 0.3.0
     */
    public double getHeight() {
        return this.height * getScale();
    }

    /**
     * <p>Gets the tag (name) of the entity</p>
     * @since 0.3.0
     */
    public String getTag() {
        return tag;
    }

    /**
     * <p>Gets the components of the entity</p>
     * @since 0.3.0
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * <p>Adds a component to the entity</p>
     * @param component The component to be added
     * @since 0.3.0
     */
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * <p>Adds multiple components to the entity</p>
     * @param components The components to be added
     * @since 0.3.0
     */
    public void addComponents(Component... components) {
        Collections.addAll(this.components, components);
    }

    /**
     * <p>Adds multiple components to the entity</p>
     * @param components The components to be added
     * @since 0.3.0
     */
    public void addComponents(List<Component> components) {
        this.components.addAll(components);
    }

    /**
     * <p>Return if the entity is no longer used or not</p>
     * @since 0.3.0
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * <p>Set whether the entity is being used or not</p>
     * @param dead Toggle whether the entity is used or not
     * @since 0.3.0
     */
    public void setIsDead(boolean dead) {
        isDead = dead;
    }

    @Deprecated
    public void setBoundary(Rectangle2D boundary) {
        this.boundary = boundary;
    }

    @Deprecated
    public void setBoundary(int xPos, int yPos, int width, int height) {
        this.boundary = new Rectangle2D(xPos, yPos, width, height);
    }

    /**
     * <p>Sets the entity's tag</p>
     * @param tag The new tag of the entity
     * @since 0.3.0
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Deprecated
    public boolean collidesWith(Entity other) {
        return this.boundary.intersects(other.boundary);
    }

    @Override
    public String toString() {
        return "Entity : " + tag + "\nComponent Info:\n No. of components : " + components.size();
    }
}
