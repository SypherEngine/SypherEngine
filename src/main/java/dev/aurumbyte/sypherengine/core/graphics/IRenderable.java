package dev.aurumbyte.sypherengine.core.graphics;

import dev.aurumbyte.sypherengine.core.SypherEngine;

public interface IRenderable {
    void update(SypherEngine engine);
    void render(SypherEngine engine);
}
