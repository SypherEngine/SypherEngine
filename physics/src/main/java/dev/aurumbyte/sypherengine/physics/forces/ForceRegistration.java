package dev.aurumbyte.sypherengine.physics.forces;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;

public class ForceRegistration {
    public ForceGenerator forceGenerator;
    public RigidBody2D rigidBody;

    public ForceRegistration(ForceGenerator forceGenerator, RigidBody2D rigidBody){
        this.forceGenerator = forceGenerator;
        this.rigidBody = rigidBody;
    }

    @Override
    public boolean equals(Object other){
        if(other == null || other.getClass() != ForceRegistration.class) return false;
        ForceRegistration forceRegistration = (ForceRegistration) other;

        return this.rigidBody == forceRegistration.rigidBody && this.forceGenerator == forceRegistration.forceGenerator;
    }
}
