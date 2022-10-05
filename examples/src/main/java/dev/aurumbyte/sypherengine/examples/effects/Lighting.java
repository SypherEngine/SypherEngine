package dev.aurumbyte.sypherengine.examples.effects;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.paint.Color;

public class Lighting extends GameManager {
    Vector2 rectPos = new Vector2(200, 200);
    Vector2 lightPos = new Vector2(250, 250);

    @Override
    public void init(SypherEngine engine) {
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(rectPos, 500, 500, true, Color.BLUE);
        renderer.drawLight(Renderer.Lighting.SPOT, lightPos, Color.RED);
    }

    public static void main(String[] args){
        SypherEngine.init(new Lighting(), "Lighting Test");
        SypherEngine.run();
    }
}
