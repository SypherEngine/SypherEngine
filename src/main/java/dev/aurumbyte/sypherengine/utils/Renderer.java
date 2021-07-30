package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.image.ImageRequest;
import dev.aurumbyte.sypherengine.utils.image.ImageTile;

import java.awt.image.DataBufferInt;
import java.util.*;

public class Renderer {
    private ArrayList<ImageRequest> imageRequests = new ArrayList<>();

    private int pixelWidth, pixelHeight;
    private int[] pixels;
    private int[] zBuffer;
    private int zDepth = 0;


    private boolean processing;

    public Renderer(SypherEngine engine){
        pixelWidth = engine.getWidth();
        pixelHeight = engine.getHeight();
        pixels = ((DataBufferInt) engine.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
    }

    public void clear(){
        Arrays.fill(pixels, 0);
        Arrays.fill(zBuffer, 0);

        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
            zBuffer[i] = 0;
        }
    }

    public void process(){
        processing = true;

        Collections.sort(imageRequests, new Comparator<ImageRequest>() {
            @Override
            public int compare(ImageRequest o1, ImageRequest o2) {
                if(o1.zDepth < o2.zDepth) return -1;
                if(o1.zDepth > o2.zDepth) return 1;
                return 0;
            }
        });

        for(int i = 0; i < imageRequests.size(); i++){
            ImageRequest imageRequest = imageRequests.get(i);
            setzDepth(imageRequest.zDepth);
            drawImage(imageRequest.image, imageRequest.offX, imageRequest.offY);
        }

        imageRequests.clear();
        processing = false;
    }

    public void setPixel(int x, int y, int value){
        int alpha = ((value >> 24) & 0xff);
        int index = x + y * pixelWidth;

        if((x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) || alpha == 0) return;
        if(zBuffer[index] > zDepth) return;
        zBuffer[index] = zDepth;

        if(alpha == 255) pixels[index] = value;
        else {
            int pixelColor = pixels[index];

            int newRed = ((pixelColor >> 16) & 0xff) - (int)((((pixelColor >> 16) & 0xff) - ((value >> 16) & 0xff)) * (alpha / 255f));
            int newGreen = ((pixelColor >> 8) & 0xff) - (int)((((pixelColor >> 8) & 0xff) - ((value >> 8) & 0xff)) * (alpha / 255f));
            int newBlue = (pixelColor & 0xff) - (int)(((pixelColor & 0xff) - (value & 0xff)) * (alpha / 255f));

            pixels[index] = (255 << 24 | newRed << 16 | newGreen << 8 | newBlue);

        }

    }

    public void drawImage(Image image, int offX, int offY){
        if(image.isAlpha() && !processing) {
            imageRequests.add(new ImageRequest(image, zDepth, offX, offY));
            return;
        }

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

    public void drawImageTile(ImageTile imageTile, int offX, int offY, int tileX, int tileY){
        if(imageTile.isAlpha() && !processing) {
            imageRequests.add(new ImageRequest(imageTile.getTileImage(tileX, tileY), zDepth, offX, offY));
            return;
        }

        int newX = 0, newY = 0;
        int newWidth = imageTile.getTileWidth();
        int newHeight = imageTile.getTileHeight();

        // Doesn't render when image goes offscreen
        if(offX < -imageTile.getTileWidth()) return;
        if(offY < -imageTile.getTileHeight()) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        // Doesn't render parts of image that go offscreen (Clipping)
        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;

        if(newWidth + offX > pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if(newHeight + offY > pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for(int y = newY; y < newHeight; y++){
            for(int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, imageTile.getPixels()[(x + tileX * imageTile.getTileWidth()) + (y + tileY * imageTile.getHeight()) * imageTile.getWidth()]);
            }
        }
    }

    public void drawRect(int offX, int offY, int width, int height, int color){
        for(int y = 0; y <= height; y++){
            setPixel(offX, y + offY, color);
            setPixel(offX + width, y + offY, color);
        }

        for(int x = 0; x <= width; x++){
            setPixel(x + offX, offY, color);
            setPixel(x + offX, offY + height, color);
        }
    }

    public void drawFilledRect(int offX, int offY, int width, int height, int color){
        int newX = 0, newY = 0;
        int newWidth = width;
        int newHeight = height;

        // Doesn't render when image goes offscreen
        if(offX < -width) return;
        if(offY < -height) return;
        if(offX >= pixelWidth) return;
        if(offY >= pixelHeight) return;

        // Doesn't render parts of image that go offscreen (Clipping)
        if(offX < 0) newX -= offX;
        if(offY < 0) newY -= offY;

        if(newWidth + offX > pixelWidth) newWidth -= newWidth + offX - pixelWidth;
        if(newHeight + offY > pixelHeight) newHeight -= newHeight + offY - pixelHeight;

        for(int y = newY; y < newHeight; y++){
            for(int x = newX; x < newWidth; x++){
                setPixel(x + offX, y + offY, color);
            }
        }
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }

}
