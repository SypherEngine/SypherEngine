package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.IRenderable;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Rectangle2D;

public abstract class Entity implements IRenderable {
    Vector2 position = new Vector2();
    public float rotation;
    float scale = 1;
    public double width;
    public double height;

    double boundaryX = position.xPos, boundaryY = position.yPos;

    Object entityRenderable;

    boolean isDead = false;

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

    public void setBoundary(Rectangle2D boundary) {
        this.boundary = boundary;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void rotate(float rotation) {
        this.rotation += rotation;
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Vector2 getCenter() {
        Vector2 pos = getDrawPosition();
        return new Vector2((float)(pos.xPos + width / 2), (float)(pos.yPos + height / 2));
    }

    public Object getEntityRenderable() {
        return entityRenderable;
    }

    public double getWidth() {
        return this.width * getScale();
    }

    public double getHeight() {
        return this.height * getScale();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setIsDead(boolean dead) {
        isDead = dead;
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public void setBoundaryPos(int xPos, int yPos){
        this.boundaryX = xPos;
        this.boundaryY = yPos;
    }

    public void setBoundary(Vector2 position, int width, int height){
        this.boundary = new Rectangle2D(position.xPos, position.yPos, width, height);
    }

    public double getBoundaryX() {
        return boundaryX;
    }

    public double getBoundaryY() {
        return boundaryY;
    }

    public boolean collidesWith(Entity entity){
        return entity.getBoundary().intersects(this.getBoundary());
    }
}
