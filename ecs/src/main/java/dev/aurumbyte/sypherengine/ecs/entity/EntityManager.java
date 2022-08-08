/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs.entity;

import dev.aurumbyte.sypherengine.ecs.component.ComponentArray;
import java.util.*;

public class EntityManager {
    final BitSet[] entitySignatures;
    int entityID;

    int maxEntities = 5000;
    int livingEntityCount = 0;

    Stack<Integer> availableEntityIDs = new Stack<>();
    Map<Integer, List<ComponentArray>> entityMap = new HashMap<>();

    public EntityManager(int maxEntities) {
        this.maxEntities = maxEntities;
        this.entitySignatures = new BitSet[maxEntities];
        for (entityID = 0; entityID < maxEntities; ++entityID) {
            availableEntityIDs.push(entityID);
        }
    }

    public EntityManager() {
        this.entitySignatures = new BitSet[maxEntities];
        for (entityID = 0; entityID < maxEntities; ++entityID) {
            availableEntityIDs.push(entityID);
        }
    }

    public int createEntity() {
        if (livingEntityCount >= maxEntities) {
            throw new IllegalStateException("living entities exceeded max entity count");
        }

        int entity = availableEntityIDs.firstElement();
        availableEntityIDs.pop();
        ++livingEntityCount;

        return entity;
    }

    public void destroyEntity(int entityID) {
        if (entityID > maxEntities) throw new IllegalStateException("Entity beyond range");
        entitySignatures[entityID].clear();
        availableEntityIDs.push(entityID);

        --livingEntityCount;
    }

    public void setSignature(int entity, BitSet signature) {
        if (entityID > maxEntities) throw new IllegalStateException("Entity beyond range");
        entitySignatures[entity] = signature;
    }

    public BitSet getSignature(int entity) throws Exception {
        if (entityID > maxEntities) throw new Exception("Entity beyond range");
        return entitySignatures[entity];
    }

    public int getMaxEntities() {
        return maxEntities;
    }
}
