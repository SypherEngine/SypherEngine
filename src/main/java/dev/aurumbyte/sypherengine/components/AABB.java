package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class AABB {
    public Vector2 position;
    int width, height;
    boolean isDebug = false;
    public Rectangle2D boundingBox;
    Entity parent;

    public AABB(Entity parent){
        this.parent = parent;
        this.width = (int)parent.width;
        this.height = (int)parent.height;
        this.position = parent.getDrawPosition();
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
    }

    // Just a quick and convenient wrapper over Rectangle2D for collision detection

    public boolean isCollidingWith(Entity other){
        return boundingBox.intersects(other.getBoundingBox());
    }

    public boolean isInsideOf(Entity other){
        return other.getBoundingBox().contains(boundingBox);
    }

    public boolean isEntityInside(Entity other){
        return boundingBox.contains(other.getBoundingBox());
    }

    public boolean isPointInsideOf(Vector2 point){
        return boundingBox.contains(new Point2D(point.xPos, point.yPos));
    }

    public void setIfDebug(boolean isDebug){
        this.isDebug = isDebug;
    }
}
