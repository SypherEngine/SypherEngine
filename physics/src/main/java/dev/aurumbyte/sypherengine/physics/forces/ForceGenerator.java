package dev.aurumbyte.sypherengine.physics.forces;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;

public interface ForceGenerator {
    void updateForce(RigidBody2D rigidBody, float deltaTime);
}
