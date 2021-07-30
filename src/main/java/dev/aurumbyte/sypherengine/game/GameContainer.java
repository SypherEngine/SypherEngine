package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class GameContainer implements IGame {
    // This class is just for tests... not for the actual engine... (yet)
    
    private Image image;
    private Image image2;

    public GameContainer(){
        image = new Image("/alpha.png");
        image2 = new Image("/test.png");
        image.setAlpha(true);
    }

    public void reset(){

    }

    public void update(SypherEngine engine, float deltaTime) {

    }

    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawImage(image, engine.getMouseInput().getMouseX() - 32, engine.getMouseInput().getMouseY() - 32);
        renderer.drawImage(image, 16, 50);

        //renderer.drawFilledRect(10, 10, 15, 15, 0xff00ffff);
    }

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine(new GameContainer());
        engine.start();
    }
}
