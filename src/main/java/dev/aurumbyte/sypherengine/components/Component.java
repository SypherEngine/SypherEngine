/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import java.util.List;

/**
 * The base component class, which all components must extend
 * @author AurumByte
 * @since v0.3.2
 */
public abstract class Component {
    /**
     * The name of the component
     */
    String componentTag = "Component";

    /**
     * <p>The abstract update method, to be overridden by the user</p>
     * @param deltaTime The deltaTime of the game, kinda useless as the update rates are fixed, but nice to have just in case
     * @since 0.3.2
     */
    public abstract void update(float deltaTime);

    /**
     * <p>The abstract render method, to be overridden by the user</p>
     * @param engine The main engine, used for rendering purposes the game
     * @since 0.3.2
     */
    public abstract void render(SypherEngine engine);

    /**
     * <p>Getting an AABB component out of all components</p>
     * @param components The list of components
     * @since 0.3.2
     */
    public static AABB getAABBComponent(List<Component> components) {
        AABB aabb = null;
        for (Component component : components) {
            if (component instanceof AABB) {
                aabb = (AABB) component;
                break;
            }
        }
        return aabb;
    }
}
