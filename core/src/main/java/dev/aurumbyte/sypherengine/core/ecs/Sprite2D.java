/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.ecs;

import dev.aurumbyte.sypherengine.core.components.Animator2D;
import dev.aurumbyte.sypherengine.core.event.InputHandler;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.image.Image;

/**
 * <p>The Sprite class, basically a wrapper over entity, which makes stuff easier.</p>
 * @author AurumByte
 * @since 0.3.0
 */
public abstract class Sprite2D extends GameObject {
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
    public Sprite2D(InputHandler inputHandler, Image spriteImage, float xPos, float yPos) {
        super(inputHandler);
        this.image = spriteImage;
        this.getTransform().setPosition(new Vector2(xPos, yPos));
        this.width = (float) spriteImage.getWidth();
        this.height = (float) spriteImage.getHeight();
    }

    /**
     * <p>Create a new Sprite with just one image as a sprite image</p>
     * @param animator2D The sprite animation engine
     * @param xPos Position of sprite (x coords)
     * @param yPos Position of sprite (y coords)
     */
    public Sprite2D(InputHandler inputHandler, Animator2D animator2D, float xPos, float yPos) {
        super(inputHandler);
        this.animator2D = animator2D;
        this.image = null;

        this.getTransform().setPosition(new Vector2(xPos, yPos));
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
     * @param renderer The renderer
     */
    @Override
    public void render(Renderer renderer) {
        renderer.drawImage(image, getTransform().getPosition(), (int) width, (int) height);
    }
}
