/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Circle {
    private float radius = 1.0f;
    private RigidBody2D rigidBody = null;

    public Circle(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public Vector2 getCenter() {
        return rigidBody.getPosition();
    }
}
