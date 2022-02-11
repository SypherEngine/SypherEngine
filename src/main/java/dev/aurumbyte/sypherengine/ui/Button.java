package dev.aurumbyte.sypherengine.ui;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Button extends UIElement {
    String buttonText;
    ButtonShape buttonShape;
    Image buttonGraphics, buttonGraphicsOnHover;
    Vector2 buttonPos = new Vector2();

    public boolean hoverEvent = false, clickEvent = false;

    public Button(String text, Vector2 position, int width, int height){
        this.buttonText = text;
        this.height = height;
        this.width = width;
        this.buttonPos = position;

        this.buttonShape = ButtonShape.RECTANGLE;
    }

    public Button(String text, Vector2 position, int radius){
        this.buttonText = text;
        this.radius = radius;
        this.buttonPos = position;

        this.buttonShape = ButtonShape.CIRCLE;
    }

    @Override
    public void update(SypherEngine engine) {
        int xPos = buttonPos.xPos;
        int yPos = buttonPos.yPos;

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
    public void render(SypherEngine engine) {
        int xPos = buttonPos.xPos;
        int yPos = buttonPos.yPos;

        switch (buttonShape) {
            case RECTANGLE -> {
                if(buttonGraphics == null) {
                    if(hoverEvent){
                        engine.getRenderer().drawRectangle(buttonPos, (int)width, (int)height, true, color != null ? color.darker() : defaultColor.darker());
                        engine.getRenderer().drawText(buttonText,
                                new Vector2((float)(xPos + width/2), (float)(yPos + height/2)),
                                textColor != null ? textColor : defaultTextColour,
                                font != null ? font : defaultFont
                        );
                    }

                    engine.getRenderer().drawRectangle(buttonPos, (int)width, (int)height, true, color != null ? color : defaultColor);
                    engine.getRenderer().drawText(buttonText,
                            new Vector2((float)(xPos + width/2), (float)(yPos + height/2)),
                            textColor != null ? textColor : defaultTextColour,
                            font != null ? font : defaultFont
                    );
                } else {
                    if(hoverEvent) engine.getRenderer().drawImage(buttonGraphicsOnHover, buttonPos, (int)buttonGraphics.getWidth(), (int)buttonGraphics.getHeight());
                    engine.getRenderer().drawImage(buttonGraphics, buttonPos, (int)buttonGraphics.getWidth(), (int)buttonGraphics.getHeight());
                }
            }
            case CIRCLE -> engine.getRenderer().drawCircle(buttonPos, radius, true, color != null ? color : defaultColor);
        }
    }

    public void setPosition(int xPos, int yPos){
        buttonPos.setPosition(xPos, yPos);
    }

    public int getxPos() {
        return buttonPos.xPos;
    }

    public int getyPos() {
        return buttonPos.yPos;
    }

    public int getButtonWidth() {
        if(buttonShape == ButtonShape.RECTANGLE) return (int)width;
        else throw new Error("The Button is not a rectangle! It doesn't have a width!");
    }

    public int getButtonHeight() {
        if(buttonShape == ButtonShape.RECTANGLE) return (int)height;
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
        this.buttonPos.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.buttonPos.yPos = yPos;
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
