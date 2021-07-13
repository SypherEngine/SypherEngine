package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.GameRenderer;

public interface IGame {
    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(SypherEngine engine, GameRenderer renderer);
}
