package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.components.scene.Scene;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

public abstract class Entity<T extends Scene> {
    Point2D position = new Point2D(0, 0);
    float rotation;
    float scale = 1;
    double width;
    double height;
    Object entityRenderable;

    boolean isDead = false;

    Rectangle2D boundary = new Rectangle2D(position.getX(), position.getY(), width, height);

    public abstract void update(float deltaTime);
    public abstract void render(Renderer renderer);

    /* **********************************************************
     *                           POSITION                                     *
     ************************************************************ */
    public Point2D getDrawPosition() {
        return position;
    }

    public void setDrawPosition(float x, float y) {
        this.position = new Point2D(x, y);
    }

    private void rotate(float rotation) {
        this.rotation += rotation;
    }

    private void move(Point2D vector) {
        this.position = this.position.add(vector);
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

    public boolean isIntersectingWith(Sprite2D sprite2D){
        return sprite2D.getBoundary().intersects(this.getBoundary());
    }
}
