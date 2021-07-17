package dev.aurumbyte.sypherengine.game.components;

public abstract class Component {
    public GameObject gameObject = null;
    public abstract void update(float deltaTime);
    public void start(){

    }
}
