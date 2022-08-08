/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.components;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import javafx.scene.image.Image;

import java.util.List;

/**
 * A sprite animation class, using individual images as frames rather than a tileset, is experimental
 * @author AurumByte
 * @since v0.4.0
 * @see Component
 */
public class Animator2D extends Component {
    /**
     * The frames to be animated upon
     */
    List<Image> frames;

    /**
     * The speed of animation to be played
     */
    int animationSpeed = 1;

    /**
     * Create an animator with specified frames
     */
    public Animator2D(List<Image> frames) {
        this.frames = frames;
    }

    /**
     * Create an animator with specified frames
     */
    public Animator2D(Image[] frames) {
        this.frames = List.of(frames);
    }

    /**
     * <p>Updating the Animation</p>
     * @param deltaTime DeltaTime, the duration taken to update a frame
     * @since 0.4.0
     */
    @Override
    public void update(float deltaTime) {}

    /**
     * <p>Rendering the frames</p>
     * @param engine the base engine, which runs the game
     * @since 0.4.0
     */
    @Override
    public void render(SypherEngine engine) {
        for (int i = 0; i < frames.size(); i++) {
            // engine.getRenderer().drawImage(frames.get(i), );
            // TODO: implement sprite frame animations
        }
    }

    /**
     * <p>Adding a frame to the animation</p>
     * @param frame the frame to be added
     * @since 0.4.0
     */
    public void addFrame(Image frame) {
        this.frames.add(frame);
    }

    /**
     * <p>Removing a frame from the animation</p>
     * @param frame the frame to be removed
     * @since 0.4.0
     */
    public void removeFrame(Image frame) {
        this.frames.remove(frame);
    }

    /**
     * <p>Adding multiple frames to the animation</p>
     * @param frames the frames to be added
     * @since 0.4.0
     */
    public void addFrames(Image[] frames) {
        this.frames.addAll(List.of(frames));
    }

    /**
     * <p>Removing multiple frames from the animation</p>
     * @param frames the frames to be removed
     * @since 0.4.0
     */
    public void removeFrames(Image[] frames) {
        for (Image frame : frames) {
            this.frames.remove(frame);
        }
    }

    /**
     * <p>Getting how many frames are present in the animation</p>
     * @since 0.4.0
     */
    public int getFrameCount() {
        return this.frames.size();
    }

    /**
     * <p>Getting the frames present in the animation</p>
     * @since 0.4.0
     */
    public List<Image> getFrames() {
        return frames;
    }

    /**
     * <p>Setting the frames present in the animation</p>
     * @since 0.4.0
     */
    public void setFrames(List<Image> frames) {
        this.frames = frames;
    }

    /**
     * <p>Setting the frames present in the animation</p>
     * @since 0.4.0
     */
    public void setFrames(Image[] frames) {
        this.frames = List.of(frames);
    }
}
