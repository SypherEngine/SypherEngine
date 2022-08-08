/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.ecs;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.components.Transform;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.IRenderable;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.ecs.component.IComponent;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.geometry.Rectangle2D;

import java.util.List;

/**
 * <p>The base entity class, which all game entities must extend</p>
 * @author AurumByte
 * @since 0.3.0
 * @see IRenderable
 */
public abstract class GameObject implements IRenderable {
    /**
     * Position of the entity
     */
    Transform transform = new Transform();

    int gameObjectID;

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
     * Boundary of the entity
     * @deprecated
     */
    @Deprecated
    Rectangle2D boundary;

    InputHandler inputHandler;

    public GameObject(InputHandler inputHandler) {
        this.gameObjectID = SypherEngine.domain.createEntity();
        this.inputHandler = inputHandler;
    }

    /**
     * <p>The entity's update loop</p>
     * @param deltaTime The deltaTime
     * @since 0.3.0
     */
    public abstract void update(float deltaTime);

    /**
     * <p>The entity's render loop</p>
     * @param renderer the renderer
     * @since 0.3.0
     */
    public abstract void render(Renderer renderer);

    /* **********************************************************
     *                           MISC                           *
     ************************************************************/

    /**
     * <p>Sets the height of the entity</p>
     * @param height The new height of the entity
     * @since 0.3.0
     */
    public void setHeight(float height) {
        this.height = height * transform.getScale();
    }

    /**
     * <p>Sets the width of the entity</p>
     * @param width The new width of the entity
     * @since 0.3.0
     */
    public void setWidth(float width) {
        this.width = width * transform.getScale();
    }

    /**
     * <p>Gets the center of the entity</p>
     * @since 0.3.0
     */
    public Vector2 getCenter() {
        Vector2 pos = transform.getPosition();
        return new Vector2((float) (pos.xPos + width / 2), (float) (pos.yPos + height / 2));
    }

    /**
     * <p>Gets the width of the entity</p>
     * @since 0.3.0
     */
    public double getWidth() {
        return this.width * transform.getScale();
    }

    /**
     * <p>Gets the height of the entity</p>
     * @since 0.3.0
     */
    public double getHeight() {
        return this.height * transform.getScale();
    }

    /**
     * <p>Gets the tag (name) of the entity</p>
     * @since 0.3.0
     */
    public String getTag() {
        return tag;
    }

    public Transform getTransform() {
        return transform;
    }

    /**
     * <p>Adds a component to the entity</p>
     * @param component The component to be added
     * @since 0.3.0
     */
    public <T extends IComponent> void addComponent(T component) {
        try {
            SypherEngine.domain.addComponent(component, gameObjectID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p>Adds multiple components to the entity</p>
     * @param components The components to be added
     * @since 0.3.0
     */
    public <T extends IComponent> void addComponents(T... components) {
        for (IComponent component : components) {
            try {
                SypherEngine.domain.addComponent(component, gameObjectID);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * <p>Adds multiple components to the entity</p>
     * @param components The components to be added
     * @since 0.3.0
     */
    public <T extends IComponent> void addComponents(List<T> components) {
        for (IComponent component : components) {
            try {
                SypherEngine.domain.addComponent(component, gameObjectID);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
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
    public boolean collidesWith(GameObject other) {
        return this.boundary.intersects(other.boundary);
    }

    @Override
    public String toString() {
        return "Entity : " + tag;
    }
}
