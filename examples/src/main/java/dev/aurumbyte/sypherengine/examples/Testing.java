package dev.aurumbyte.sypherengine.examples;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.physics.PhysicsSystem2D;
import dev.aurumbyte.sypherengine.physics.primitives.AABBCollider;
import dev.aurumbyte.sypherengine.physics.primitives.CircleCollider;
import dev.aurumbyte.sypherengine.physics.rigidbody.RigidBody2D;
import dev.aurumbyte.sypherengine.util.DrawUtil;
import dev.aurumbyte.sypherengine.util.math.Transform;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.paint.Color;

public class Testing extends GameManager {
    Vector2 playerPos = new Vector2(250, 200);
    Transform rawTransform = new Transform();
    Transform rawTransform2 = new Transform();
    InputHandler inputHandler = getInputHandler();
    RigidBody2D rigidBody = new RigidBody2D();
    RigidBody2D rigidBody2 = new RigidBody2D();
    CircleCollider circleCollider, circleCollider2;
    AABBCollider aabbCollider, aabbCollider2;
    PhysicsSystem2D physicsSystem = new PhysicsSystem2D(1/60f, new Vector2(0, 40));

    @Override
    public void init(SypherEngine engine) {
        rawTransform.setPosition(new Vector2(300, 100));
        rawTransform2.setPosition(new Vector2(300, 250));
        rigidBody.setRawTransform(rawTransform);
        rigidBody2.setRawTransform(rawTransform2);

        rigidBody.setMass(100f);
        rigidBody2.setMass(200f);

        circleCollider = new CircleCollider(10.0f);
        circleCollider.setRigidBody(rigidBody);

        circleCollider2 = new CircleCollider(20.0f);
        circleCollider2.setRigidBody(rigidBody2);

        rigidBody.setCollider(circleCollider);
        rigidBody2.setCollider(circleCollider2);

        physicsSystem.addRigidBody(rigidBody, true);
        physicsSystem.addRigidBody(rigidBody2, false);
    }

    @Override
    public void update(float deltaTime) {
        physicsSystem.update(deltaTime);
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawCircle(DrawUtil.createCircle(rigidBody.getPosition(), 10), Color.BLUE);
        renderer.drawCircle(DrawUtil.createCircle(rigidBody2.getPosition(), 20), Color.RED);
    }

    public static void main(String[] args){
        EngineConfig engineConfig = new EngineConfig();
        engineConfig.withTitle("Test").withWindowResolution(1000, 700);

        SypherEngine.init(new Testing(), engineConfig);
        SypherEngine.run();
    }
}
