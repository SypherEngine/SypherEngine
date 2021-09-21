package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.camera.Camera;
import dev.aurumbyte.sypherengine.utils.image.Image;

import java.io.IOException;

public class Game extends GameManager<Game> {
    public static final int TS = 16;
    private boolean[] collisions;
    private int levelWidth, levelHeight;

    public Game(){
        gameObjects.add(new Player(3, 3));
        gameManager = this;
        loadLevel("/level.png");

        camera = new Camera<>("Player");
    }

    @Override
    public void init(SypherEngine engine){
        engine.getRenderer().setAmbientColor(-1);
    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        for(int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).update(engine, gameManager, deltaTime);
            if(gameObjects.get(i).isDead()){
                gameObjects.remove(i);
                i--;
            }
        }

        camera.update(engine, this, deltaTime);
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        camera.render(engine, renderer);

        for(int y = 0; y < levelHeight; y++){
            for(int x = 0; x < levelWidth; x++){
                if(collisions[x + y * levelWidth]) renderer.drawFilledRect(x * TS, y * TS, TS, TS, 0xff0f0f0f);
                else renderer.drawFilledRect(x * TS, y * TS, TS, TS, 0xfff9f9f9);
            }
        }
        gameObjects.forEach(gameObject -> gameObject.render(engine, renderer));
    }

    public void loadLevel(String path){
        Image level  = new Image(path);
        levelWidth = level.getWidth();
        levelHeight = level.getHeight();

        collisions = new boolean[levelWidth * levelHeight];

        for(int y = 0; y < level.getHeight(); y++){
            for(int x = 0; x < level.getWidth(); x++){
                collisions[x + y * levelWidth] = level.getPixels()[x + y * levelWidth] == 0xff000000;
            }
        }
    }

    public void addObject(GameObject<Game> gameObject){
        gameObjects.add(gameObject);
    }

    public boolean getCollision(int x, int y){
        if(x == 0 || x >= levelWidth || y == 0 || y >= levelHeight) return true;
        return collisions[x + y * levelWidth];
    }

    public static void main(String[] args) throws IOException {
        SypherEngine engine = new SypherEngine(new Game());

        engine.setWidth(320);
        engine.setHeight(240);
        engine.setScale(3f);
        engine.setTitle("TestPlatformer");

        engine.start();
    }
}
