package dev.aurumbyte.sypherengine.components.scene;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.ui.UIElement;

import java.util.ArrayList;
import java.util.List;

public abstract class Scene {
    Scene currentScene;
    public List<Entity> entities = new ArrayList<>();
    public List<UIElement> uiElements = new ArrayList<>();

    public abstract void init(SypherEngine engine);
    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(Renderer renderer);
}
