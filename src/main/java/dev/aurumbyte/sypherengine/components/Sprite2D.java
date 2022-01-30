package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite2D extends Entity {
    Image image;

    public Sprite2D(Image spriteImage, int xPos, int yPos){
        this.entityRenderable = spriteImage;
        this.position = new Point2D(xPos, yPos);
        this.width = spriteImage.getWidth();
        this.height = spriteImage.getHeight();
        this.boundary = new Rectangle2D(xPos, yPos, spriteImage.getWidth(), spriteImage.getHeight());
    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {

    }

    @Override
    public void render(Renderer renderer) {
        renderer.addEntity(this);
    }
}
