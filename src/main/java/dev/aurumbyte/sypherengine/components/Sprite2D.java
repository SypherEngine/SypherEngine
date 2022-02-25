/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.scene.image.Image;

public abstract class Sprite2D extends Entity {
    Image image;

    public Sprite2D(Image spriteImage, int xPos, int yPos) {
        this.image = spriteImage;
        this.position = new Vector2(xPos, yPos);
        this.width = spriteImage.getWidth();
        this.height = spriteImage.getHeight();
    }

    public abstract void updateSprite(SypherEngine engine);

    @Override
    public void update(SypherEngine engine) {
        updateSprite(engine);
    }

    @Override
    public void render(SypherEngine engine) {
        engine.getRenderer().drawImage(image, position, (int) width, (int) height);
        engine.getRenderer().addEntity(this);
    }
}
