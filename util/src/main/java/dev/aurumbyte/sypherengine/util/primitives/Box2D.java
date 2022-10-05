package dev.aurumbyte.sypherengine.util.primitives;

import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Box2D extends Primitive {
    float width, height;
    float stroke = 1.0f;
    boolean isFilled = true;

    public Box2D(Vector2 position, float width, float height){
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Box2D(Vector2 position, float width, float height,  float stroke){
        this.position = position;
        this.width = width;
        this.height = height;
        this.stroke = stroke;
        this.isFilled = false;
    }

    public float getStroke() {
        return stroke;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public boolean isFilled(){
        return this.isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
