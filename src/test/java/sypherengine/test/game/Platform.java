package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.physics.AABB;
import dev.aurumbyte.sypherengine.physics.Physics;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class Platform extends GameObject<Game> {
    private int color = (int) (Math.random() * Integer.MAX_VALUE);
    private boolean debugCollision = false;
    private String message;

    public Platform(){
        this.tag = "Platform";
        this.width = 32;
        this.height = 16;
        this.padding = 0;
        this.paddingTop = 0;
        this.xPos = 380;
        this.yPos = 172;

        this.addComponent(new AABB<>(this));
    }

    public Platform(int x, int y){
        this.tag = "Platform";
        this.width = 32;
        this.height = 16;
        this.padding = 0;
        this.paddingTop = 0;
        this.xPos = x;
        this.yPos = y;

        this.addComponent(new AABB<>(this));
    }

    @Override
    public void init() {

    }

    @Override
    public void update(SypherEngine engine, Game gameManager, float deltaTime) {
        this.updateComponents(engine, gameManager, deltaTime);
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawFilledRect((int)xPos, (int)yPos, width, height, color);
        this.renderComponents(engine, renderer);
    }

    @Override
    public void event(GameObject<Game> other) {
        if(Physics.notCollided) color = (int) (Math.random() * Integer.MAX_VALUE);
        if(debugCollision){
            System.out.println(message);
        }
    }

    public void setDebugCollison(boolean debugCollision, String message){
        this.debugCollision = debugCollision;
        this.message = message;
    }
}
