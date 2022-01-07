package dev.aurumbyte.sypherengine.math;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.gameUtils.GameManager;

public class Unit {
    private static final float pixelPerMeter = GameManager.defaultTileSize / SypherEngine.scale;

    public static double convertPixelToMeters(int pixels){
        return -(pixels / pixelPerMeter);
    }

    public static double convertMetersToPixels(float meters){
        return -(meters * pixelPerMeter);
    }
}
