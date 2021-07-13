package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.GameRenderer;

public class GameManager implements IGame {
    public GameManager(){

    }

    public void update(SypherEngine engine, float deltaTime) {

    }

    public void render(SypherEngine engine, GameRenderer renderer) {

    }

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new GameManager());
        engine.start();
    }
}
