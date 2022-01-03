package dev.aurumbyte.sypherengine.math;

public class Vector2 {
    private int x, y;

    public Vector2(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2(Vector2 vector2){
        this.x = vector2.x;
        this.y = vector2.y;
    }

    public Vector2 addVectors(Vector2 a, Vector2 b){
        return new Vector2(a.x + b.x, a.y + b.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setValue(int x, int y){
        this.x = x;
        this.y = y;
    }
}
