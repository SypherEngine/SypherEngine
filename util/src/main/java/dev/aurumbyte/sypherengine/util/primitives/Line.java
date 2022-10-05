package dev.aurumbyte.sypherengine.util.primitives;

import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Line extends Primitive {
    Vector2 start;
    Vector2 end;

    float stroke = 1.0f;

    public Line(Vector2 start, Vector2 end){
        this.start = start;
        this.end = end;
    }

    public Line(Vector2 start, Vector2 end, float stroke){
        this.start = start;
        this.end = end;
        this.stroke = stroke;
    }

    public float getStroke() {
        return stroke;
    }

    public Vector2 getEnd() {
        return end;
    }

    public Vector2 getStart() {
        return start;
    }

    public void setEnd(Vector2 end) {
        this.end = end;
    }

    public void setStart(Vector2 start) {
        this.start = start;
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
    }

    public float lengthSquared(){
        float dx = end.xPos - start.xPos;
        float dy = end.yPos - start.yPos;

        return  (dx * dx) + (dy * dy);
    }
}
