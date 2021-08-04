package dev.aurumbyte.sypherengine.game;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.Renderer;

public interface IGame {
    void init(SypherEngine engine);
    void update(SypherEngine engine, float deltaTime);
    void render(SypherEngine engine, Renderer renderer);
}
