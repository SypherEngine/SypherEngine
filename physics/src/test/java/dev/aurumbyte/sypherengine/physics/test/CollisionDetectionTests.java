/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.test;

import dev.aurumbyte.sypherengine.physics.primitives.Box2DCollider;
import dev.aurumbyte.sypherengine.physics.primitives.CircleCollider;
import dev.aurumbyte.sypherengine.physics.rigidbody.IntersectionDetector2D;
import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import dev.aurumbyte.sypherengine.util.primitives.Line;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollisionDetectionTests {
    @Test
    public void pointOnLineTests(){
        Vector2 lineStart = new Vector2();
        Vector2 lineEnd = new Vector2(12, 4);

        Vector2 point = new Vector2();

        assert IntersectionDetector2D.pointOnLine(point, new Line(lineStart, lineEnd));
    }

    @Test
    public void pointOnVerticalLineTests(){
        Vector2 lineStart = new Vector2();
        Vector2 lineEnd = new Vector2(0, 10);

        Vector2 point = new Vector2(0, 5);

        assert IntersectionDetector2D.pointOnLine(point, new Line(lineStart, lineEnd));
    }

    private final float EPSILON = 0.000001f;

    // ============================================================================================
    // Line Intersection tests
    // ============================================================================================
    @Test
    public void pointOnLineShouldReturnTrueTest() {
        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
        Vector2 point = new Vector2(0, 0);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    //@Test
    //public void pointOnLineShouldReturnTrueTestTwo() {
    //    Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
    //    Vector2 point = new Vector2(12, 4);
    //
    //    assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    //}

    @Test
    public void pointOnVerticalLineShouldReturnTrue() {
        Line line = new Line(new Vector2(0, 0), new Vector2(0, 10));
        Vector2 point = new Vector2(0, 5);

        boolean result = IntersectionDetector2D.pointOnLine(point, line);
        assertTrue(result);
    }

    @Test
    public void pointOnLineShouldReturnTrueTestOne() {
        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
        Vector2 point = new Vector2(0, 0);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnLineShouldReturnTrueTestTwo() {
        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
        Vector2 point = new Vector2(6, 2);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnLineShouldReturnFalseTestOne() {
        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
        Vector2 point = new Vector2(4, 2);

        assertFalse(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnLineShouldReturnTrueTestThree() {
        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
        Vector2 point = new Vector2(10, 10);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnLineShouldReturnTrueTestFour() {
        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
        Vector2 point = new Vector2(16, 12);

        assertTrue(IntersectionDetector2D.pointOnLine(point, line));
    }

    @Test
    public void pointOnLineShouldReturnFalseTestTwo() {
        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
        Vector2 point = new Vector2(14, 12);

        assertFalse(IntersectionDetector2D.pointOnLine(point, line));
    }

//    TODO: SHOULD THESE BE IMPLEMENTED
//    @Test
//    public void closestPointToLineTestOne() {
//        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
//        Vector2 point = new Vector2(6, 2);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(6, 2);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToLineTestTwo() {
//        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
//        Vector2 point = new Vector2(13, 3);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(12, 4);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToLineTestThree() {
//        Line line = new Line(new Vector2(0, 0), new Vector2(12, 4));
//        Vector2 point = new Vector2(7, 4);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(7.5f, 2.5f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToLineTestFour() {
//        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
//        Vector2 point = new Vector2(16, 12);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(16, 12);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToLineTestFive() {
//        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
//        Vector2 point = new Vector2(23, 13);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(22, 14);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToLineTestSix() {
//        Line line = new Line(new Vector2(10, 10), new Vector2(22, 14));
//        Vector2 point = new Vector2(17, 14);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, line);
//        Vector2 actualClosestPoint = new Vector2(17.5f, 12.5f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }

    // =========================================================================================================
    // Ray2Dcast IntersectionDetector2D tests
    // =========================================================================================================
    // TODO: SHOULD THESE BE IMPLEMENTED?
//    @Test
//    public void pointOnRayShouldReturnTrueTestOne() {
//        Ray2D ray = new Ray2D(new Vector2(0), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(0, 0);
//
//        assertTrue(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnTrueTestTwo() {
//        Ray2D ray = new Ray2D(new Vector2(0), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(6, 2);
//
//        assertTrue(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnFalseTestOne() {
//        Ray2D ray = new Ray2D(new Vector2(0), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(-6, -2);
//
//        assertFalse(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnFalseTestTwo() {
//        Ray2D ray = new Ray2D(new Vector2(0), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(4, 2);
//
//        assertFalse(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnTrueTestThree() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(10, 10);
//
//        assertTrue(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnTrueTestFour() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(16, 12);
//
//        assertTrue(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnFalseTestThree() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(-6 + 10, -2 + 10);
//
//        assertFalse(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void pointOnRayShouldReturnFalseTestFour() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(14, 12);
//
//        assertFalse(IntersectionDetector2D.pointOnRay(point, ray));
//    }
//
//    @Test
//    public void closestPointToRayTestOne() {
//        Ray2D ray = new Ray2D(new Vector2(0, 0), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(-1, -1);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(0, 0);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToRayTestTwo() {
//        Ray2D ray = new Ray2D(new Vector2(0, 0), new Vector2((float)(3.0 / Math.sqrt(10f)), (float)(1.0 / Math.sqrt(10f))));
//        Vector2 point = new Vector2(6, 2);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(6, 2);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRayTestThree() {
//        Ray2D ray = new Ray2D(new Vector2(0, 0), new Vector2((float)(3.0 / Math.sqrt(10f)), (float)(1.0 / Math.sqrt(10f))));
//        Vector2 point = new Vector2(7, 4);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(7.5f, 2.5f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRayTestFour() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2(0.948683f, 0.316228f));
//        Vector2 point = new Vector2(9, 9);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(10, 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToRayTestFive() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2((float)(3.0 / Math.sqrt(10f)), (float)(1.0 / Math.sqrt(10f))));
//        Vector2 point = new Vector2(16, 12);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(16, 12);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRayTestSix() {
//        Ray2D ray = new Ray2D(new Vector2(10, 10), new Vector2((float)(3.0 / Math.sqrt(10f)), (float)(1.0 / Math.sqrt(10f))));
//        Vector2 point = new Vector2(17, 14);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, ray);
//        Vector2 actualClosestPoint = new Vector2(17.5f, 12.5f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }

    // =========================================================================================================
    // CircleCollider intersection tester tests
    // =========================================================================================================
    @Test
    public void pointInCircleColliderShouldReturnTrueTestOne() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(3, -2);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertTrue(result);
    }

    @Test
    public void pointInCircleColliderShouldReturnTrueTestTwo() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(-4.9f, 0);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertTrue(result);
    }

    @Test
    public void pointInCircleColliderShouldReturnFalseTestOne() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(-6, -6);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertFalse(result);
    }

    @Test
    public void pointInCircleColliderShouldReturnTrueTestFour() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(3 + 10, -2 + 10);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertTrue(result);
    }

    @Test
    public void pointInCircleColliderShouldReturnTrueTestFive() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(-4.9f + 10, 0 + 10);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertTrue(result);
    }

    @Test
    public void pointInCircleColliderShouldReturnFalseTestTwo() {
        CircleCollider CircleCollider = new CircleCollider();
        CircleCollider.setRadius(5f);
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        CircleCollider.setRigidBody(body);

        Vector2 point = new Vector2(-6 + 10, -6 + 10);

        boolean result = IntersectionDetector2D.pointInCircleCollider(point, CircleCollider);
        assertFalse(result);
    }

//    TODO: IMPLEMENT THESE
//    @Test
//    public void closestPointToCircleColliderTestOne() {
//        CircleCollider CircleCollider = new CircleCollider();
//        CircleCollider.setRadius(1f);
//        RigidBody2D body = new RigidBody2D();
//        CircleCollider.setRigidBody(body);
//
//        Vector2 point = new Vector2(5, 0);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, CircleCollider);
//        Vector2 actualClosestPoint = new Vector2(1, 0);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToCircleColliderTestTwo() {
//        CircleCollider CircleCollider = new CircleCollider();
//        CircleCollider.setRadius(1f);
//        RigidBody2D body = new RigidBody2D();
//        CircleCollider.setRigidBody(body);
//
//        Vector2 point = new Vector2(2.5f, -2.5f);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, CircleCollider);
//        Vector2 actualClosestPoint = new Vector2(0.5773502f, -0.5773502f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToCircleColliderTestThree() {
//        CircleCollider CircleCollider = new CircleCollider();
//        CircleCollider.setRadius(1f);
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10));
//        CircleCollider.setRigidBody(body);
//
//        Vector2 point = new Vector2(5 + 10, 0 + 10);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, CircleCollider);
//        Vector2 actualClosestPoint = new Vector2(1 + 10, 0 + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToCircleColliderTestFour() {
//        CircleCollider CircleCollider = new CircleCollider();
//        CircleCollider.setRadius(1f);
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10));
//        CircleCollider.setRigidBody(body);
//
//        Vector2 point = new Vector2(2.5f + 10, -2.5f + 10);
//
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, CircleCollider);
//        Vector2 actualClosestPoint = new Vector2(0.5773502f + 10, -0.5773502f + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }

    // =========================================================================================================
    // Box2DCollider intersection tester tests
    // =========================================================================================================
    @Test
    public void pointInBox2DColliderShouldReturnTrueTestOne() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        box.setRigidBody(body);

        Vector2 point = new Vector2(4, 4.3f);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInBox2DColliderShouldReturnTrueTestTwo() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        box.setRigidBody(body);

        Vector2 point = new Vector2(-4.9f, -4.9f);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInBox2DColliderShouldReturnFalseTestOne() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        box.setRigidBody(body);

        Vector2 point = new Vector2(0, 5.1f);

        assertFalse(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInBox2DColliderShouldReturnTrueTestThree() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        box.setRigidBody(body);

        Vector2 point = new Vector2(4 + 10, 4.3f + 10);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInBox2DColliderShouldReturnTrueTestFour() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        box.setRigidBody(body);

        Vector2 point = new Vector2(-4.9f + 10, -4.9f + 10);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInBox2DColliderShouldReturnFalseTestTwo() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10));
        box.setRigidBody(body);

        Vector2 point = new Vector2(10, 5.1f + 10);

        assertFalse(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedBox2DColliderShouldReturnTrueTestOne() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(0, 0), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-1, -1);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedShouldReturnTrueTestTwo() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-3.43553390593f, 3.43553390593f);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedShouldReturnFalseTestOne() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-3.63553390593f, 3.63553390593f);

        assertFalse(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedBox2DColliderShouldReturnTrueTestThree() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-1 + 10, -1 + 10);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedShouldReturnTrueTestFour() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));
        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-3.43553390593f + 10, 3.43553390593f + 10);

        assertTrue(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }

    @Test
    public void pointInRotatedShouldReturnFalseTestTwo() {
        Box2DCollider box = new Box2DCollider();
        box.setSize(new Vector2(10, 10));

        RigidBody2D body = new RigidBody2D();
        body.setTransform(new Vector2(10, 10), 45);
        box.setRigidBody(body);

        Vector2 point = new Vector2(-3.63553390593f + 10, 3.63553390593f + 10);

        assertFalse(IntersectionDetector2D.pointInBox2DCollider(point, box));
    }


