/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.examples.basics;

import dev.aurumbyte.sypherengine.core.GameManager;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;

public class BlankWindow extends GameManager {

    @Override
    public void init(SypherEngine engine) {}

    @Override
    public void update(float deltaTime) {}

    @Override
    public void render(Renderer renderer) {}

    @Override
    public void dispose() {}

    public static void main(String[] args) {
        SypherEngine.init(new BlankWindow());
        SypherEngine.run();
    }
}
