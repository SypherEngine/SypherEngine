package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class Bullet extends GameObject<Game> {
    int tileX, tileY;
    float offX, offY;

    private final int direction;

    public Bullet(int tileX, int tileY, float offX, float offY, int direction){
        this.direction = direction;
        this.xPos = tileX * Game.TS + offX;;
        this.yPos = tileY * Game.TS + offY;;
        this.tileX = tileX;
        this.tileY = tileY;
        this.offX = offX;
        this.offY = offY;
        this.padding = 0;
        this.paddingTop = 0;

        this.width = 4;
        this.height = 4;
    }

    @Override
    public void init() {

    }

    @Override
    public void update(SypherEngine engine, Game gameManager, float deltaTime){
        float speed = 200;
        switch (direction) {
            case 0 -> offY -= speed * deltaTime;
            case 1 -> offX += speed * deltaTime;
            case 2 -> offY += speed * deltaTime;
            case 3 -> offX -= speed * deltaTime;
        }

        if(offY > Game.TS / 2){
            tileY++;
            offY -= Game.TS;
        }

        if(offY < 0){
            tileY--;
            offY += Game.TS;
        }

        if(offX > Game.TS / 2){
            tileX++;
            offX -= Game.TS;
        }
        if(offX < 0){
            tileX--;
            offX += Game.TS;
        }

        if(gameManager.getCollision(tileX, tileY)) this.isDead = true;

        xPos = tileX * Game.TS + offX;
        yPos = tileY * Game.TS + offY;
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawFilledRect((int)xPos, (int)yPos, 4, 4, 0xffff0000);
    }

    @Override
    public void event(GameObject<Game> other) {

    }
}
