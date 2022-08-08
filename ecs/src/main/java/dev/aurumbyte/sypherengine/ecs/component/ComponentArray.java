/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs.component;

import java.util.HashMap;
import java.util.Map;

public class ComponentArray<T extends IComponent> implements IComponentArray {
    T[] componentArray;
    Map<Integer, Integer> entityToIndexMap = new HashMap<>();
    Map<Integer, Integer> indexToEntityMap = new HashMap<>();
    final int maxEntities;
    int size;

    @SuppressWarnings("unchecked")
    public ComponentArray(int maxEntities) {
        this.maxEntities = maxEntities;
        this.componentArray = (T[]) new IComponent[maxEntities];
    }

    public <X extends IComponent> void insertData(int entity, X component) {
        if (entityToIndexMap.get(entity) != null) {
            throw new IllegalStateException(
                    "Component of class " + component.getClass() + " was added to same entity more than once.");
        }

        int newIndex = size;
        entityToIndexMap.put(entity, newIndex);
        indexToEntityMap.put(newIndex, entity);
        componentArray[newIndex] = (T) component;
        ++size;
    }

    public void removeData(int entity) {
        if (entityToIndexMap.get(entity) == null) {
            throw new IllegalStateException(
                    "Entity with ID: " + entity + " does not have data in this component array.");
        }

        // Copy element at end into deleted element's place to maintain density
        int removedEntityIndex = entityToIndexMap.get(entity);
        int lastElementIndex = size - 1;
        componentArray[removedEntityIndex] = componentArray[lastElementIndex];

        // Update map to point to moved spot
        int lastElementEntity = indexToEntityMap.get(lastElementIndex);
        entityToIndexMap.put(lastElementEntity, removedEntityIndex);
        indexToEntityMap.put(removedEntityIndex, lastElementEntity);

        // Remove the entity and its corresponding index
        entityToIndexMap.remove(entity);
        indexToEntityMap.remove(lastElementIndex);

        --size;
    }

    public T getData(int entity) {
        if (entityToIndexMap.get(entity) == null) {
            throw new IllegalStateException(
                    "Entity with ID: " + entity + " does not have data in this component array.");
        }

        return componentArray[entityToIndexMap.get(entity)];
    }

    @Override
    public void entityDestroyed(int entity) {
        Integer idx = entityToIndexMap.get(entity);
        if (idx == null || idx.equals(entityToIndexMap.get(entityToIndexMap.size()))) {
            return;
        }

        removeData(entity);
    }
}
