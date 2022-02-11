package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite2D extends Entity {
    Image image;

    public Sprite2D(Image spriteImage, int xPos, int yPos){
        this.entityRenderable = spriteImage;
        this.image = spriteImage;
        this.position = new Vector2(xPos, yPos);
        this.width = spriteImage.getWidth();
        this.height = spriteImage.getHeight();
        this.boundary = new Rectangle2D(xPos, yPos, spriteImage.getWidth(), spriteImage.getHeight());
    }

    @Override
    public void update(SypherEngine engine) {

    }

    @Override
    public void render(SypherEngine engine) {
        engine.getRenderer().drawImage(image, position, (int)width, (int)height);
        engine.getRenderer().addEntity(this);
    }
}
