/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class AABBCollider extends Collider2D {
    private Vector2 size = new Vector2();
    private Vector2 halfSize;
    private RigidBody2D rigidBody;

    public AABBCollider() {
        this.halfSize = new Vector2(size).multiply(0.5f);
    }

    public AABBCollider(Vector2 min, Vector2 max) {
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

        return new Vector2[]{
                new Vector2(min.xPos, min.yPos), new Vector2(min.xPos, max.yPos),
                new Vector2(max.xPos, min.yPos), new Vector2(max.xPos, max.yPos)
        };
    }

    public void setRigidBody(RigidBody2D rigidBody) {
        this.rigidBody = rigidBody;
    }

    public void setSize(Vector2 size){
        this.size = size;
        this.halfSize = new Vector2(size.xPos / 2.0f, size.yPos / 2.0f);
    }

    public RigidBody2D getRigidBody() {
        return rigidBody;
    }

    public Vector2 getHalfSize() {
        return halfSize;
    }

    public Vector2 getSize() {
        return size;
    }
}
