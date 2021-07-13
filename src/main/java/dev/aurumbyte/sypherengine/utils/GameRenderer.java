package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.SypherEngine;

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
}
