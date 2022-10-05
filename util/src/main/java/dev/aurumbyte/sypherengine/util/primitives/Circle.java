package dev.aurumbyte.sypherengine.util.primitives;

import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Circle extends Primitive {
    float radius;
    float stroke = 1.0f;

    boolean isFilled = true;

    public Circle(Vector2 position, float radius){
        this.radius = radius;
        this.position = position;
    }

    public Circle(Vector2 position, float radius, float strokeWidth){
        this.radius = radius;
        this.position = position;
        this.stroke = strokeWidth;
        this.isFilled = false;
    }

    public float getRadius() {
        return radius;
    }

    public float getStroke() {
        return stroke;
    }

    public Vector2 getCenter() {
        return position;
    }

    public boolean isFilled(){
        return this.isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }
}
