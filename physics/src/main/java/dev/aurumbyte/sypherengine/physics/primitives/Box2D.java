/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Box2D {
    private Vector2 size = new Vector2();
    private Vector2 halfSize;
    private RigidBody2D rigidBody;

    public Box2D() {
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public Box2D(Vector2 min, Vector2 max) {
        this.size = new Vector2(max).subtract(min);
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public Vector2 getMin() {
        return new Vector2(this.rigidBody.getPosition()).subtract(this.halfSize);
    }

    public Vector2 getMax() {
        return new Vector2(this.rigidBody.getPosition()).add(this.halfSize);
    }

    public Vector2[] getVertices() {
        Vector2 min = getMin();
        Vector2 max = getMax();

        Vector2[] vertices = {
            new Vector2(min.xPos, min.yPos), new Vector2(min.xPos, max.yPos),
            new Vector2(max.xPos, min.yPos), new Vector2(max.xPos, max.yPos)
        };

        if (rigidBody.getRotation() != 0.0f) {
            for (Vector2 vertex : vertices) {
                // TODO: ROTATE IMPLEMENT
                // Rotates vertex(Vector2) about center(Vector2) by rotation (float rotation in degrees)
                // Mathf.rotate(vertex, this.rigidBody.getPosition(), this.rigidBody.getRotation());
            }
        }

        return vertices;
    }

    public RigidBody2D getRigidBody() {
        return this.rigidBody;
    }
}