//    TODO: IMPLEMENT THESE FUNCIONS
//    @Test
//    public void closestPointToBox2DColliderTestOne() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(0, 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(0, 5);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToBox2DColliderTestTwo() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(-6, -4);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(-5, -4);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToBox2DColliderTestThree() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(3, -4);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(3, -4);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToBox2DColliderTestFour() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10));
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(0 + 10, 10 + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(0 + 10, 5 + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToBox2DColliderTestFive() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10));
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(-6 + 10, -4 + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(-5 + 10, -4 + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToBox2DColliderTestSix() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10));
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(3 + 10, -4 + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(3 + 10, -4 + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestOne() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(10, 0);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(7.07106781187f, 0);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestTwo() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(-5.5355339f, -5.5355339f);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(-3.5355339f, -3.5355339f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestThree() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(0, 7.07106781187f);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(0, 7.07106781187f);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestFour() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(10 + 10, 0 + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(7.07106781187f + 10, 0 + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestFive() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(-5.5355339f + 10, -5.5355339f + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(-3.5355339f + 10, -3.5355339f + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
//
//    @Test
//    public void closestPointToRotatedBox2DColliderTestSix() {
//        Box2DCollider box = new Box2DCollider();
//        box.setSize(new Vector2(10));
//        RigidBody2D body = new RigidBody2D();
//        body.setTransform(new Vector2(10), 45);
//        box.setRigidBody(body);
//
//        Vector2 point = new Vector2(0 + 10, 7.07106781187f + 10);
//        Vector2 calculatedClosestPoint = IntersectionDetector2D.closestPoint(point, box);
//        Vector2 actualClosestPoint = new Vector2(0 + 10, 7.07106781187f + 10);
//
//        assertTrue(JMath.compare(calculatedClosestPoint, actualClosestPoint, EPSILON));
//    }
}
