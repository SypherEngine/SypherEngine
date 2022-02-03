package io.github.sypherengine.components.scene;

import io.github.sypherengine.components.Entity;
import io.github.sypherengine.core.SypherEngine;
import io.github.sypherengine.core.graphics.IRenderable;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    public List<Entity> entities = new ArrayList<>();
    public List<IRenderable> renderables = new ArrayList<>();

    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine);
    public abstract void render(SypherEngine engine);
}
