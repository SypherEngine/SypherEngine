/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs.system;

import java.util.HashSet;
import java.util.Set;

public interface System {
    Set<Integer> entities = new HashSet<>();
}
