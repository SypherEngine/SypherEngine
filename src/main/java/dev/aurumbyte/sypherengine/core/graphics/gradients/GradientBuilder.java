/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics.gradients;

import dev.aurumbyte.sypherengine.math.Vector2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.paint.*;

public class GradientBuilder {
    GradientType gradientType;
    List<Stop> stops = new ArrayList<>();
    Vector2 start, end, position;
    int focusAngle = 0, focusDistance = 0, radius = 0;
    boolean isProportional = true;
    CycleMethod cycleMethod = CycleMethod.NO_CYCLE;

    public GradientBuilder(Vector2 start, Vector2 end, List<Stop> stops) {
        this.start = start;
        this.end = end;
        this.stops = stops;

        this.gradientType = GradientType.LINEAR;
    }

    public GradientBuilder(Vector2 start, Vector2 end, Stop[] stops) {
        this.start = start;
        this.end = end;
        this.stops.addAll(Arrays.asList(stops));

        this.gradientType = GradientType.LINEAR;
    }

    public GradientBuilder(Vector2 center, int focusAngle, int focusDistance, int radius, List<Stop> stops) {
        this.stops = stops;
        this.position = center;
        this.focusAngle = focusAngle;
        this.focusDistance = focusDistance;
        this.radius = radius;

        this.gradientType = GradientType.RADIAL;
    }

    public GradientBuilder(Vector2 center, int focusAngle, int focusDistance, int radius, Stop[] stops) {
        this.stops.addAll(Arrays.asList(stops));
        this.position = center;
        this.focusAngle = focusAngle;
        this.focusDistance = focusDistance;
        this.radius = radius;

        this.gradientType = GradientType.RADIAL;
    }

    public Paint buildGradient() {
        switch (gradientType) {
            case LINEAR -> {
                return new LinearGradient(
                        start.xPos, start.yPos, end.xPos, end.yPos, isProportional, cycleMethod, stops);
            }

            case RADIAL -> {
                return new RadialGradient(
                        focusAngle,
                        focusDistance,
                        position.xPos,
                        position.yPos,
                        radius,
                        isProportional,
                        cycleMethod,
                        stops);
            }

            default -> {
                try {
                    throw new Exception("Gradient Type is either null or not set!");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
    }

    public GradientType getGradientType() {
        return gradientType;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setifProportional(boolean proportional) {
        isProportional = proportional;
    }

    public void setStart(Vector2 start) {
        this.start = start;
    }

    public void setEnd(Vector2 end) {
        this.end = end;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setCycleMethod(CycleMethod cycleMethod) {
        this.cycleMethod = cycleMethod;
    }

    enum GradientType {
        RADIAL,
        LINEAR
    }
}
