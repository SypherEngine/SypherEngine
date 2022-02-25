/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.IRenderable;
import dev.aurumbyte.sypherengine.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Rectangle2D;

public abstract class Entity implements IRenderable {
    Vector2 position = new Vector2();
    public float rotation;
    float scale = 1;
    public double width;
    public double height;
    boolean isDead = false;

    String tag;

    List<Component> components = new ArrayList<>();

    Rectangle2D boundary;

    public abstract void update(SypherEngine engine);

    public abstract void render(SypherEngine engine);

    /* **********************************************************
     *                           MISC                           *
     ************************************************************/

    public Vector2 getDrawPosition() {
        return position;
    }

    public void setDrawPosition(float x, float y) {
        this.position.setPosition(x, y);
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void rotate(float rotation) {
        this.position.rotate(rotation);
    }

    public float getRotation() {
        return this.position.getRotation();
    }

    public float getScale() {
        return scale;
    }

    public Vector2 getCenter() {
        Vector2 pos = getDrawPosition();
        return new Vector2((float) (pos.xPos + width / 2), (float) (pos.yPos + height / 2));
    }

    public double getWidth() {
        return this.width * getScale();
    }

    public double getHeight() {
        return this.height * getScale();
    }

    public String getTag() {
        return tag;
    }

    public List<Component> getComponents() {
        return components;
    }

    public boolean isDead() {
        return isDead;
    }

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
