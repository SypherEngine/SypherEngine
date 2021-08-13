package dev.aurumbyte.sypherengine.gameUtils.entity.components;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.entity.Component;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.util.ArrayList;
import java.util.List;

public class GameObject<T extends GameManager<T>> extends Component<T> {
    protected String tag;
    protected float xPos, yPos;
    protected int width, height;
    protected boolean isDead = false;

    protected List<Component<T>> components = new ArrayList<>();

    @Override
    public void update(SypherEngine engine, T gameManager, float deltaTime) {
        components.forEach(component -> component.update(engine, gameManager, deltaTime));
    }

    @Override
    public void render(SypherEngine engine, Renderer renderer) {
        components.forEach(component -> component.render(engine, renderer));
    }

    public void addComponent(Component<T> component){
        components.add(component);
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public float getxPos() {
        return xPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isDead(){
        return isDead;
    }

    public void setIsDead(boolean dead) {
        isDead = dead;
    }
}
