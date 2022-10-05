package dev.aurumbyte.sypherengine.util;

import dev.aurumbyte.sypherengine.util.math.Vector2;
import dev.aurumbyte.sypherengine.util.primitives.Box2D;
import dev.aurumbyte.sypherengine.util.primitives.Circle;
import dev.aurumbyte.sypherengine.util.primitives.Line;

public class DrawUtil {
    public static Circle createCircle(Vector2 position, float radius){
        return new Circle(position, radius);
    }

    public static Circle createCircleStroke(Vector2 position, float radius, float stroke){
        return new Circle(position, radius, stroke);
    }

    public static Line createLine(Vector2 start, Vector2 end){
        return new Line(start, end);
    }

    public static Line createLineStroke(Vector2 start, Vector2 end, float stroke){
        return new Line(start, end, stroke);
    }

    public static Box2D createBox(Vector2 position, float width, float height){
        return new Box2D(position, width, height);
    }

    public static Box2D createBoxStroke(Vector2 position, float width, float height, float stroke){
        return new Box2D(position, width, height, stroke);
    }
}
