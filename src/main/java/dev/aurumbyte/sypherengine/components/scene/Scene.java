package dev.aurumbyte.sypherengine.components.scene;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.IRenderable;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    public List<Entity> entities = new ArrayList<>();
    public List<IRenderable> renderables = new ArrayList<>();

    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine);
    public abstract void render(SypherEngine engine);
}
