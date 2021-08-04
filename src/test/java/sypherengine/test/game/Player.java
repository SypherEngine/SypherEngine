package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.awt.event.KeyEvent;

public class Player extends GameObject {
    int speed = 100;

    public Player(float xPos, float yPos){
        this.tag = "Player";
        this.xPos = xPos * 16;
        this.yPos = yPos * 16;
        this.width = 50;
        this.height = 50;
    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        if(engine.getKeyBoardInput().isKey(KeyEvent.VK_W)) yPos -= deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(KeyEvent.VK_A)) xPos -= deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(KeyEvent.VK_S)) yPos += deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(KeyEvent.VK_D)) xPos += deltaTime * speed;
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawFilledRect((int)xPos, (int)yPos, width, height, 0xffff0fff);
    }
}
