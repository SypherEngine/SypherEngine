package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.game.Transform;
import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.graphics.renderer.components.SpriteRenderer;
import dev.aurumbyte.sypherengine.scene.camera.Camera;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class LevelEditorScene extends Scene{
    public LevelEditorScene(){

    }

    @Override
    public void init(){
        this.camera = new Camera(new Vector2f());

        int xOffset = 10;
        int yOffset = 10;

        float totalwidth = (float) (600 - xOffset * 2);
        float totalheight = (float) (300 - yOffset * 2);
        float sizeX = totalwidth / 100.0f;
        float sizeY = totalheight / 100.0f;

        for(int x = 0; x < 100; x++){
            for(int y = 0; y < 100; y++){
                float xPos = xOffset + (x * sizeX);
                float yPos = yOffset + (y * sizeY);

                GameObject gameObject = new GameObject("Obj " + x + " " + y, new Transform(new Vector2f(xPos, yPos), new Vector2f(sizeX, sizeY)));
                gameObject.addComponent(new SpriteRenderer(new Vector4f(xPos/totalwidth, yPos/totalheight, 1, 1)));
                this.addGameObjectToScene(gameObject);
            }
        }
    }

    @Override
    public void update(float deltaTime) {
        /*
        for (GameObject gameObject : this.gameObjects){
            gameObject.update(deltaTime);
        }
         */

        gameObjects.forEach(gameObject -> gameObject.update(deltaTime));
        this.renderer.render();
    }
}
