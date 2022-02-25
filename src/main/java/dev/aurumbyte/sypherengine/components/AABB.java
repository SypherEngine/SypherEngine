/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class AABB extends Component {
    public Vector2 position;
    int width, height;
    boolean isDebug = false;
    public Rectangle2D boundingBox;
    Entity parent;

    boolean shouldDebug = false;

    public AABB(Entity parent) {
        this.parent = parent;
        this.width = (int) parent.width;
        this.height = (int) parent.height;
        this.position = parent.getDrawPosition();
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);

        this.componentTag = "AABB - " + parent.toString();
    }

    @Override
    public void update(SypherEngine engine) {
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
        this.position = parent.getDrawPosition();
    }

    @Override
    public void render(SypherEngine engine) {
        if (shouldDebug) engine.getRenderer().drawRectangle(position, width, height, false, Color.BLACK);
    }

    // Just a quick and convenient wrapper over Rectangle2D for collision detection

    public boolean isCollidingWith(Entity other) {
        AABB otherAABB = getAABBComponent(other.components);
        return boundingBox.intersects(otherAABB.boundingBox);
    }

    public boolean isInsideOf(Entity other) {
        AABB otherAABB = getAABBComponent(other.components);
        return otherAABB.boundingBox.contains(boundingBox);
    }

    public boolean isEntityInside(Entity other) {
        AABB otherAABB = getAABBComponent(other.components);
        return boundingBox.contains(otherAABB.boundingBox);
    }

    public boolean isPointInsideOf(Vector2 point) {
        return boundingBox.contains(new Point2D(point.xPos, point.yPos));
    }

    public void setIfDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    public void setBoundingBox(Vector2 position, int width, int height) {
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
    }

    private AABB getAABBComponent(List<Component> components) {
        AABB aabb = null;
        for (Component component : components) {
            if (component instanceof AABB) {
                aabb = (AABB) component;
                break;
            }
        }
        return aabb;
    }
}
