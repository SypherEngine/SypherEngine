package dev.aurumbyte.sypherengine.gui;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import javafx.scene.paint.Color;

public class Button extends Entity {
    String text;
    int xPos, yPos, width, height, radius;
    ButtonShape buttonShape;

    Color color;
    Color defaultColor = Color.BEIGE;

    public Button(String text, int width, int height){
        this.text = text;
        this.height = height;
        this.width = width;
        this.buttonShape = ButtonShape.RECTANGLE;
    }

    public Button(String text, int radius){
        this.text = text;
        this.radius = radius;
        this.buttonShape = ButtonShape.CIRCLE;
    }

    @Override
    public void update(float deltaTime) {
        switch (buttonShape) {
            case RECTANGLE -> {

            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        switch (buttonShape) {
            case RECTANGLE -> renderer.drawRectangle(xPos, yPos, width, height, true, color != null ? color : defaultColor);
            case CIRCLE -> renderer.drawCircle(xPos, yPos, radius, true, color != null ? color : defaultColor);
        }
    }

    public void setPosition(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public String getText() {
        return text;
    }

    public int getButtonWidth() {
        if(buttonShape == ButtonShape.RECTANGLE) return width;
        else throw new Error("The Button is not a rectangle! It doesn't have a width!");
    }

    public int getButtonHeight() {
        if(buttonShape == ButtonShape.RECTANGLE) return height;
        else throw new Error("The Button is not a rectangle! It doesn't have a height!");
    }

    public int getRadius() {
        if(buttonShape == ButtonShape.CIRCLE) return radius;
        else throw new Error("The Button is not a circle! It doesn't have a radius!");
    }

    public Color getColor() {
        return color;
    }

    public void setWidth(int width) {
        if(buttonShape == ButtonShape.RECTANGLE) this.width = width;
        else throw new Error("The button is not a rectangle! You cannot set a width value");
    }

    public void setHeight(int height) {
        if(buttonShape == ButtonShape.RECTANGLE) this.height = height;
        else throw new Error("The button is not a rectangle! You cannot set a height value");
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setRadius(int radius) {
        if(buttonShape == ButtonShape.RECTANGLE) this.radius = radius;
        else throw new Error("The button is not a circle! You cannot set a radius value to a circle!");
    }

    public enum ButtonShape {
        RECTANGLE, CIRCLE, POLYGON
    }
}
