/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.util.math;

/**
 * <p>The Math utility class for the engine</p>
 * @author AurumByte
 * @since 0.3.2
 */
public class Mathf {
    /**
     * <p>The linear interpolation formula</p>
     */
    public static float lerp(float a, float b, float f) {
        return a + f * (b - a);
    }

    /**
     * <p>The inverse of the linear interpolation formula</p>
     */
    public static float inverseLerp(float a, float b, float v) {
        return (v - a) / (b - a);
    }

    /**
     * <p>The normalization of a number</p>
     */
    public static float normalize(float min, float max, float num) {
        if (num > max) {
            throw new IllegalArgumentException("The provided number must be less than the maximum number.");
        }
        if (num < min) {
            throw new IllegalArgumentException("The provided number must be more than the minimum number.");
        }

        return (num - min) / (max - min);
    }

    /**
     * <p>The getting back of a number from a normalized one</p>
     */
    public static float denormalize(float min, float max, float normalized) {
        if (normalized > 1.0f) {
            throw new IllegalArgumentException("The normalized number must be less than 1.0f.");
        }
        if (normalized < 0.0f) {
            throw new IllegalArgumentException("The normalized number must be more than 0.0f.");
        }
        if (min > max) {
            throw new IllegalArgumentException("The minimum must be less than the maximum.");
        }

        return (normalized * (max - min)) + min;
    }

    public static void rotate(Vector2 vec, float degrees, Vector2 origin) {
        float x = vec.xPos - origin.xPos;
        float y = vec.yPos - origin.yPos;

        float cosTheta = (float) Math.cos(Math.toRadians(degrees));
        float sinTheta = (float) Math.sin(Math.toRadians(degrees));

        float xPrime = (x * cosTheta) - (y * sinTheta);
        float yPrime = (x * sinTheta) + (y * cosTheta);

        xPrime += origin.xPos;
        yPrime += origin.yPos;

        vec.setPosition(xPrime, yPrime);
    }

    // Compares flaoting point numbers to the desired precision
    public static boolean compare(float x, float y, float epsilon) {
        return Math.abs(x - y) <= epsilon * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2 a, Vector2 b, float epsilon) {
        return compare(a.xPos, b.xPos, epsilon) && compare(a.yPos, b.yPos, epsilon);
    }

    public static boolean compare(float x, float y) {
        return Math.abs(x - y) <= Float.MIN_VALUE * Math.max(1.0f, Math.max(Math.abs(x), Math.abs(y)));
    }

    public static boolean compare(Vector2 a, Vector2 b) {
        return compare(a.xPos, b.xPos) && compare(a.yPos, b.yPos);
    }
}
