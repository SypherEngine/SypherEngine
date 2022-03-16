/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics;

import dev.aurumbyte.sypherengine.core.SypherEngine;

/**
 * <p>An Interface for all renderable entities</p>
 * @author AurumByte
 */
public interface IRenderable {
    void update(float deltaTime);

    void render(SypherEngine engine);
}
