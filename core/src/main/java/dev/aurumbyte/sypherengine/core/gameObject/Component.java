package dev.aurumbyte.sypherengine.core.gameObject;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.ecs.component.IComponent;

public abstract class Component implements IComponent {
    public <T extends Component> Component(T component, int entityID){
        SypherEngine.domain.registerComponent(component.getClass());
        try {
            SypherEngine.domain.addComponent(component, entityID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void update(float deltaTime);
    public abstract void render(Renderer renderer);
}
