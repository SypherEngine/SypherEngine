/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics.tiles;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

/**
 * Getting specific Images from a tileset
 * @author AurumByte
 * @since v0.3.0
 */
public class ImageTile {
    /**
     * The tileset image
     */
    Image image;

    /**
     * The width and height of a tile
     */
    int tileWidth, tileHeight;

    /**
     * <p>The abstract initialization method, to be overridden by the user</p>
     * @param image The tileset
     * @param tileWidth The width of a tile
     * @param tileHeight The height of a tile
     * @since 0.3.0
     */
    public ImageTile(Image image, int tileWidth, int tileHeight) {
        this.image = image;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /**
     * <p>The abstract initialization method, to be overridden by the user</p>
     * @param path The path to the tileset
     * @param tileWidth The width of a tile
     * @param tileHeight The height of a tile
     * @since 0.3.0
     */
    public ImageTile(String path, int tileWidth, int tileHeight) {
        this.image = new Image(path);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    /**
     * <p>The abstract initialization method, to be overridden by the user</p>
     * @param tileX The tile count from left to right as per specified tile width
     * @param tileY The tile count from top to bottom as per specified tile height
     * @since 0.3.0
     */
    public Image getImageTile(int tileX, int tileY) {
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    /**
     * <p>returning the Tileset</p>
     * @since 0.3.0
     */
    public Image getTileSet() {
        return image;
    }

    /**
     * <p>Getting the height of the tile</p>
     * @since 0.3.0
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * <p>Getting the width of the tile</p>
     * @since 0.3.0
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * <p>Setting the tileset</p>
     * @param image the tileset image
     * @since 0.3.0
     */
    public void setTileSet(Image image) {
        this.image = image;
    }

    /**
     * <p>Setting the height of the tile</p>
     * @param tileHeight the height of the tile to be set
     * @since 0.3.0
     */
    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    /**
     * <p>Setting the width of the tile</p>
     * @param tileWidth the width of the tile to be set
     * @since 0.3.0
     */
    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }
}
