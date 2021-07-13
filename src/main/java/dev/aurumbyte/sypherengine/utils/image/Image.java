package dev.aurumbyte.sypherengine.utils.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Image {
    private int width, height;
    private int[] pixels;

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
}
