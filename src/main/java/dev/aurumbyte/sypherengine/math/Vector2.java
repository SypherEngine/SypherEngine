/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.math;

/**
 * <p>The Vector2 class of SypherEngine, the base of all position</p>
 * @author AurumByte
 * @since 0.3.0
 */
public class Vector2 {
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
    public void add(Vector2 vector2) {
        this.xPosi += vector2.xPosi;
        this.yPosi += vector2.yPosi;

        this.xPos += vector2.xPos;
        this.yPosi += vector2.yPos;
    }

    /**
     * <p>Subtracts a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public void subtract(Vector2 vector2) {
        this.xPosi -= vector2.xPosi;
        this.yPosi -= vector2.yPosi;

        this.xPos -= vector2.xPos;
        this.yPosi -= vector2.yPos;
    }

    /**
     * <p>Multiplies a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public void multiply(Vector2 vector2) {
        this.xPosi *= vector2.xPosi;
        this.yPosi *= vector2.yPosi;

        this.xPos *= vector2.xPos;
        this.yPosi *= vector2.yPos;
    }

    /**
     * <p>Divides a Vector2 with another Vector2</p>
     * @param vector2 The other Vector
     * @since 0.3.0
     */
    public void divide(Vector2 vector2) {
        this.xPosi /= vector2.xPosi;
        this.yPosi /= vector2.yPosi;

        this.xPos /= vector2.xPos;
        this.yPosi /= vector2.yPos;
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
    public void normalize() {
        // sets length to 1
        double length = Math.sqrt(xPos * xPos + yPos * yPos);

        if (length != 0.0) {
            float s = 1.0f / (float) length;
            xPos = xPos * s;
            yPos = yPos * s;
        }
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
}
