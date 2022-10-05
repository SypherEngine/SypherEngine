package dev.aurumbyte.sypherengine.physics.rigidbody;

import dev.aurumbyte.sypherengine.physics.primitives.AABBCollider;
import dev.aurumbyte.sypherengine.physics.primitives.CircleCollider;
import dev.aurumbyte.sypherengine.physics.primitives.Collider2D;
import dev.aurumbyte.sypherengine.util.math.Vector2;

public class Collisions {
    public static CollisionManifold findCollisionFeatures(Collider2D a, Collider2D b) {
        if (a instanceof CircleCollider c1 && b instanceof CircleCollider c2)
            return findCollisionFeatures(c1, c2);
        //if (a instanceof AABBCollider a1 && b instanceof AABBCollider a2)
        //    return findCollisionFeatures(a1, a2);
        //if (a instanceof CircleCollider c && b instanceof AABBCollider box)
        //    return findCollisionFeatures(box, c);



        else assert false : "Unknown collider '" + a.getClass() + "' vs '" + b.getClass() + "'";


        return null;
    }

    public static CollisionManifold findCollisionFeatures(CircleCollider a, CircleCollider b) {
        CollisionManifold result = new CollisionManifold();
        float sumRadii = a.getRadius() + b.getRadius();
        Vector2 distance = new Vector2(b.getCenter()).subtract(a.getCenter());
        if (distance.lengthSquared() - (sumRadii * sumRadii) > 0) {
            return result;
        }

        // Multiply by 0.5 because we want to separate each circle the same
        // amount. Consider updating to factor in the momentum and velocity
        float depth = Math.abs(distance.length() - sumRadii) * 0.5f;
        Vector2 normal = new Vector2(distance);
        normal.normalize();
        float distanceToPoint = a.getRadius() - depth;
        Vector2 contactPoint = new Vector2(a.getCenter()).add(
                new Vector2(normal).multiply(distanceToPoint));

        result = new CollisionManifold(normal, depth);
        result.addContactPoint(contactPoint);
        return result;
    }

    public static CollisionManifold findCollisionFeatures(AABBCollider a, AABBCollider b){
        //TODO: AABB v AABB collision resolution
        return null;
    }

    public static CollisionManifold findCollisionFeatures(CircleCollider c, AABBCollider b){
        //TODO: AABB vs Circle collision resolution
        return null;
    }
}
