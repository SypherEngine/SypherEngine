package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.io.IOException;

public class Game extends GameManager<Game> {
    public Game(){
        gameObjects.add(new Player(2, 2));
        gameManager = this;
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
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        gameObjects.forEach(gameObject -> gameObject.render(engine, renderer));
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
