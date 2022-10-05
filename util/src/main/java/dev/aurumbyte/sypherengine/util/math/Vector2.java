/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.util.math;

import java.io.Serializable;

/**
 * <p>The Vector2 class of SypherEngine, the base of all position</p>
 * @author AurumByte
 * @since 0.3.0
 */
public class Vector2 implements Serializable {
    /**
     * The integer position of xPos and yPos, for people who are too lazy to cast to int XD
     */
    public int xPosi, yPosi;

    /**
     * The xPos and yPos (in float)
     */
    public float xPos, yPos;

    /**
     * The rotation of the vector
     */
    float rotation = 0;

    /**
     * <p>Create an empty Vector of coords (0, 0)</p>
     * @since 0.3.0
     */
    public Vector2() {
        this.xPosi = 0;
        this.yPosi = 0;
        this.rotation = 0;

        this.xPos = 0;
        this.yPos = 0;
    }

    /**
     * <p>Creates a Vector2 instance with x and y position</p>
     * @param x x position
     * @param y y position
     * @since 0.3.0
     */
    public Vector2(float x, float y) {
        this.xPosi = (int) x;
        this.yPosi = (int) y;
        this.rotation = 0;

        this.xPos = x;
        this.yPos = y;
    }

    /**
     * <p>Creates a Vector2 instance with another Vector2 data (a copy)</p>
     * @param vector2 The other vector2
     * @since 0.3.0
     */
    public Vector2(Vector2 vector2) {
        this.xPosi = (int) vector2.xPos;
        this.yPosi = (int) vector2.yPos;
        this.rotation = vector2.getRotation();

        this.xPos = vector2.xPos;
        this.yPos = vector2.yPos;
    }

    /**
     * <p>Creates a Vector2 instance with a specified position and rotation</p>
     * @param x x position
     * @param y y position
     * @param rotation rotation of the vector
     * @since 0.3.0
     */
    public Vector2(float x, float y, float rotation) {
        this.xPosi = (int) x;
        this.yPosi = (int) y;
        this.rotation += rotation;

        this.xPos = x;
        this.yPos = y;
    }

    /**
     * <p>Adds a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public Vector2 add(Vector2 vector2) {
        this.xPosi += vector2.xPosi;
        this.yPosi += vector2.yPosi;

        this.xPos += vector2.xPos;
        this.yPos += vector2.yPos;

        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Subtracts a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public Vector2 subtract(Vector2 vector2) {
        this.xPosi -= vector2.xPosi;
        this.yPosi -= vector2.yPosi;

        this.xPos -= vector2.xPos;
        this.yPos -= vector2.yPos;

        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Multiplies a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public Vector2 multiply(Vector2 vector2) {
        this.xPosi *= vector2.xPosi;
        this.yPosi *= vector2.yPosi;

        this.xPos *= vector2.xPos;
        this.yPos *= vector2.yPos;

        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Multiplies a Vector2 with another Vector2</p>
     * @param value The value by which to multiply
     * @since 0.3.0
     */
    public Vector2 multiply(float value) {
        this.xPos *= value;
        this.yPos *= value;

        this.xPosi = (int)xPos;
        this.yPosi = (int)yPos;


        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Divides a Vector2 with another Vector2</p>
     * @param value, the value by which we are dividing the vector's magnitude
     * @since 0.3.0
     */
    public Vector2 divide(float value) {
        this.xPos /= value;
        this.yPos /= value;

        this.xPosi = (int)xPos;
        this.yPosi = (int)yPos;


        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Rotates the Vector</p>
     * @param rotation The amount of rotation needed for the vector
     * @since 0.3.0
     */
    public void rotate(float rotation) {
        this.rotation += rotation;
    }

    /**
     * <p>Set the Vector's position</p>
     * @param xPos The x position
     * @param yPos The y position
     * @since 0.3.0
     */
    public void setPosition(float xPos, float yPos) {
        this.xPosi = (int) xPos;
        this.yPosi = (int) yPos;

        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * <p>Gets the vector position</p>
     * @since 0.3.0
     */
    public Vector2 getPosition() {
        return new Vector2(this.xPosi, this.yPosi);
    }

    /**
     * <p>Gets the rotation of the Vector</p>
     * @since 0.3.0
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * <p>Normalizes the Vector</p>
     * @since v0.3.0
     */
    public Vector2 normalize() {
        // sets length to 1
        double length = Math.sqrt(xPos * xPos + yPos * yPos);

        if (length != 0.0) {
            float s = 1.0f / (float) length;
            xPos = xPos * s;
            yPos = yPos * s;
        }

        return new Vector2(xPos, yPos);
    }

    /**
     * <p>Calculates the distance between two vectors</p>
     * @param a A Vector
     * @param b Another vector
     */
    public static double distance(Vector2 a, Vector2 b) {
        float v0 = b.xPos - a.xPos;
        float v1 = b.yPos - a.yPos;
        return Math.sqrt(v0 * v0 + v1 * v1);
    }

    public float dot(Vector2 other) {
        return xPos * other.xPos + yPos * other.yPos;
    }

    public float lengthSquared(){
        return (xPos * xPos) + (yPos * yPos);
    }

    public float get(int index){
        switch (index){
            case 0 -> {return xPos;}
            case 1 -> {return yPos;}
            default -> throw new IllegalArgumentException("There are only 2 components in a Vector2! 0 for the X component and 1 for the Y component");
        }
    }

    public void setComponent(int index, float value){
        switch (index){
            case 0 -> {
                this.xPos = value;
                this.xPosi = (int)value;
            }
            case 1 -> {
                this.yPos = value;
                this.yPosi = (int)value;
            }
            default -> throw new IllegalArgumentException("There are only 2 components in a Vector2! 0 for the X component and 1 for the Y component");
        }
    }

    public float length(){
        return (float) Math.sqrt(lengthSquared());
    }
    public void set(Vector2 vector){
        this.xPos = vector.xPos;
        this.yPos = vector.yPos;
        this.xPosi = (int)xPos;
        this.yPosi = (int)yPos;
        this.rotation = vector.rotation;
    }

    @Override
    public String toString() {
        return "(x : " + xPos + ", y : " + yPos + ")";
    }

    public void set(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xPosi = (int)xPos;
        this.yPosi = (int)yPos;
    }
}
