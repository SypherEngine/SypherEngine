package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.gameUtils.input.Keys;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class Player extends GameObject<Game> {
    int speed = 100;

    public Player(float xPos, float yPos){
        this.tag = "Player";
        this.xPos = xPos * 16;
        this.yPos = yPos * 16;
        this.width = 16;
        this.height = 16;
    }

    public void update(SypherEngine engine, Game game, float deltaTime) {
        if(engine.getKeyBoardInput().isKey(Keys.W)) yPos -= deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(Keys.A)) xPos -= deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(Keys.S)) yPos += deltaTime * speed;
        if(engine.getKeyBoardInput().isKey(Keys.D)) xPos += deltaTime * speed;
    }

    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawFilledRect((int)xPos, (int)yPos, width, height, 0xff00ffb4);
    }
}
