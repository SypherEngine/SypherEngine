package dev.aurumbyte.sypherengine.utils.image;

import dev.aurumbyte.sypherengine.utils.light.Light;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Image {
    private int width, height;
    private int[] pixels;
    private boolean isAlpha = false;
    private int lightBlock = Light.NONE;

    public Image(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(Image.class.getResourceAsStream(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = Objects.requireNonNull(image).getWidth();
        height = image.getHeight();
        pixels = image.getRGB(0, 0, width, height, null, 0, width);

        image.flush();
    }

    public Image(int[] pixels, int width, int height){
        this.pixels = pixels;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return pixels;
    }

    public void setPixels(int[] pixels) {
        this.pixels = pixels;
    }

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setAlpha(boolean alpha) {
        isAlpha = alpha;
    }

    public int getLightBlock() {
        return lightBlock;
    }

    public void setLightBlock(int lightBlock) {
        this.lightBlock = lightBlock;
    }
}
