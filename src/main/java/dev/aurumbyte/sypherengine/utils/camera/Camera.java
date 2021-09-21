package dev.aurumbyte.sypherengine.utils.camera;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.entity.components.GameObject;
import dev.aurumbyte.sypherengine.utils.Renderer;

public class Camera<T extends GameManager<T>> /*extends Component<T>*/ {
    private double offX, offY;
    private double cameraSpeed = 10;

    private String tag;
    private GameObject<T> target = null;

    public Camera(String tag){
        this.tag = tag;
    }

    public void update(SypherEngine engine, T game, double deltaTime) {
        if(target == null) target = game.getObject(tag);
        if(target == null) return;

        double targetX = (target.getxPos() + target.getWidth() / 2.0) - engine.getWidth() / 2.0;
        double targetY = (target.getyPos() + target.getHeight() / 2.0) - engine.getHeight() / 2.0;

        offX -= deltaTime * (offX - targetX) * cameraSpeed;
        offY -= deltaTime * (offY - targetY) * cameraSpeed;
    }

    public void render(SypherEngine engine, Renderer renderer) {
        renderer.setCamPos((int)Math.floor(offX), (int)Math.floor(offY));
    }


    public double getOffX() {
        return offX;
    }

    public double getOffY() {
        return offY;
    }

    public GameObject<T> getTarget() {
        return target;
    }

    public String getTag() {
        return tag;
    }

    public double getCameraSpeed() {
        return cameraSpeed;
    }

    public void setOffX(double offX) {
        this.offX = offX;
    }

    public void setOffY(double offY) {
        this.offY = offY;
    }

    public void setCameraPos(double offX, double offY){
        this.offX = offX;
        this.offY = offY;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTarget(GameObject<T> target) {
        this.target = target;
    }

    public void setCameraSpeed(double cameraSpeed) {
        this.cameraSpeed = cameraSpeed;
    }
}
