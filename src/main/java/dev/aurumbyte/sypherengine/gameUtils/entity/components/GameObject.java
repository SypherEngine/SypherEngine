package dev.aurumbyte.sypherengine.gameUtils.entity.components;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.entity.Component;
import dev.aurumbyte.sypherengine.utils.Renderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class GameObject<T extends GameManager<T>> {
    protected String tag;
    protected float xPos, yPos;
    protected int width, height;
    protected boolean isDead = false;

    public int padding, paddingTop; // should change this soon

    protected List<Component<T>> components = new ArrayList<>();


    public abstract void init();


    public abstract void update(SypherEngine engine, T gameManager, float deltaTime);
    public abstract void render(SypherEngine engine, Renderer renderer);
    public abstract void event(GameObject<T> other); //will expand this this to other events soon :v

    public void updateComponents(SypherEngine engine, T gameManager, float deltaTime){
        components.forEach(component -> component.update(engine, gameManager, deltaTime));
    }

    public void renderComponents(SypherEngine engine, Renderer renderer){
        components.forEach(component -> component.render(engine, renderer));
    }

    public void initComponents(){
        components.forEach(Component::init);
    }

    public void addComponent(Component<T> component){
        components.add(component);
    }

    public void removeComponent(String tag) {
        components.removeIf(component -> Objects.equals(component.getTag(), tag));
    }

    public Component<T> findComponent(String tag) {
        for(Component<T> component : components){
            if(Objects.equals(component.getTag(), tag)){
                return component;
            }
        }
        return null;
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

    public List<Component<T>> getComponents() {
        return components;
    }

    public int getPadding() {
        return padding;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public int getPaddingTop() {
        return paddingTop;
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }
}
