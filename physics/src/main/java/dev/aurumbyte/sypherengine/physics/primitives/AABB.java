/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class AABB {
    private Vector2 size = new Vector2();
    private final Vector2 halfSize;
    private RigidBody2D rigidBody = null;

    public AABB() {
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public AABB(Vector2 min, Vector2 max) {
        this.size = new Vector2(max).subtract(min);
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public Vector2 getMin() {
        return new Vector2(this.rigidBody.getPosition()).subtract(this.halfSize);
    }

    public Vector2 getMax() {
        return new Vector2(this.rigidBody.getPosition()).add(this.halfSize);
    }
}
