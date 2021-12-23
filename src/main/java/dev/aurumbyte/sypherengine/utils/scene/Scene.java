package dev.aurumbyte.sypherengine.utils.scene;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.Renderer;

public abstract class Scene {
    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(SypherEngine engine, Renderer renderer);
}
