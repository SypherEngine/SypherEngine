package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class AABB extends Entity {
    boolean isDebug = false;
    public Rectangle2D boundingBox;
    Entity parent;

    public AABB(Entity parent){
        this.parent = parent;
        this.width = parent.width;
        this.height = parent.getHeight();
        this.position = parent.getDrawPosition();
        this.boundingBox = new Rectangle2D(position.xPos, position.yPos, width, height);
    }

    // Just a quick and convenient wrapper over Rectangle2D for collision detection
    @Override
    public void update(SypherEngine engine) {
        this.position.xPos = parent.position.xPos;
        this.position.yPos = parent.position.yPos;

        this.isDead = parent.isDead;
    }

    @Override
    public void render(SypherEngine engine) {
        if(isDebug) engine.getRenderer().drawRectangle(position, (int)width, (int) height, false, Color.BLACK);
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

    public void setIfDebug(boolean isDebug){
        this.isDebug = isDebug;
    }
}
