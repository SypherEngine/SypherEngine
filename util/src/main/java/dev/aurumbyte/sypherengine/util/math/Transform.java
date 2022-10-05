/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.util.math;

public class Transform {
    float rotation = 0;
    Vector2 position = new Vector2();
    float scale = 1;

    public float getRotation() {
        return rotation;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getScale() {
        return scale;
    }

    public void rotate(float rotation) {
        this.rotation = rotation;
        this.getPosition().rotate(rotation);
    }

    public void move(Vector2 position) {
        this.position = position;
    }

    public void moveX(float x){
        this.position.xPos += x;
    }

    public void moveY(float y){
        this.position.yPos += y;
    }

    public void scale(float scale) {
        this.scale = scale;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
