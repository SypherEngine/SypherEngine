package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.graphics.IRenderable;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public abstract class Entity implements IRenderable {
    Point2D position = new Point2D(0, 0);
    public float rotation;
    float scale = 1;
    public double width;
    public double height;

    public double xPos = 0, yPos = 0;
    double boundaryX = xPos, boundaryY = yPos;

    Object entityRenderable;

    boolean isDead = false;

    Rectangle2D boundary;

    public abstract void update(SypherEngine engine);
    public abstract void render(SypherEngine engine);

    /* **********************************************************
     *                           MISC                           *
     ************************************************************/
    public Point2D getDrawPosition() {
        return new Point2D(xPos, yPos);
    }

    public void setDrawPosition(float x, float y) {
        this.xPos = x;
        this.yPos = y;
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

    public void move(Point2D vector) {
        setDrawPosition((float) (this.position.getX() + vector.getX()), (float) (this.position.getY() + vector.getY()));
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Point2D getCenter() {
        Point2D pos = getDrawPosition();
        return new Point2D(pos.getX() + width / 2, pos.getY() + height / 2);
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

    public void setBoundary(int xPos, int yPos, int width, int height){
        this.boundary = new Rectangle2D(xPos, yPos, width, height);
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
