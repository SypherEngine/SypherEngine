/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ecs;

import dev.aurumbyte.sypherengine.components.Animator2D;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.scene.image.Image;

/**
 * <p>The Sprite class, basically a wrapper over entity, which makes stuff easier.</p>
 * @author AurumByte
 * @since 0.3.0
 */
public abstract class Sprite2D extends Entity {
    /**
     * The sprite image, could be an image tile
     */
    Image image;

    /**
     * The animator, which contains sprite animations.
     */
    Animator2D animator2D;

    /**
     * <p>Create a new Sprite with just one image as a sprite image</p>
     * @param spriteImage The sprite image
     * @param xPos Position of sprite (x coords)
     * @param yPos Position of sprite (y coords)
     */
    public Sprite2D(Image spriteImage, float xPos, float yPos) {
        this.image = spriteImage;
        this.position = new Vector2(xPos, yPos);
        this.width = (float) spriteImage.getWidth();
        this.height = (float) spriteImage.getHeight();
    }

    /**
     * <p>Create a new Sprite with just one image as a sprite image</p>
     * @param animator2D The sprite animation engine
     * @param xPos Position of sprite (x coords)
     * @param yPos Position of sprite (y coords)
     */
    public Sprite2D(Animator2D animator2D, float xPos, float yPos) {
        this.animator2D = animator2D;
        this.image = null;

        this.position = new Vector2(xPos, yPos);
    }

    /**
     * <p>The update loop for a sprite</p>
     * @param deltaTime The deltaTime of the engine
     */
    public abstract void updateSprite(float deltaTime);

    /**
     * <p>The update loop for the entity, the base of the sprite</p>
     * @param deltaTime The deltaTime
     */
    @Override
    public void update(float deltaTime) {
        updateSprite(deltaTime);
    }

    /**
     * <p>The render loop for the entity, the base of the sprite</p>
     * @param engine The engine
     */
    @Override
    public void render(SypherEngine engine) {
        engine.getRenderer().drawImage(image, position, (int) width, (int) height);
    }
}
