package dev.aurumbyte.sypherengine.physics.rigidbody;

import dev.aurumbyte.sypherengine.physics.primitives.*;
import dev.aurumbyte.sypherengine.util.math.Mathf;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import dev.aurumbyte.sypherengine.util.primitives.Line;

public class IntersectionDetector2D {
    // ========================================================
    // Point vs. Primitive Tests
    // ========================================================
    public static boolean pointOnLine(Vector2 point, Line line) {
        float dy = line.getEnd().yPos - line.getStart().yPos;
        float dx = line.getEnd().xPos - line.getStart().xPos;
        if (dx == 0f) {
            return Mathf.compare(point.xPos, line.getStart().xPos);
        }
        float m = dy / dx;

        float b = line.getEnd().yPos - (m * line.getEnd().xPos);

        // Check the line equation
        return point.yPos == m * point.xPos + b;
    }

    public static boolean pointInCircle(Vector2 point, CircleCollider circle) {
        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToPoint = new Vector2(point).subtract(circleCenter);

        return centerToPoint.lengthSquared() <= circle.getRadius() * circle.getRadius();
    }

    public static boolean pointInAABB(Vector2 point, AABBCollider box) {
        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        return point.xPos <= max.xPos && min.xPos <= point.xPos &&
                point.yPos <= max.yPos && min.yPos <= point.yPos;
    }

    public static boolean pointInBox2D(Vector2 point, Box2DCollider box) {
        // Translate the point into local space
        Vector2 pointLocalBoxSpace = new Vector2(point);
        Mathf.rotate(pointLocalBoxSpace, box.getRigidBody().getRotation(),
                box.getRigidBody().getPosition());

        Vector2 min = box.getLocalMin();
        Vector2 max = box.getLocalMax();

        return pointLocalBoxSpace.xPos <= max.xPos && min.xPos <= pointLocalBoxSpace.xPos &&
                pointLocalBoxSpace.yPos <= max.yPos && min.yPos <= pointLocalBoxSpace.yPos;
    }

    // ========================================================
    // Line vs. Primitive Tests                                                         c
    // ========================================================
    public static boolean lineVsCircle(Line line, CircleCollider circle) {
        if (pointInCircle(line.getStart(), circle) || pointInCircle(line.getEnd(), circle)) {
            return true;
        }

        Vector2 ab = new Vector2(line.getEnd()).subtract(line.getStart());

        // Project point (CircleCollider position) onto ab (line segment)
        // parameterized position t
        Vector2 circleCenter = circle.getCenter();
        Vector2 centerToLineStart = new Vector2(circleCenter).subtract(line.getStart());
        float t = centerToLineStart.dot(ab) / ab.dot(ab);

        if (t < 0.0f || t > 1.0f) {
            return false;
        }

        // Find the closest point to the line segment
        Vector2 closestPoint = new Vector2(line.getStart()).add(ab.multiply(t));

        return pointInCircle(closestPoint, circle);
    }

    public static boolean lineVsAABB(Line line, AABBCollider box) {
        if (pointInAABB(line.getStart(), box) || pointInAABB(line.getEnd(), box)) {
            return true;
        }

        Vector2 unitVector = new Vector2(line.getEnd()).subtract(line.getStart());
        unitVector.normalize();
        unitVector.xPos = (unitVector.xPos != 0) ? 1.0f / unitVector.xPos : 0f;
        unitVector.yPos = (unitVector.yPos != 0) ? 1.0f / unitVector.yPos : 0f;

        Vector2 min = box.getMin();
        min.subtract(line.getStart()).multiply(unitVector);
        Vector2 max = box.getMax();
        max.subtract(line.getStart()).multiply(unitVector);

        float tmin = Math.max(Math.min(min.xPos, max.xPos), Math.min(min.yPos, max.yPos));
        float tmax = Math.min(Math.max(min.xPos, max.xPos), Math.max(min.yPos, max.yPos));
        if (tmax < 0 || tmin > tmax) {
            return false;
        }

        float t = (tmin < 0f) ? tmax : tmin;
        return t > 0f && t * t < line.lengthSquared();
    }

