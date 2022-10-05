/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Mathf;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Box2DCollider extends Collider2D {
    private Vector2 size = new Vector2();
    private Vector2 halfSize = new Vector2();
    private RigidBody2D rigidbody = null;

    public Box2DCollider() {
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public Box2DCollider(Vector2 min, Vector2 max) {
        this.size = new Vector2(max).subtract(min);
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public Vector2 getLocalMin() {
        return new Vector2(this.rigidbody.getPosition()).subtract(this.halfSize);
    }

    public Vector2 getLocalMax() {
        return new Vector2(this.rigidbody.getPosition()).add(this.halfSize);
    }

    public Vector2 getHalfSize() {
        return this.halfSize;
    }

    public Vector2[] getVertices() {
        Vector2 min = getLocalMin();
        Vector2 max = getLocalMax();

        Vector2[] vertices = {
                new Vector2(min.xPos, min.yPos), new Vector2(min.xPos, max.yPos),
                new Vector2(max.xPos, min.yPos), new Vector2(max.xPos, max.yPos)
        };

        if (rigidbody.getRotation() != 0.0f) {
            for (Vector2 vert : vertices) {
                // Rotates point(Vector2) about center(Vector2) by rotation(float in degrees)
                Mathf.rotate(vert, this.rigidbody.getRotation(), this.rigidbody.getPosition());
            }
        }

        return vertices;
    }

    public RigidBody2D getRigidBody() {
        return this.rigidbody;
    }

    public void setRigidBody(RigidBody2D rigidbody) {
        this.rigidbody = rigidbody;
    }

    public void setSize(Vector2 size) {
        this.size.set(size);
        this.halfSize.set(size.xPos / 2.0f, size.yPos / 2.0f);
    }
}
