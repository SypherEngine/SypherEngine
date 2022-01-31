package dev.aurumbyte.sypherengine.ui;

import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.SypherEngine;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Button extends UIElement {
    String buttonText;
    ButtonShape buttonShape;
    Image buttonGraphics, buttonGraphicsOnHover;

    public boolean hoverEvent = false, clickEvent = false;

    public Button(String text, int width, int height){
        this.buttonText = text;
        this.height = height;
        this.width = width;
        this.buttonShape = ButtonShape.RECTANGLE;
    }

    public Button(String text, int radius){
        this.buttonText = text;
        this.radius = radius;
        this.buttonShape = ButtonShape.CIRCLE;
    }

    @Override
    public void update(SypherEngine engine, float deltaTime) {
        switch (buttonShape) {
            case RECTANGLE -> {
                Point2D mousePos = engine.mouseListener.getMousePos();
                hoverEvent = (mousePos.getX() > xPos && mousePos.getX() < xPos + width) && (mousePos.getY() > yPos && mousePos.getY() < yPos + height);
                clickEvent = hoverEvent && engine.mouseListener.isDown(MouseButton.PRIMARY);
            }

            case CIRCLE -> {
                Point2D mousePos = engine.mouseListener.getMousePos();
                hoverEvent = Math.sqrt(((xPos + mousePos.getX()) * (xPos + mousePos.getX()))
                    +
                        ((yPos + mousePos.getY()) * (yPos + mousePos.getY()))) < radius ;
                clickEvent = hoverEvent && engine.mouseListener.isDown(MouseButton.PRIMARY);
            }
        }
    }

    @Override
    public void render(Renderer renderer) {
        switch (buttonShape) {
            case RECTANGLE -> {
                if(buttonGraphics == null) {
                    renderer.drawRectangle(xPos, yPos, width, height, true, color != null ? color : defaultColor);
                    renderer.drawText(buttonText,
                            xPos,
                            yPos + height / 2,
                            textColor != null ? textColor : defaultTextColour,
                            font != null ? font : defaultFont
                    );
                } else {
                    if(hoverEvent) renderer.drawImage(buttonGraphicsOnHover, xPos, yPos, (int)buttonGraphics.getWidth(), (int)buttonGraphics.getHeight());
                    renderer.drawImage(buttonGraphics, xPos, yPos, (int)buttonGraphics.getWidth(), (int)buttonGraphics.getHeight());
                }
            }
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

    public void setButtonText(String text) {
        this.buttonText = text;
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

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setRadius(int radius) {
        if(buttonShape == ButtonShape.RECTANGLE) this.radius = radius;
        else throw new Error("The button is not a circle! You cannot set a radius value to a circle!");
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setButtonGraphics(Image buttonGraphics) {
        this.buttonGraphics = buttonGraphics;
    }

    public void setButtonGraphicsOnHover(Image buttonGraphicsOnHover) {
        this.buttonGraphicsOnHover = buttonGraphicsOnHover;
    }

    public enum ButtonShape {
        RECTANGLE, CIRCLE, POLYGON
    }
}