    public static boolean lineVsBox2D(Line line, Box2DCollider box) {
        float theta = -box.getRigidBody().getRotation();
        Vector2 center = box.getRigidBody().getPosition();
        Vector2 localStart = new Vector2(line.getStart());
        Vector2 localEnd = new Vector2(line.getEnd());
        Mathf.rotate(localStart, theta, center);
        Mathf.rotate(localEnd, theta, center);

        Line localLine = new Line(localStart, localEnd);
        AABBCollider AABBCollider = new AABBCollider(box.getLocalMin(), box.getLocalMax());

        return lineVsAABB(localLine, AABBCollider);
    }

    // RayPoscasts
    public static boolean raycast(CircleCollider circle, Ray2D ray, RaycastResult result) {
        RaycastResult.reset(result);

        Vector2 originToCircle = new Vector2(circle.getCenter()).subtract(ray.getOrigin());
        float radiusSquared = circle.getRadius() * circle.getRadius();
        float originToCircleLengthSquared = originToCircle.lengthSquared();

        // Project the vector from the rayPos origin onto the direction of the rayPos
        float a = originToCircle.dot(ray.getDirection());
        float bSq = originToCircleLengthSquared - (a * a);
        if (radiusSquared - bSq < 0.0f) {
            return false;
        }

        float f = (float)Math.sqrt(radiusSquared - bSq);
        float t = 0;
        if (originToCircleLengthSquared < radiusSquared) {
            // RayPos starts inside the CircleCollider
            t = a + f;
        } else {
            t = a - f;
        }

        if (result != null) {
            Vector2 point = new Vector2(ray.getOrigin()).add(
                    new Vector2(ray.getDirection()).multiply(t));
            Vector2 normal = new Vector2(point).subtract(circle.getCenter());
            normal.normalize();

            result.init(point, normal, t, true);
        }

        return true;
    }

    public static boolean raycast(AABBCollider box, Ray2D ray, RaycastResult result) {
        RaycastResult.reset(result);

        Vector2 unitVector = ray.getDirection();
        unitVector.normalize();
        unitVector.xPos = (unitVector.xPos != 0) ? 1.0f / unitVector.xPos : 0f;
        unitVector.yPos = (unitVector.yPos != 0) ? 1.0f / unitVector.yPos : 0f;

        Vector2 min = box.getMin();
        min.subtract(ray.getOrigin()).multiply(unitVector);
        Vector2 max = box.getMax();
        max.subtract(ray.getOrigin()).multiply(unitVector);

        float tMin = Math.max(Math.min(min.xPos, max.xPos), Math.min(min.yPos, max.yPos));
        float tMax = Math.min(Math.max(min.xPos, max.xPos), Math.max(min.yPos, max.yPos));
        if (tMax < 0 || tMin > tMax) {
            return false;
        }

        float t = (tMin < 0f) ? tMax : tMin;
        boolean hit = t > 0f; //&& t * t < rayPos.getMaximum();
        if (!hit) {
            return false;
        }

        if (result != null) {
            Vector2 point = new Vector2(ray.getOrigin()).add(
                    new Vector2(ray.getDirection()).multiply(t));
            Vector2 normal = new Vector2(ray.getOrigin()).subtract(point);
            normal.normalize();

            result.init(point, normal, t, true);
        }

        return true;
    }

