package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.audio.Audio;
import dev.aurumbyte.sypherengine.core.graphics.ImageTile;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import javafx.scene.input.KeyCode;

public class Test extends GameManager {
    Audio audio = new Audio("/RetroGame1.wav");

    public static void main(String[] args){
        EngineConfig config = new EngineConfig();
        config.setTitle("Testing");
        config.setScreenResolution(500, 500);

        SypherEngine.init(new Test(), config);
        SypherEngine.run(args);
    }

    @Override
    public void init() {

    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        if(engine.keyListener.isDown(KeyCode.P)) audio.play();
    }

    @Override
    public void render(Renderer renderer) {
        ImageTile imageTile = new ImageTile("/playerTileset.png", 16, 16);
        renderer.drawImageTile(imageTile,
                (int)renderer.getScreenCenter().getX(),
                (int)renderer.getScreenCenter().getY(),
                1,
                1
        );
    }
}
