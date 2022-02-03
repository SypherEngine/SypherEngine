package io.github.sypherengine.core.graphics;

import io.github.sypherengine.core.SypherEngine;

public interface IRenderable {
    void update(SypherEngine engine);
    void render(SypherEngine engine);
}
