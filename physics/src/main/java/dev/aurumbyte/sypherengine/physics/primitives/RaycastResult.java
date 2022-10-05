package dev.aurumbyte.sypherengine.physics.primitives;

import dev.aurumbyte.sypherengine.util.math.Vector2;

public class RaycastResult {
    Vector2 point;
    Vector2 normal;
    float t;
    boolean hit;

    public RaycastResult(){
        this.point = new Vector2();
        this.normal = new Vector2();
        this.t = -1f;
        this.hit = false;
    }

    public void init(Vector2 point, Vector2 normal, float t, boolean hit){
        this.point = point;
        this.normal = normal;
        this.t = t;
        this.hit = hit;
    }

    public static void reset(RaycastResult raycastResult){
        if(raycastResult != null) {
            raycastResult.normal = new Vector2();
            raycastResult.point = new Vector2();
            raycastResult.hit = false;
            raycastResult.t = -1;
        }
    }
}
