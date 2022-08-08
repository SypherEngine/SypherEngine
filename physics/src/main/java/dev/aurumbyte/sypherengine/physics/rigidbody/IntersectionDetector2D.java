/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.rigidbody;

import dev.aurumbyte.sypherengine.physics.primitives.AABB;
import dev.aurumbyte.sypherengine.physics.primitives.Box2D;
import dev.aurumbyte.sypherengine.physics.primitives.Circle;
import dev.aurumbyte.sypherengine.util.math.Mathf;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class IntersectionDetector2D {
    // Point vs Primitive

    // Check whether a point is on a given line
    public static boolean pointOnLine(Vector2 point, Vector2 lineStart, Vector2 lineEnd) {
        float dy = lineEnd.yPos - lineStart.yPos;
        float dx = lineEnd.xPos - lineStart.xPos;

        float slope = dy / dx;

        float yIntersect = lineEnd.yPos - (slope * lineEnd.xPos);

        // check whether point is on line
        return point.yPos == slope * point.xPos + yIntersect;
    }

    // Check whether a point is inside or on a circle
    public static boolean pointInCircle(Vector2 point, Circle circle) {
        Vector2 center = circle.getCenter();
        Vector2 centerToPoint = new Vector2(point).subtract(center);

        float lengthSqaured = centerToPoint.xPos * centerToPoint.xPos + centerToPoint.yPos * centerToPoint.yPos;

        return lengthSqaured <= circle.getRadius() * circle.getRadius();
    }

    // Check whether a point is inside an AABB (Axis Aligned Bounding Box)
    public static boolean pointInAABB(Vector2 point, AABB box) {
        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return point.xPos <= max.xPos && min.xPos <= point.xPos && point.yPos <= max.yPos && min.yPos <= point.yPos;
    }

    // Checks whether a point is inside a Box (rotatable)
    public static boolean pointInBox2D(Vector2 point, Box2D box) {
        // Translate the point into local space
        Vector2 pointLocalToBox = new Vector2(point);
        Mathf.rotate(
                pointLocalToBox,
                box.getRigidBody().getRotation(),
                box.getRigidBody().getPosition());

        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return pointLocalToBox.xPos <= max.xPos
                && min.xPos <= pointLocalToBox.xPos
                && pointLocalToBox.yPos <= max.yPos
                && min.yPos <= pointLocalToBox.yPos;
    }

    // Line vs Primitive
    public static boolean lineVsCircle(Vector2 lineStart, Vector2 lineEnd, Circle circle) {
        if (pointInCircle(lineStart, circle) || pointInCircle(lineEnd, circle)) {
            return true;
        }

        Vector2 ab = new Vector2(lineStart).subtract(lineEnd);
        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToLineStart = new Vector2(circleCenter).subtract(lineStart);

        float t = centerToLineStart.dot(ab) / ab.dot(ab);

        if (t < 0.0f || t > 1.0f) {
            return false;
        }

        Vector2 closestPoint = new Vector2(lineEnd).add(ab.multiply(t));

        return pointInCircle(closestPoint, circle);
    }
}
