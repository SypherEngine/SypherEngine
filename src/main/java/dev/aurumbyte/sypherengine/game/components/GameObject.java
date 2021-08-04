package dev.aurumbyte.sypherengine.game.components;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.Renderer;

public abstract class GameObject {
    protected String tag;
    protected float xPos, yPos;
    protected int width, height;
    protected boolean isDead = false;

    public abstract void update(SypherEngine engine, float deltaTime);
    public abstract void render(SypherEngine engine, Renderer renderer);

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
