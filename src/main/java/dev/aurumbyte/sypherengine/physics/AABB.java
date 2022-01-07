package dev.aurumbyte.sypherengine.physics;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.entity.Component;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.math.Vector2;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class AABB<T extends GameManager<T>> extends Component<T> {
    private Vector2 min, max;

    private final GameObject<T> parent;

    public AABB(GameObject<T> parent){
        this.parent = parent;
    }

    @Override
    public void init() {
    }

    @Override
    public void update(SypherEngine engine, T gameManager, float deltaTime) {
        this.min = new Vector2((int)(parent.getxPos() + parent.getPadding()), //x
                               (int)(parent.getyPos() + parent.getPaddingTop())); //y

        this.max = new Vector2((int)(parent.getxPos() + parent.getPadding() + parent.getWidth()), //x
                               (int)(parent.getyPos() + parent.getPaddingTop()) + parent.getHeight()); //y

        gameManager.physics.addAABBComponent(this);
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        renderer.drawRect(min.getX(), min.getY(), max.getX() - min.getX(), max.getY() - min.getY(), 0xff000000);
    }

    public GameObject<T> getParent() {
        return parent;
    }

    public Vector2 getMin() {
        return min;
    }

    public Vector2 getMax() {
        return max;
    }
}
