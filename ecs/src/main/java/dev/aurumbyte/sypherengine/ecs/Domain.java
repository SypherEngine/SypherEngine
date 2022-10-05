/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs;

import dev.aurumbyte.sypherengine.ecs.component.ComponentManager;
import dev.aurumbyte.sypherengine.ecs.component.IComponent;
import dev.aurumbyte.sypherengine.ecs.entity.EntityManager;
import dev.aurumbyte.sypherengine.ecs.system.System;
import dev.aurumbyte.sypherengine.ecs.system.SystemManager;
import java.util.BitSet;

public class Domain {
    EntityManager entityManager;
    ComponentManager componentManager;
    SystemManager systemManager;

    public Domain() {
        this.entityManager = new EntityManager();
        this.componentManager = new ComponentManager(entityManager.getMaxEntities());
        this.systemManager = new SystemManager();
    }

    public int createEntity() {
        return entityManager.createEntity();
    }

    public void destroyEntity(int entity) {
        entityManager.destroyEntity(entity);
        componentManager.entityDestoyed(entity);
        systemManager.entityDestroyed(entity);
    }

    public <T extends IComponent> void registerComponent(Class<T> component) {
        componentManager.registerComponent(component);
    }

    public <T extends IComponent> void addComponent(T component, int entity) throws Exception {
        componentManager.addComponent(entity, component);

        BitSet signature = entityManager.getSignature(entity);
        if (signature == null) {
            signature = new BitSet();
        }
        signature.set(componentManager.getComponentType(component.getClass()));

        entityManager.setSignature(entity, signature);
        systemManager.entitySignatureChanged(entity, signature);
    }

    public <T extends IComponent> void removeComponent(T component, int entity) throws Exception {
        componentManager.removeComponent(entity, component);

        BitSet signature = entityManager.getSignature(entity);
        signature.set(componentManager.getComponentType(component.getClass()), false);
        entityManager.setSignature(entity, signature);

        systemManager.entitySignatureChanged(entity, signature);
    }

    public <T extends IComponent> T getComponent(int entity, Class<T> componentClass) {
        return componentManager.getComponent(entity, componentClass);
    }

    public <T extends IComponent> int getComponentType(Class<T> componentClass) {
        return componentManager.getComponentType(componentClass);
    }

    public <T extends System> System registerSystem(Class<T> system) {
        return systemManager.registerSystem(system, null);
    }

    public <T extends System> void setSystemSignature(Class<T> system, BitSet signature) {
        systemManager.setSignature(system, signature);
    }
}
