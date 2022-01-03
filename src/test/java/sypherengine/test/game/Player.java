package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.gameUtils.input.Keys;
import dev.aurumbyte.sypherengine.physics.AABB;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.image.ImageTile;

public class Player extends GameObject<Game> {
    int tileX, tileY;
    float offX, offY;

    private int direction = 0;
    private float animation = 0;
    int speed = 100;
    float fallDistance, fallSpeed = 10f;
    boolean ground = false;
    boolean groundLast = false;
    float jump = -4;

    private final ImageTile playerImage = new ImageTile("/playerTileset.png", 16, 16);

    public Player(float xPos, float yPos){
        this.tag = "Player";
        this.tileX = (int)xPos;
        this.tileY = (int)yPos;
        this.offX = 1;
        this.offY = 1;
        this.xPos = xPos * Game.TS;
        this.yPos = yPos * Game.TS;
        this.width = Game.TS - 10;
        this.height = Game.TS - 2;

        this.padding = 5;
        this.paddingTop = 2;

        this.addComponent(new AABB<>(this));
    }

    @Override
    public void init(){

    }

    @Override
    public void update(SypherEngine engine, Game game, float deltaTime) {
        /*
        * =======================================
        * LEFT AND RIGHT COLLISIONS
        * =======================================
        */
        /* //TODO: Fix Horizntal axis
            if(engine.getKeyBoardInput().horizontalAxis()){
                System.out.println(engine.getKeyBoardInput().getMovement());
                if(game.getCollision(tileX + 1, tileY)) {
                    if(offX < 0){
                        offX += (deltaTime * speed) * engine.getKeyBoardInput().getMovement();
                        if(offX < 0) offX = 0;
                    } else offX = 0;
                } else offX += (deltaTime * speed) * 1;

                if(game.getCollision(tileX - 1, tileY)) {
                    if(offX > 0){
                        offX += (deltaTime * speed) * engine.getKeyBoardInput().getMovement();
                        if(offX > 0) offX = 0;
                    } else offX = 0;
                } else offX += (deltaTime * speed) * engine.getKeyBoardInput().getMovement();
            }
         */
            if(engine.getKeyBoardInput().isKey(Keys.D)){

                if(game.getCollision(tileX + 1, tileY) || game.getCollision(tileX + 1, tileY + (int)(Math.signum((int) offY)))) {
                    offX += deltaTime * speed;
                    if(offX > padding) offX = padding;

                } else offX += deltaTime * speed;
            }

            if(engine.getKeyBoardInput().isKey(Keys.A)){
                direction = 1;
                if(game.getCollision(tileX - 1, tileY) || game.getCollision(tileX - 1, tileY + (int)(Math.signum((int) offY)))) {
                    offX -= deltaTime * speed;
                    if(offX < -(padding)) offX = -(padding);

                } else offX -= deltaTime * speed;
            }

        /*
        * =======================================
        * JUMP AND GRAVITY
        * =======================================
        */
        fallDistance += fallSpeed * deltaTime;

        if(engine.getKeyBoardInput().isKeyDown(Keys.W) && ground){
            fallDistance = jump;
            ground = false;
        }

        offY += fallDistance;

        if(fallDistance < 0){
            if ((game.getCollision(tileX, tileY - 1) || game.getCollision(tileX + (int) (Math.signum((int)Math.abs(offX) > padding ? offX : 0)), tileY - 1)) && offY < -paddingTop) {
                fallDistance = 0;
                offY = -paddingTop;
            }
        }

        if(fallDistance > 0) {
            if ((game.getCollision(tileX, tileY + 1) || game.getCollision(tileX + (int) (Math.signum((int) Math.abs(offX) > padding ? offX : 0)), tileY + 1)) && offY > 0) {
                fallDistance = 0;
                offY = 0;
                ground = true;
            }
        }

        /*
         * ========================================
         * ANIMATING PLAYER
         * ========================================
         */
        int animationSpeed = 8;

        if(engine.getKeyBoardInput().isKey(Keys.D)){
            direction = 0;
            animation += deltaTime * animationSpeed;

            // The player animation tileset has only 4 files, hence a check
            if(animation >= 4) animation = 0;
        }
        else if(engine.getKeyBoardInput().isKey(Keys.A)){
            direction = 1;
            animation += deltaTime * animationSpeed;

            // The player animation tileset has only 4 files, hence a check
            if(animation >= 4) animation = 0;
        }
        else {
            animation = 0;
        }

        if(!ground) animation = 1;
        if(ground && !groundLast) animation = 2;

        groundLast = ground;

        /*
        * ========================================
        * MISCELLANEOUS
        * ========================================
        */

        if(offY > Game.TS / 2){
            tileY++;
            offY -= Game.TS;
        }

        if(offY < -Game.TS / 2){
            tileY--;
            offY += Game.TS;
        }

        if(offX > Game.TS / 2){
            tileX++;
            offX -= Game.TS;
        }
        if(offX < -Game.TS / 2){
            tileX--;
            offX += Game.TS;
        }

        if(engine.getKeyBoardInput().isKeyDown(Keys.UP)) game.addObject(new Bullet(tileX, tileY, (int)(offX + width / 2), (int)(offY + height / 2), 0));
        if(engine.getKeyBoardInput().isKeyDown(Keys.RIGHT)) game.addObject(new Bullet(tileX, tileY, (int)(offX + width / 2), (int)(offY + height / 2), 1));
        if(engine.getKeyBoardInput().isKeyDown(Keys.DOWN)) game.addObject(new Bullet(tileX, tileY, (int)(offX + width / 2), (int)(offY + height / 2), 2));
        if(engine.getKeyBoardInput().isKeyDown(Keys.LEFT)) game.addObject(new Bullet(tileX, tileY, (int)(offX + width / 2), (int)(offY + height / 2), 3));


        xPos = tileX * Game.TS + offX;
        yPos = tileY * Game.TS + offY;

        this.updateComponents(engine, game, deltaTime);
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        //renderer.drawFilledRect((int)xPos, (int)yPos, width, height, 0xff00ffb4);
        renderer.drawImageTile(playerImage, (int)xPos, (int)yPos, (int)animation, direction);
        this.renderComponents(engine, renderer);
    }

    @Override
    public void event(GameObject<Game> other) {
        if(other.getTag().equalsIgnoreCase("Platform")){

        }
    }
}
