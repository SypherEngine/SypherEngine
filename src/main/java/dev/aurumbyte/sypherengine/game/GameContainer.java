package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.image.ImageRenderer;
import dev.aurumbyte.sypherengine.utils.GameRenderer;

public class GameContainer implements IGame {
    private ImageRenderer imageRenderer;

    public GameContainer(){
        imageRenderer = new ImageRenderer("/input.png");
    }

    public void update(SypherEngine engine, float deltaTime) {

    }

    public void render(SypherEngine engine, GameRenderer renderer) {
        renderer.drawImage(imageRenderer, engine.getMouseInput().getMouseX(), engine.getMouseInput().getMouseY());
    }

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new GameContainer());
        engine.start();
    }
}
