package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.GameRenderer;

public class GameContainer implements IGame {
    // This class is just for tests... not for the actual engine... (yet)
    
    private Image image;

    public GameContainer(){
        image = new Image("/input.png");
    }

    public void update(SypherEngine engine, float deltaTime) {

    }

    public void render(SypherEngine engine, GameRenderer renderer) {
        renderer.drawImage(image, engine.getMouseInput().getMouseX(), engine.getMouseInput().getMouseY());
    }

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new GameContainer());
        engine.start();
    }
}
