/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.examples.ui;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.ui.Button;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ButtonTest extends GameManager {
    Button button = new Button("Button", new Vector2(200, 200), 100, 50);
    Vector2 textPos = new Vector2(400, 400);

    @Override
    public void init(SypherEngine engine) {}

    @Override
    public void update(float deltaTime) {
        button.update(deltaTime);
    }

    @Override
    public void render(Renderer renderer) {
        button.render(renderer);

        if (button.clickEvent) {
            renderer.drawText("Button Clicked!", textPos, Color.BLACK, Font.font(30));
        }
    }

    @Override
    public void dispose() {}

    public static void main(String[] args) {
        SypherEngine.init(new ButtonTest(), "Button Test");
        SypherEngine.run();
    }
}
