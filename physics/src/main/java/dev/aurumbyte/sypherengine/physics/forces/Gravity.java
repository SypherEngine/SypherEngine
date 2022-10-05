package dev.aurumbyte.sypherengine.physics.forces;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Gravity implements ForceGenerator{
    Vector2 gravity;

    public Gravity(Vector2 force){
        this.gravity = new Vector2(force);
    }

    @Override
    public void updateForce(RigidBody2D rigidBody, float deltaTime) {
        rigidBody.addForce(new Vector2(gravity).multiply(rigidBody.getMass()));
    }
}
