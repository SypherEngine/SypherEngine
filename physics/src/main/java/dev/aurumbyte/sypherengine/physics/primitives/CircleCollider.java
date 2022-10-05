/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class CircleCollider extends Collider2D {
    private float radius = 1.0f;
    private RigidBody2D rigidBody = null;

    public CircleCollider(float radius) {
        this.radius = radius;
    }
    public CircleCollider(){
        this.radius = 0.0f;
    }

    public float getRadius() {
        return radius;
    }

    public Vector2 getCenter() {
        return rigidBody.getPosition();
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setRigidBody(RigidBody2D rigidBody) {
        this.rigidBody = rigidBody;
    }
}
