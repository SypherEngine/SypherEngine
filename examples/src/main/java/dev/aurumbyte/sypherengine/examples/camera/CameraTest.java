/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.examples.camera;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.input.KeyCode;

import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;

public class CameraTest extends GameManager {
    Vector2 rect1Pos = new Vector2(200, 200);
    Vector2 rect2Pos = new Vector2(300, 300);
    InputHandler inputHandler;

    @Override
    public void init(SypherEngine engine) {
        this.inputHandler = this.getInputHandler();
    }

    @Override
    public void update(float deltaTime) {
        if (inputHandler.keyListener.isDown(KeyCode.UP)) this.getCamera().getCameraPosition().yPos -= 5;
        if (inputHandler.keyListener.isDown(KeyCode.DOWN)) this.getCamera().getCameraPosition().yPos += 5;
        if (inputHandler.keyListener.isDown(KeyCode.LEFT)) this.getCamera().getCameraPosition().xPos -= 5;
        if (inputHandler.keyListener.isDown(KeyCode.RIGHT)) this.getCamera().getCameraPosition().xPos += 5;

        if (inputHandler.keyListener.isDown(KeyCode.W)) rect1Pos.yPos -= 5;
        if (inputHandler.keyListener.isDown(KeyCode.S)) rect1Pos.yPos += 5;
        if (inputHandler.keyListener.isDown(KeyCode.A)) rect1Pos.xPos -= 5;
        if (inputHandler.keyListener.isDown(KeyCode.D)) rect1Pos.xPos += 5;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawRectangle(rect1Pos, 50, 50, true, RED);
        renderer.drawRectangle(rect2Pos, 50, 50, true, BLUE);
    }

    @Override
    public void dispose() {}

    public static void main(String[] args) {
        SypherEngine.init(new CameraTest(), "Camera Test");
        SypherEngine.run();
    }
}
