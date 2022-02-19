package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public class AABB {
    // Just a quick and convenient wrapper over Rectangle2D for collision detection

    public Rectangle2D boundingBox;

    public AABB(Entity parent){
        this.boundingBox = new Rectangle2D(parent.position.xPos, parent.position.yPos, parent.getWidth(), parent.getHeight());
    }

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
}
