package dev.aurumbyte.sypherengine.util.primitives;

import dev.aurumbyte.sypherengine.util.math.Mathf;
import dev.aurumbyte.sypherengine.util.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Polygon2D extends Primitive {
    List<Vector2> points = new ArrayList<>();
    float stroke = 1.0f;

    boolean isFilled = true;

    public Polygon2D(List<Vector2> points){
        this.points = points;
    }

    public Polygon2D(List<Vector2> points, float stroke){
        this.points = points;
        this.stroke = stroke;
        this.isFilled = false;
    }

    public List<Vector2> getPoints() {
        return points;
    }

    public double[] getPointsAsSingleArray(){
        double[] array = new double[points.size() * 2];
        List<Double> temp = new ArrayList<>();

        for(Vector2 point: points){
            temp.add((double)point.xPos);
            temp.add((double)point.yPos);
        }

        for(int i = 0; i < temp.size(); i++){
            array[i] = temp.get(i);
        }

        return array;
    }

    public void rotate(Vector2 origin, float degrees){
        for(Vector2 point: points){
            Mathf.rotate(point, degrees, origin);
        }
    }

    public void translate(Vector2 translationVector){
        for(Vector2 point: points){
            point.add(translationVector);
        }
    }

    public void translateX(float x){
        for(Vector2 point: points){
            point.xPos += x;
        }
    }

    public void translateY(float y){
        for(Vector2 point: points){
            point.yPos += y;
        }
    }

    public boolean isFilled(){
        return this.isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public void setStroke(float stroke) {
        this.stroke = stroke;
    }

    public void setPoints(List<Vector2> points) {
        this.points = points;
    }

    public float getStroke() {
        return stroke;
    }
}
