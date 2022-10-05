/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.examples.basics.inputs;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.text.Font;

import static javafx.scene.input.KeyCode.*;
import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.BLUE;

public class KeyboardInput extends GameManager {
    Vector2 playerPos = new Vector2(200, 200);
    String text = "";
    InputHandler inputHandler = getInputHandler();

    @Override
    public void init(SypherEngine engine) {

    }

    @Override
    public void update(float deltaTime) {
        if (inputHandler.keyListener.isDown(UP)) {
            text = "UP";
            playerPos.yPos -= 4;
        } else if (inputHandler.keyListener.isDown(DOWN)) {
            text = "DOWN";
            playerPos.yPos += 4;
        } else if (inputHandler.keyListener.isDown(LEFT)) {
            text = "LEFT";
            playerPos.xPos -= 4;
        } else if (inputHandler.keyListener.isDown(RIGHT)) {
            text = "RIGHT";
            playerPos.xPos += 4;
        } else text = "";
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(playerPos, 50, 50, true, BLUE);
        renderer.drawText(text, new Vector2(playerPos.xPos, playerPos.yPos - 15), BLACK, Font.font(15));
    }

    public static void main(String[] args) {
        SypherEngine.init(new KeyboardInput());
        SypherEngine.run();
    }
}
