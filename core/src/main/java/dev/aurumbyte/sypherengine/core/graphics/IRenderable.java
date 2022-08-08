/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics;

/**
 * <p>An Interface for all renderable entities</p>
 * @author AurumByte
 */
public interface IRenderable {
    void update(float deltaTime);

    void render(Renderer renderer);
}
