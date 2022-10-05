/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs.system;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SystemManager {
    Map<String, BitSet> signatures = new HashMap<>();
    Map<String, System> systems = new HashMap<>();

    public <T extends System> T registerSystem(Class<T> systemClass, LinkedHashMap<Class<?>, Object> arguments) {
        String typeName = systemClass.getTypeName();

        if (systems.get(typeName) != null) {
            throw new IllegalStateException("Trying to register system of type " + typeName + " more than once.");
        }

        T system;

        try {
            if (arguments == null) {
                system = systemClass.getDeclaredConstructor().newInstance();
            } else {
                Constructor<T> constructor =
                        systemClass.getDeclaredConstructor(arguments.keySet().toArray(new Class[0]));
                constructor.setAccessible(true);
                system = constructor.newInstance(arguments.values().toArray());
            }
        } catch (NoSuchMethodException
                | InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }

        systems.put(typeName, system);
        return system;
    }

    public <T extends System> void setSignature(Class<T> systemClass, BitSet signature) {
        String typeName = systemClass.getTypeName();
        if (systems.get(typeName) == null) {
            throw new IllegalStateException("System of type " + typeName + " was used before it was registered.");
        }

        signatures.put(typeName, signature);
    }

    public void entityDestroyed(int entity) {
        for (Map.Entry<String, System> entry : systems.entrySet()) {
            entry.getValue().entities.remove(entity);
        }
    }

    public void entitySignatureChanged(int entity, BitSet signature) {
        for (Map.Entry<String, System> entry : systems.entrySet()) {
            String type = entry.getKey();
            System system = entry.getValue();
            BitSet systemSignature = signatures.get(type);

            BitSet entitySignatureClone = (BitSet) signature.clone();
            entitySignatureClone.and(systemSignature);
            if (entitySignatureClone.equals(systemSignature)) {
                system.entities.add(entity);
            } else {
                system.entities.remove(entity);
            }
        }
    }
}
