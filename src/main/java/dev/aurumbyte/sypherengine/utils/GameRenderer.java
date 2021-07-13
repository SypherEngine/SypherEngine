package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.image.ImageTile;

import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class GameRenderer {
    private int pixelWidth, pixelHeight;
    private int[] pixels;

    public GameRenderer(SypherEngine engine){
        pixelWidth = engine.getWidth();
        pixelHeight = engine.getHeight();
        pixels = ((DataBufferInt) engine.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }

    public void clear(){
        Arrays.fill(pixels, 0);
    }

    public void setPixel(int x, int y, int value){
        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || value == 0xffff00ff){
            return;
        }

        pixels[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY){
        int newX = 0, newY = 0;
        int newWidth = image.getWidth();
        int newHeight = image.getHeight();

        // Doesn't render when image goes offscreen
        if(offX < -image.getWidth()) return;
        if(offY < -image.getHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        // Doesn't render parts of image that go offscreen (Clipping)
        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;

        if(newWidth + offX > pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if(newHeight + offY > pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for(int y = newY; y < newHeight; y++){
            for(int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, image.getPixels()[x + y * image.getWidth()]);
            }
        }
    }

    public void drawImageTile(ImageTile imageTileRenderer, int offX, int offY, int tileX, int tileY){
        int newX = 0, newY = 0;
        int newWidth = imageTileRenderer.getTileWidth();
        int newHeight = imageTileRenderer.getTileHeight();

        // Doesn't render when image goes offscreen
        if(offX < -imageTileRenderer.getTileWidth()) return;
        if(offY < -imageTileRenderer.getTileHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        // Doesn't render parts of image that go offscreen (Clipping)
        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;

        if(newWidth + offX > pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if(newHeight + offY > pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for(int y = newY; y < newHeight; y++){
            for(int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, imageTileRenderer.getPixels()[(x + tileX * imageTileRenderer.getTileWidth()) + (y + tileY * imageTileRenderer.getHeight()) * imageTileRenderer.getWidth()]);
            }
        }
    }
}
