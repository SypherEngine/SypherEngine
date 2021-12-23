package dev.aurumbyte.sypherengine.gameUtils.entity;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.utils.Renderer;

public abstract class Component<T extends GameManager<T>> {
    public abstract void update(SypherEngine engine, T gameManager, float deltaTime);
    public abstract void render(SypherEngine engine, Renderer renderer);
}
