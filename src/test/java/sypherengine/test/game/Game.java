package sypherengine.test.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.game.IGame;
import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.util.ArrayList;

public class Game implements IGame {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public Game(){
        gameObjects.add(new Player(2, 2));
    }

    @Override
    public void init(SypherEngine engine){
        engine.getRenderer().setAmbientColor(-1);
    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        for(int i = 0; i < gameObjects.size(); i++){
            gameObjects.get(i).update(engine, deltaTime);
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

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new Game());
        engine.start();
    }
}
