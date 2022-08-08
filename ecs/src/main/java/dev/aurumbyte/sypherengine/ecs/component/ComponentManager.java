/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs.component;

import java.util.HashMap;
import java.util.Map;

public class ComponentManager {
    private final Map<String, Integer> componentTypes = new HashMap<>();
    private final Map<String, IComponentArray> componentArrays = new HashMap<>();
    private int nextComponentType = 0;

    private final int maxEntities;

    public ComponentManager(int maxEntityCount) {
        this.maxEntities = maxEntityCount;
    }

    public <T extends IComponent> void registerComponent(Class<T> componentClass) {
        String typeName = componentClass.getTypeName();
        if (componentTypes.get(typeName) != null) {
            throw new IllegalStateException("Trying to register component of type " + typeName + " more than once.");
        }

        componentTypes.put(typeName, nextComponentType);
        componentArrays.put(typeName, new ComponentArray<T>(maxEntities));

        ++nextComponentType;
    }

    public <T extends IComponent> int getComponentType(Class<T> componentClass) {
        String typeName = componentClass.getTypeName();
        return componentTypes.get(typeName);
    }

    public <T extends IComponent> void addComponent(int entity, T component) {
        getComponentArray(component.getClass()).insertData(entity, component);
    }

    public <T extends IComponent> void removeComponent(int entity, T component) {
        getComponentArray(component.getClass()).removeData(entity);
    }

    public <T extends IComponent> T getComponent(int entity, Class<T> componentClass) {
        return getComponentArray(componentClass).getData(entity);
    }

    public void entityDestoyed(int entity) {
        for (var pair : componentArrays.entrySet()) {
            IComponentArray componentArray = pair.getValue();
            componentArray.entityDestroyed(entity);
        }
    }

    private <T extends IComponent> ComponentArray<T> getComponentArray(Class<T> componentClass) {
        String typeName = componentClass.getTypeName();

        if (componentTypes.get(typeName) == null) {
            throw new IllegalStateException("An array for component type " + typeName + " was not found.");
        }

        return (ComponentArray<T>) componentArrays.get(typeName);
    }
}
