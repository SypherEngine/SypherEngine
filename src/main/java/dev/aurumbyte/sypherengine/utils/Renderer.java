package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.utils.image.Image;
import dev.aurumbyte.sypherengine.utils.image.AssetRequests;
import dev.aurumbyte.sypherengine.utils.image.ImageTile;
import dev.aurumbyte.sypherengine.utils.light.Light;

import java.awt.image.DataBufferInt;
import java.util.*;

public class Renderer {
    private ArrayList<AssetRequests.ImageRequest> imageRequests = new ArrayList<>();
    private ArrayList<AssetRequests.LightRequest> lightRequests = new ArrayList<>();

    private int pixelWidth, pixelHeight;
    private int[] pixels;
    private int[] zBuffer;
    private int zDepth = 0;

    private int camX, camY;

    private int[] lightMap;
    private int[] lightBlock;

    private int ambientColor = 0xff232323;

    private boolean processing;

    public Renderer(SypherEngine engine){
        pixelWidth = engine.getWidth();
        pixelHeight = engine.getHeight();
        pixels = ((DataBufferInt) engine.getWindow().getImage().getRaster().getDataBuffer()).getData();
        zBuffer = new int[pixels.length];
        lightMap = new int[pixels.length];
        lightBlock = new int[pixels.length];
    }

    public void clear(){
        for(int i = 0; i < pixels.length; i++){
            pixels[i] = 0;
            zBuffer[i] = 0;
            lightMap[i] = ambientColor;
            lightBlock[i] = 0;
        }
    }

    public void process(){
        processing = true;

        imageRequests.sort(Comparator.comparingInt(o -> o.zDepth));

        for(int i = 0; i < imageRequests.size(); i++){
            AssetRequests.ImageRequest imageRequest = this.imageRequests.get(i);
            setzDepth(imageRequest.zDepth);
            drawImage(imageRequest.image, imageRequest.offX, imageRequest.offY);
        }

        for(int i = 0; i < lightRequests.size(); i++){
            AssetRequests.LightRequest lightRequest = this.lightRequests.get(i);
            drawLightRequest(lightRequest.light, lightRequest.xPos, lightRequest.yPos);
        }

        for(int i = 0; i < pixels.length; i++){
            float red = ((lightMap[i] >> 16) & 0xff) / 255f;
            float green = ((lightMap[i] >> 8) & 0xff) / 255f;
            float blue = (lightMap[i] & 0xff) / 255f;

            pixels[i] = ((int)(((pixels[i] >> 16) & 0xff) * red) << 16 | (int)(((pixels[i] >> 8) & 0xff) * green) << 8 | (int)((pixels[i] & 0xff) * blue));
        }

        imageRequests.clear();
        lightRequests.clear();
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

            pixels[index] = (newRed << 16 | newGreen << 8 | newBlue);

        }
    }

    public void setLightMap(int x, int y, int value){
        if(x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) return;

        int baseColor = lightMap[x + y * pixelWidth];

        int maxRed = Math.max((baseColor >> 16) & 0xff, (value >> 16) & 0xff);
        int maxGreen = Math.max((baseColor >> 8) & 0xff, (value >> 8) & 0xff);
        int maxBlue = Math.max(baseColor & 0xff, value & 0xff);

        lightMap[x + y * pixelWidth] = (maxRed << 16 | maxGreen << 8 | maxBlue);
    }

    public void setLightBlock(int x, int y, int value){
        if(x < 0 || x >= pixelWidth || y < 0 || y >= pixelHeight) return;
        if(zBuffer[x + y * pixelWidth] > zDepth) return;

        lightBlock[x + y * pixelWidth] = value;
    }

    public void drawImage(Image image, int offX, int offY){
        offX -= camX;
        offY -= camY;

        if(image.isAlpha() && !processing) {
            imageRequests.add(new AssetRequests.ImageRequest(image, zDepth, offX, offY));
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
                setLightBlock(x + offX, y + offY, image.getLightBlock());
            }
        }
    }

    public void drawImageTile(ImageTile imageTile, int offX, int offY, int tileX, int tileY){
        offX -= camX;
        offY -= camY;

        if(imageTile.isAlpha() && !processing) {
            imageRequests.add(new AssetRequests.ImageRequest(imageTile.getTileImage(tileX, tileY), zDepth, offX, offY));
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
                setPixel(x + offX, y + offY, imageTile.getPixels()[(x + tileX * imageTile.getTileWidth()) + (y + tileY * imageTile.getTileHeight()) * imageTile.getWidth()]);
                setLightBlock(x + offX, y + offY, imageTile.getLightBlock());
            }
        }
    }

    public void drawRect(int offX, int offY, int width, int height, int color){
        offX -= camX;
        offY -= camY;

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
        offX -= camX;
        offY -= camY;

        // Doesn't render parts of image that go offscreen (Clipping)
        //if(offX < -width) return;
        //if(offY < -height) return;
        //if(offX > pixelWidth) return;
        //if(offY > pixelHeight) return;

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                setPixel(x + offX, y + offY, color);
            }
        }
    }

    public void setzDepth(int zDepth) {
        this.zDepth = zDepth;
    }

    public void drawLight(Light light, int offX, int offY){
      lightRequests.add(new AssetRequests.LightRequest(light, offX, offY));
    }

    private void drawLightRequest(Light light, int offX, int offY){
        offX -= camX;
        offY -= camY;

        for(int i = 0; i <= light.getDiameter(); i++){
            drawLightLine(light, light.getRadius(), light.getRadius(), i, 0, offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(), i, light.getDiameter(), offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(), 0, i, offX, offY);
            drawLightLine(light, light.getRadius(), light.getRadius(), light.getDiameter(), i, offX, offY);
        }
    }

    private void drawLightLine(Light light, int x0, int y0, int x1, int y1, int offX, int offY){
        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int sx = x0 < x1 ? 1 : -1;
        int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int err2;

        while(true){
            int screenX = x0 - light.getRadius() + offX;
            int screenY = y0 - light.getRadius() + offY;

            if(screenX == 0 || screenX >= pixelWidth || screenY == 0 || screenY >= pixelWidth) return;

            int lightColor = light.getLightValue(x0, y0);
            if(lightColor == 0) return;
            if(lightBlock[screenX + screenY * pixelWidth] == Light.FULL) return;

            setLightMap(screenX, screenY, lightColor);

            if(x0 == x1 && y0 == y1) break;
            err2 = 2 * err;

            if(err2 > -1 * dy){
                err -= dy;
                x0 += sx;
            }

            if(err2 < dx){
                err += dx;
                y0 += sy;
            }
        }
    }

    public void setAmbientColor(int ambientColor) {
        this.ambientColor = ambientColor;
    }

    public void setCamX(int camX) {
        this.camX = camX;
    }

    public void setCamY(int camY) {
        this.camY = camY;
    }

    public void setCamPos(int camX, int camY) {
        this.camX = camX;
        this.camY = camY;
    }
}
