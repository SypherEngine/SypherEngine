/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics.animation;

import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.graphics.tiles.TilesetImage;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * A sprite animation class, using individual images as frames rather than a tileset, is experimental
 * @author AurumByte
 * @since v0.4.0
 */
public class Animator2D {
    /**
     * The frames to be animated upon
     */
    List<Image> frames;
    TilesetImage tileSet;
    Renderer renderer;

    Vector2 tileXRange, tileYRange;

    AnimationType animationType;

    /**
     * The speed of animation to be played
     */
    int animationSpeed = 1;

    /**
     * Create an animator with specified frames
     */
    public Animator2D(List<Image> frames, Renderer renderer) {
        this.renderer = renderer;
        this.frames = frames;
        this.animationType = AnimationType.FRAME_BASED;
        this.tileSet = null;
    }

    /**
     * Create an animator with specified frames
     */
    public Animator2D(Image[] frames, Renderer renderer) {
        this.renderer = renderer;
        this.frames = List.of(frames);
        this.animationType = AnimationType.FRAME_BASED;
        this.tileSet = null;
    }

    public Animator2D(TilesetImage tileSet, Vector2 tileXRange, Vector2 tileYRange, Renderer renderer){
        this.renderer = renderer;
        this.tileSet = tileSet;
        this.tileXRange = tileXRange;
        this.tileYRange = tileYRange;
        this.animationType = AnimationType.TILE_BASED;
        this.frames = null;
    }

    /**
     * <p>Rendering the frames/Playing the animation</p>
     * @since 0.4.0
     */

    public void play(float deltaTime, Vector2 position) {
        float properAnimSpeed = animationSpeed * deltaTime;

        switch (animationType){
            case TILE_BASED -> {
                for(int y = tileYRange.xPosi; y < tileYRange.yPosi; y+=(int)properAnimSpeed){
                    for(int x = tileXRange.xPosi; x < tileXRange.yPosi; x+=(int)properAnimSpeed){
                        renderer.drawImageTile(tileSet, position, x, y);
                        if(x >= tileXRange.yPosi - 1){
                            x = tileXRange.xPosi;
                        }
                    }
                }
            }

            case FRAME_BASED -> {
                for(int i = 0; i < getFrameCount(); i+=properAnimSpeed){
                    Image frame = frames.get(i);
                    renderer.drawImage(frame, position, (float)frame.getWidth(), (float) frame.getHeight());
                }
            }

            default -> throw new IllegalArgumentException(
                    "Sorry, only frame and tile based animations have been implemented, sorry for the inconvenience :)"
            );

            //TODO: implement tweening animation
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


    // this method should only be used if the tileset contains
    public List<Image> getFramesFromTileSet(TilesetImage tileSet){
        List<Image> frames = new ArrayList<>();

        // We get the total size of the tileset
        int imageHeight = (int) tileSet.getTileSet().getHeight();
        int imageWidth = (int) tileSet.getTileSet().getWidth();

        // We get the number of tiles possible from the left to the right, and from the top to the bottom
        int numberOfHorizontalTiles = imageWidth / tileSet.getTileWidth();
        int numberOfVerticalTiles = imageHeight / tileSet.getTileHeight();

        //We loop through them to get the frames, as in individual tiles from the tile image
        for(int i = 0; i < numberOfVerticalTiles; i++){
            for(int j = 0; j < numberOfHorizontalTiles; j++){
                frames.add(tileSet.getImageTile(j, i));
            }
        }

        return frames;
    }

    enum AnimationType {
        TILE_BASED, FRAME_BASED, TWEEN
    }
}
