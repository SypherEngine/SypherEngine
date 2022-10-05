package dev.aurumbyte.sypherengine.physics.forces;

import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;

import java.util.ArrayList;
import java.util.List;

public class ForceRegistry {
    List<ForceRegistration> registry;

    public ForceRegistry() {
        this.registry = new ArrayList<>();
    }

    public void add(ForceGenerator forceGenerator, RigidBody2D rigidBody) {
        ForceRegistration forceRegistry = new ForceRegistration(forceGenerator, rigidBody);
        registry.add(forceRegistry);
    }

    public void remove(ForceGenerator forceGenerator, RigidBody2D rigidBody) {
        ForceRegistration forceRegistry = new ForceRegistration(forceGenerator, rigidBody);
        registry.remove(forceRegistry);
    }

    public void clear(){
        registry.clear();
    }

    public void updateForces(float deltaTime){
        for(ForceRegistration forceRegistration: registry){
            forceRegistration.forceGenerator.updateForce(forceRegistration.rigidBody, deltaTime);
        }
    }

    public void zeroForces(){
        for(ForceRegistration forceRegistration: registry){
            //TODO: implement this
            //forceRegistration.rigidBody.zeroForces();
        }
    }
}
