package box2d.playground;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class PhysicsWorld {
    static Vec2 gravity = new Vec2(0.0f, -10.0f);
    static World world = new World(gravity);

    //iterations
    static float timeStep = 1.0f / 60.0f;
    static int velocityIterations = 6;
    static int positionIterations = 2;

    //ground
    public static BodyDef groundBodyDef = new BodyDef();

    public static void setBodiesInWorld(){

    }

    public static void main(String[] args){
        //ground
        groundBodyDef.position.set(0.0f, -10.0f);
        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(50.0f, 10.0f);

        groundBody.createFixture(groundBox, 0.0f);

        // Box to collide with the world

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(0.0f, 4.0f);



        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(1.0f, 1.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;

        Body body = world.createBody(bodyDef);
        body.createFixture(fixtureDef);

        for(int i = 0; i < 60; ++i){

        }
    }
}
