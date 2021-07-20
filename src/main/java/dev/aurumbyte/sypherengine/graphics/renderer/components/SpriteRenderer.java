package dev.aurumbyte.sypherengine.graphics.renderer.components;

import dev.aurumbyte.sypherengine.game.components.Component;
import dev.aurumbyte.sypherengine.graphics.Texture2D;
import org.joml.Vector2f;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {
    private Vector4f color;
    private Vector2f[] textureCoords;
    private Texture2D texture;

    public SpriteRenderer(Vector4f color){
        this.color = color;
        this.texture = null;
    }
    public SpriteRenderer(Texture2D texture){
        this.texture = texture;
        this.color = new Vector4f(1,1,1,1);
    }


    @Override
    public void start(){

    }

    @Override
    public void update(float deltaTime) {

    }

    public Vector4f getColor() {
        return this.color;
    }
    public Texture2D getTexture(){ return this.texture; }

    public Vector2f[] getTextureCoords(){
        Vector2f[] textureCoords = {
                new Vector2f(1, 1),
                new Vector2f(1, 0),
                new Vector2f(0, 0),
                new Vector2f(0, 1),
        };

        return textureCoords;
    }
}
