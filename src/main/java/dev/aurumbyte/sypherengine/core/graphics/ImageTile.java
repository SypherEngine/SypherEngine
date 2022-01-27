package dev.aurumbyte.sypherengine.core.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageTile {
    Image image;
    int tileWidth, tileHeight;

    public ImageTile(Image image, int tileWidth, int tileHeight){
        this.image = image;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public ImageTile(String path, int tileWidth, int tileHeight){
        this.image = new Image(path);
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }

    public Image getImageTile(int tileX, int tileY){
        PixelReader reader = image.getPixelReader();
        return new WritableImage(reader, tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    public Image getImage() {
        return image;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }
}
