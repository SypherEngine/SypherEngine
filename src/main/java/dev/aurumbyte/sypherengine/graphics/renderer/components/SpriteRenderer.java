package dev.aurumbyte.sypherengine.graphics.renderer.components;

import dev.aurumbyte.sypherengine.game.components.Component;
import org.joml.Vector4f;

public class SpriteRenderer extends Component {
    private Vector4f color;

    public SpriteRenderer(Vector4f color){
        this.color = color;
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
}