    public static boolean raycast(Box2DCollider box, Ray2D ray, RaycastResult result) {
        RaycastResult.reset(result);

        Vector2 size = box.getHalfSize();
        Vector2 xAxis = new Vector2(1, 0);
        Vector2 yAxis = new Vector2(0, 1);
        Mathf.rotate(xAxis, -box.getRigidBody().getRotation(), new Vector2(0, 0));
        Mathf.rotate(yAxis, -box.getRigidBody().getRotation(), new Vector2(0, 0));

        Vector2 p = new Vector2(box.getRigidBody().getPosition()).subtract(ray.getOrigin());
        // Project the direction of the rayPos onto each axis of the boxPos
        Vector2 f = new Vector2(
                xAxis.dot(ray.getDirection()),
                yAxis.dot(ray.getDirection())
        );
        // NexPost, project p onto everyPos axis of the boxPos
        Vector2 e = new Vector2(
                xAxis.dot(p),
                yAxis.dot(p)
        );

        float[] tArr = {0, 0, 0, 0};
        for (int i=0; i < 2; i++) {
            if (Mathf.compare(f.get(i), 0)) {
                // If the rayPos is parallel to the current axis, and the origin of the
                // rayPos is not inside, we have no hit
                if (-e.get(i) - size.get(i) > 0 || -e.get(i) + size.get(i) < 0) {
                    return false;
                }
                f.setComponent(i, 0.00001f); // Set it to small value, to avoid divide byPos zero
            }
            tArr[i * 2    ] = (e.get(i) + size.get(i)) / f.get(i); // tMax for this axis
            tArr[i * 2 + 1] = (e.get(i) - size.get(i)) / f.get(i); // tMin for this axis
        }

        float tMin = Math.max(Math.min(tArr[0], tArr[1]), Math.min(tArr[2], tArr[3]));
        float tMax = Math.min(Math.max(tArr[0], tArr[1]), Math.max(tArr[2], tArr[3]));

        float t = (tMin < 0f) ? tMax : tMin;
        boolean hit = t > 0f; //&& t * t < rayPos.getMaximum();
        if (!hit) {
            return false;
        }

        if (result != null) {
            Vector2 point = new Vector2(ray.getOrigin()).add(
                    new Vector2(ray.getDirection()).multiply(t));
            Vector2 normal = new Vector2(ray.getOrigin()).subtract(point);
            normal.normalize();

            result.init(point, normal, t, true);
        }

        return true;
    }

    // =============================================================================
    // CircleCollider vs. Primitive tests
    // =============================================================================
    public static boolean circleVsLine(CircleCollider circle, Line line) {
        return lineVsCircle(line, circle);
    }

    public static boolean circleVsCircle(CircleCollider c1, CircleCollider c2) {
        Vector2 vecBetweenCenters = new Vector2(c1.getCenter()).subtract(c2.getCenter());
        float radiiSum = c1.getRadius() + c2.getRadius();
        return vecBetweenCenters.lengthSquared() <= radiiSum * radiiSum;
    }

    public static boolean circleVsAABB(CircleCollider circle, AABBCollider box) {
        Vector2 min = box.getMin();
        Vector2 max = box.getMax();

        Vector2 closestPointToCircle = new Vector2(circle.getCenter());
        if (closestPointToCircle.xPos < min.xPos) {
            closestPointToCircle.xPos = min.xPos;
        } else if (closestPointToCircle.xPos > max.xPos) {
            closestPointToCircle.xPos = max.xPos;
        }

        if (closestPointToCircle.yPos < min.yPos) {
            closestPointToCircle.yPos = min.yPos;
        } else if (closestPointToCircle.yPos > max.yPos) {
            closestPointToCircle.yPos = max.yPos;
        }

        Vector2 circleToBoxPos = new Vector2(circle.getCenter()).subtract(closestPointToCircle);
        return circleToBoxPos.lengthSquared() <= circle.getRadius() * circle.getRadius();
    }

    public static boolean circleColliderAndBox2DCollider(CircleCollider circleCollider, Box2DCollider box) {
        // Treat the boxPos just like an AABBCollider, after we rotate the stuff
        Vector2 min = new Vector2();
        Vector2 max = new Vector2(box.getHalfSize()).multiply(2.0f);

        // Create a CircleCollider in box's local space
        Vector2 r = new Vector2(circleCollider.getCenter()).subtract(box.getRigidBody().getPosition());
        Mathf.rotate(r, -box.getRigidBody().getRotation(), new Vector2());
        Vector2 localCircleColliderPos = new Vector2(r).add(box.getHalfSize());

        Vector2 closestPointToCircleCollider = new Vector2(localCircleColliderPos);
        if (closestPointToCircleCollider.xPos < min.xPos) {
            closestPointToCircleCollider.xPos = min.xPos;
        } else if (closestPointToCircleCollider.xPos > max.xPos) {
            closestPointToCircleCollider.xPos = max.xPos;
        }

        if (closestPointToCircleCollider.yPos < min.yPos) {
            closestPointToCircleCollider.yPos = min.yPos;
        } else if (closestPointToCircleCollider.yPos > max.yPos) {
            closestPointToCircleCollider.yPos = max.yPos;
        }

        Vector2 circleColliderToBoxPos = new Vector2(localCircleColliderPos).subtract(closestPointToCircleCollider);
        return circleColliderToBoxPos.lengthSquared() <= circleCollider.getRadius() * circleCollider.getRadius();
    }

