package dev.aurumbyte.sypherengine.components.scene;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    public KeyListener keyListener = KeyListener.getInstance();
    public MouseListener mouseListener = MouseListener.getInstance();
    public List<Entity> entities = new ArrayList<>();

    public abstract void init();
    public abstract void update(float deltaTime);
    public abstract void render(Renderer renderer);
}
