/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.math;

public class Vector2 {
    public int xPos, yPos;
    public float xPosf, yPosf;
    float rotation = 0;

    public Vector2() {
        this.xPos = 0;
        this.yPos = 0;
        this.rotation = 0;

        this.xPosf = 0;
        this.yPosf = 0;
    }

    public Vector2(int x, int y) {
        this.xPos = x;
        this.yPos = y;
        this.rotation = 0;

        this.xPosf = x;
        this.yPosf = y;
    }

    public Vector2(float x, float y) {
        this.xPos = (int) x;
        this.yPos = (int) y;
        this.rotation = 0;

        this.xPosf = x;
        this.yPosf = y;
    }

    public Vector2(int x, int y, float rotation) {
        this.xPos = x;
        this.yPos = y;
        this.rotation += rotation;

        this.xPosf = x;
        this.yPosf = y;
    }

    public Vector2(float x, float y, float rotation) {
        this.xPos = (int) x;
        this.yPos = (int) y;
        this.rotation += rotation;

        this.xPosf = x;
        this.yPosf = y;
    }

    public void add(Vector2 vector2) {
        this.xPos += vector2.xPos;
        this.yPos += vector2.yPos;

        this.xPosf += vector2.xPosf;
        this.yPos += vector2.yPosf;
    }

    public void subtract(Vector2 vector2) {
        this.xPos -= vector2.xPos;
        this.yPos -= vector2.yPos;

        this.xPosf -= vector2.xPosf;
        this.yPos -= vector2.yPosf;
    }

    public void multiply(Vector2 vector2) {
        this.xPos *= vector2.xPos;
        this.yPos *= vector2.yPos;

        this.xPosf *= vector2.xPosf;
        this.yPos *= vector2.yPosf;
    }

    public void divide(Vector2 vector2) {
        this.xPos /= vector2.xPos;
        this.yPos /= vector2.yPos;

        this.xPosf /= vector2.xPosf;
        this.yPos /= vector2.yPosf;
    }

    public static Vector2 Origin() {
        return new Vector2(0, 0);
    }

    public void rotate(float rotation) {
        this.rotation += rotation;
    }

    public void setPosition(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;

        this.xPosf = xPos;
        this.yPosf = yPos;
    }

    public void setPosition(float xPos, float yPos) {
        this.xPos = (int) xPos;
        this.yPos = (int) yPos;

        this.xPosf = xPos;
        this.yPosf = yPos;
    }

    public Vector2 getPosition() {
        return new Vector2(this.xPos, this.yPos);
    }

    public float getRotation() {
        return rotation;
    }

    public void normalize() {
        // sets length to 1
        //
        double length = Math.sqrt(xPosf * xPosf + yPosf * yPosf);

        if (length != 0.0) {
            float s = 1.0f / (float) length;
            xPosf = xPosf * s;
            yPosf = yPosf * s;
        }
    }

    public static double distance(Vector2 a, Vector2 b) {
        float v0 = b.xPosf - a.xPosf;
        float v1 = b.yPosf - a.yPosf;
        return Math.sqrt(v0 * v0 + v1 * v1);
    }
}