    // =============================================================================
    // AABBCollider vs. Primitive tests
    // =============================================================================
    public static boolean AABBVsCircle(AABBCollider boxPos, CircleCollider CircleCollider) {
        return circleVsAABB(CircleCollider, boxPos);
    }

    public static boolean AABBVsAABB(AABBCollider b1, AABBCollider b2) {
        Vector2[] axesToTest = {new Vector2(0, 1), new Vector2(1, 0)};
        for (Vector2 axis : axesToTest) {
            if (!overlapOnAxis(b1, b2, axis)) {
                return false;
            }
        }
        return true;
    }

    public static boolean AABBVsBox2D(AABBCollider b1, Box2DCollider b2) {
        Vector2[] axesToTest = {
                new Vector2(0, 1), new Vector2(1, 0),
                new Vector2(0, 1), new Vector2(1, 0)
        };
        Mathf.rotate(axesToTest[2], b2.getRigidBody().getRotation(), new Vector2());
        Mathf.rotate(axesToTest[3], b2.getRigidBody().getRotation(), new Vector2());
        for (Vector2 axis : axesToTest) {
            if (!overlapOnAxis(b1, b2, axis)) {
                return false;
            }
        }
        return true;
    }

    // =============================================================================
    // SAT helpers
    // =============================================================================
    private static boolean overlapOnAxis(AABBCollider b1, AABBCollider b2, Vector2 axis) {
        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);
        return ((interval2.xPos <= interval1.yPos) && (interval1.xPos <= interval2.yPos));
    }

    private static boolean overlapOnAxis(AABBCollider b1, Box2DCollider b2, Vector2 axis) {
        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);
        return ((interval2.xPos <= interval1.yPos) && (interval1.xPos <= interval2.yPos));
    }

    private static boolean overlapOnAxis(Box2DCollider b1, Box2DCollider b2, Vector2 axis) {
        Vector2 interval1 = getInterval(b1, axis);
        Vector2 interval2 = getInterval(b2, axis);
        return ((interval2.xPos <= interval1.yPos) && (interval1.xPos <= interval2.yPos));
    }

    private static Vector2 getInterval(AABBCollider rect, Vector2 axis) {
        Vector2 result = new Vector2(0, 0);

        Vector2 min = rect.getMin();
        Vector2 max = rect.getMax();

        Vector2[] vertices = {
                new Vector2(min.xPos, min.yPos), new Vector2(min.xPos, max.yPos),
                new Vector2(max.xPos, min.yPos), new Vector2(max.xPos, max.yPos)
        };

        result.xPos = axis.dot(vertices[0]);
        result.yPos = result.xPos;
        for (int i=1; i < 4; i++) {
            float projection = axis.dot(vertices[i]);
            if (projection < result.xPos) {
                result.xPos = projection;
            }
            if (projection > result.yPos) {
                result.yPos = projection;
            }
        }
        return result;
    }

    private static Vector2 getInterval(Box2DCollider rect, Vector2 axis) {
        Vector2 result = new Vector2(0, 0);

        Vector2[] vertices = rect.getVertices();

        result.xPos = axis.dot(vertices[0]);
        result.yPos = result.xPos;
        for (int i=1; i < 4; i++) {
            float projection = axis.dot(vertices[i]);
            if (projection < result.xPos) {
                result.xPos = projection;
            }
            if (projection > result.yPos) {
                result.yPos = projection;
            }
        }
        return result;
    }
}
