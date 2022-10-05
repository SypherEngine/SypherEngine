package dev.aurumbyte.sypherengine.physics.rigidbody;

import dev.aurumbyte.sypherengine.physics.primitives.Collider2D;
import dev.aurumbyte.sypherengine.util.math.Transform;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class RigidBody2D {
    private Transform rawTransform;
    private Collider2D collider;

    private Vector2 position = new Vector2();
    private float rotation = 0.0f;
    private float mass = 0.0f;
    private float inverseMass = 0.0f;

    private Vector2 forceAccum = new Vector2();
    private Vector2 linearVelocity = new Vector2();
    private float angularVelocity = 0.0f;
    private float linearDamping = 0.0f;
    private float angularDamping = 0.0f;

    // Coefficient of restitution
    private float restitution = 1.0f;

    private boolean fixedRotation = false;

    public Vector2 getPosition() {
        return position;
    }

    public void physicsUpdate(float dt) {
        if (this.mass == 0.0f) return;

        // Calculate linear velocity
        Vector2 acceleration = new Vector2(forceAccum).multiply(this.inverseMass);
        linearVelocity.add(acceleration.multiply(dt));

        // Update the linear position
        this.position.add(new Vector2(linearVelocity).multiply(dt));

        syncCollisionTransforms();
        clearAccumulators();
    }

    public void syncCollisionTransforms() {
        if (rawTransform != null) {
            rawTransform.getPosition().set(this.position);
        }
    }

    public void clearAccumulators() {
        this.forceAccum = new Vector2();
    }

    public void setTransform(Vector2 position, float rotation) {
        this.position.set(position);
        this.rotation = rotation;
    }

    public void setTransform(Vector2 position) {
        this.position.set(position);
    }

    public void setLinearVelocity(Vector2 velocity) {
        this.linearVelocity.set(velocity);
    }

    public Vector2 getLinearVelocity() {
        return this.linearVelocity;
    }

    public float getRotation() {
        return rotation;
    }

    public float getMass() {
        return mass;
    }

    public float getInverseMass() {
        return this.inverseMass;
    }

    public void setMass(float mass) {
        this.mass = mass;
        if (this.mass != 0.0f) {
            this.inverseMass = 1.0f / this.mass;
        }
    }

    public boolean hasInfiniteMass() {
        return this.mass == 0.0f;
    }

    public void addForce(Vector2 force) {
        this.forceAccum.add(force);
    }

    public void setRawTransform(Transform rawTransform) {
        this.rawTransform = rawTransform;
        this.position.set(rawTransform.getPosition());
    }

    public void setCollider(Collider2D collider) {
        this.collider = collider;
    }

    public Collider2D getCollider() {
        return this.collider;
    }

    public float restitution() {
        return restitution;
    }

    public void setRestitution(float restitution) {
        this.restitution = restitution;
    }
}
