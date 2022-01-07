package box2d.playground;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.math.Unit;
import dev.aurumbyte.sypherengine.utils.Renderer;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Main extends GameManager<Main> {
    //iterations
    static int velocityIterations = 6;
    static int positionIterations = 2;

    //ground
    public static BodyDef groundBodyDef = new BodyDef();
    static Body groundBody;

    //bodies
    Body body;

    @Override
    public void init(SypherEngine engine) {
        engine.getRenderer().setAmbientColor(-1);

        //ground
        groundBodyDef.position.set(-2.0f, -10.0f);
        groundBody = engine.getWorld().createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(50.0f, 10.0f);

        groundBody.createFixture(groundBox, 0.0f);

        // Box to collide with the world

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(-2.0f, -10.0f);



        PolygonShape dynamicBox = new PolygonShape();
        dynamicBox.setAsBox(1.0f, 1.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dynamicBox;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 0.3f;

        body = engine.getWorld().createBody(bodyDef);
        body.createFixture(fixtureDef);

        System.out.printf("%d, %d",(int) Unit.convertMetersToPixels(groundBody.getPosition().x), (int)Unit.convertMetersToPixels(groundBody.getPosition().y));

    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        engine.getWorld().step(deltaTime, velocityIterations, positionIterations);
        Vec2 position = body.getPosition();
        float angle = body.getAngle();

        //System.out.printf("%f %f %f\n", Unit.convertMetersToPixels(position.x), Unit.convertMetersToPixels(position.y), angle);
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawFilledRect((int) Unit.convertMetersToPixels(body.getPosition().x), (int)Unit.convertMetersToPixels(body.getPosition().y), 16, 16, 0xff00ffb4);
        renderer.drawFilledRect((int) Unit.convertMetersToPixels(groundBody.getPosition().x), (int)Unit.convertMetersToPixels(groundBody.getPosition().y), 50, 50, 0xffffffff);
    }

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new Main());

        engine.setWidth(320);
        engine.setHeight(240);
        engine.setScale(3f);

        engine.start();
    }
}
