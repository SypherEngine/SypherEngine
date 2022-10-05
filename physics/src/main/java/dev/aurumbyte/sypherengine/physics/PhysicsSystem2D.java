package dev.aurumbyte.sypherengine.physics;

import dev.aurumbyte.sypherengine.physics.forces.ForceRegistry;
import dev.aurumbyte.sypherengine.physics.forces.Gravity;
import dev.aurumbyte.sypherengine.physics.primitives.Collider2D;
import dev.aurumbyte.sypherengine.physics.rigidbody.CollisionManifold;
import dev.aurumbyte.sypherengine.physics.rigidbody.Collisions;
import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSystem2D {
    ForceRegistry forceRegistry;
    List<RigidBody2D> rigidBodies = new ArrayList<>();
    List<RigidBody2D> rigidBodies1 = new ArrayList<>(), rigidBodies2 = new ArrayList<>();
    List<CollisionManifold> collisionManifolds = new ArrayList<>();
    Gravity gravity;

    float fixedUpdate;
    int impulseIteration = 6;

    public PhysicsSystem2D(float fixedUpdateDT, Vector2 gravity){
        this.forceRegistry = new ForceRegistry();
        this.fixedUpdate = fixedUpdateDT;
        this.gravity = new Gravity(gravity);
    }

    public void update(float deltaTime){
        fixedUpdate();
    }

    private void fixedUpdate(){
        rigidBodies1.clear();
        rigidBodies2.clear();
        collisionManifolds.clear();

        int size = rigidBodies.size();
        //Find any collisions

        for(int i = 0; i < size; i++){
            for(int j = i; j < size; j++){
                if(i == j) continue;
                CollisionManifold result = new CollisionManifold();
                RigidBody2D r1 = rigidBodies.get(i);
                RigidBody2D r2 = rigidBodies.get(j);

                Collider2D c1 = r1.getCollider();
                Collider2D c2 = r2.getCollider();

                if(c1 != null && c2 != null && !(r1.hasInfiniteMass() && r2.hasInfiniteMass())){
                    result = Collisions.findCollisionFeatures(c1, c2);
                }

                if(result != null && result.isColliding()){
                    rigidBodies1.add(r1);
                    rigidBodies2.add(r2);
                    collisionManifolds.add(result);
                }
            }
        }

        // Update the forces
        forceRegistry.updateForces(fixedUpdate);

        //Resolve them using impulse resolution
        for(int i = 0; i < impulseIteration; i++){
            for(int x = 0; x < collisionManifolds.size(); x++){
                int jSize = collisionManifolds.get(x).getContactPoints().size();
                for(int j = 0; j < jSize; j++){
                    RigidBody2D r1 = rigidBodies1.get(x);
                    RigidBody2D r2 = rigidBodies2.get(x);
                    applyImpulse(r1, r2, collisionManifolds.get(x));
                }
            }
        }

        //Give the velocities to the rigidbodies
        for(RigidBody2D rigidBody : rigidBodies){
            rigidBody.physicsUpdate(fixedUpdate);
        }
    }

    private void applyImpulse(RigidBody2D a, RigidBody2D b, CollisionManifold m) {
        float invMass_A = a.getInverseMass();
        float invMass_B = b.getInverseMass();
        float invMassSum = invMass_A + invMass_B;

        if(invMassSum == 0.0f) return;

        Vector2 relativeVelocity = new Vector2(b.getLinearVelocity()).subtract(a.getLinearVelocity());
        Vector2 relativeNormal = new Vector2(m.getNormal()).normalize();

        if(relativeVelocity.dot(relativeNormal) > 0) return;

        float e = Math.min(a.restitution(), b.restitution());
        float numerator = (-(1.0f + e) * relativeVelocity.dot(relativeNormal));
        float J = numerator/invMassSum;

        if(m.getContactPoints().size() > 0 && J != 0.0f){
            J /= m.getContactPoints().size();
        }

        Vector2 impulse = new Vector2(relativeNormal).multiply(J);
        a.setLinearVelocity(
                new Vector2(a.getLinearVelocity())
                        .add(new Vector2(impulse)
                                .multiply(invMass_A)
                                .multiply(-1)
                        )
        );

        b.setLinearVelocity(
                new Vector2(b.getLinearVelocity())
                        .add(new Vector2(impulse)
                                .multiply(invMass_B)
                                .multiply(1)
                        )
        );
    }

    public void addRigidBody(RigidBody2D rigidBody, boolean addGravity){
        rigidBodies.add(rigidBody);
        if(addGravity) forceRegistry.add(gravity, rigidBody);
    }
}
