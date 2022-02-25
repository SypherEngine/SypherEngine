/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;

public abstract class Component {
    String componentTag = "Component";

    public abstract void update(SypherEngine engine);

    public abstract void render(SypherEngine engine);
}
