/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.ui;

import dev.aurumbyte.sypherengine.core.SypherEngine;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p>The Button UI class for SypherEngine</p>
 * @author AurumByte
 * @since 0.3.1
 */
public class Button extends UIElement {
    /**
     * The button text
     */
    String buttonText;

    /**
     * The shape of button, whether round or rectangular
     */
    ButtonShape buttonShape;

    /**
     * Graphical buttons, one for regular, one for on hovering
     */
    Image buttonGraphics, buttonGraphicsOnHover;

    /**
     * The position of the button
     */
    Vector2 buttonPos = new Vector2();

    /**
     * The hover and click events of a Button
     */
    public boolean hoverEvent = false, clickEvent = false;

    /**
     * <p>Create a new button (rectangular) with specified parameters</p>
     * @param text The button text
     * @param position The position of button
     * @param width The width of button
     * @param height The height of the button
     * @since 0.3.1
     */
    public Button(String text, Vector2 position, int width, int height) {
        this.buttonText = text;
        this.height = height;
        this.width = width;
        this.buttonPos = position;

        this.buttonShape = ButtonShape.RECTANGLE;
    }

    /**
     * <p>Create a new button (round) with specified parameters</p>
     * @param text The button text
     * @param position The position of the button
     * @param radius The radius of the button
     * @since 0.3.1
     */
    public Button(String text, Vector2 position, int radius) {
        this.buttonText = text;
        this.radius = radius;
        this.buttonPos = position;

        this.buttonShape = ButtonShape.CIRCLE;
    }

    @Override
    public void update(float deltaTime) {
        float xPos = buttonPos.xPos;
        float yPos = buttonPos.yPos;

        switch (buttonShape) {
            case RECTANGLE -> {
                Point2D mousePos = mouseListener.getMousePos();
                hoverEvent = (mousePos.getX() > xPos && mousePos.getX() < xPos + width)
                        && (mousePos.getY() > yPos && mousePos.getY() < yPos + height);
                clickEvent = hoverEvent && mouseListener.isDown(MouseButton.PRIMARY);
            }

            case CIRCLE -> {
                Point2D mousePos = mouseListener.getMousePos();
                hoverEvent = Math.sqrt(((xPos + mousePos.getX()) * (xPos + mousePos.getX()))
                                + ((yPos + mousePos.getY()) * (yPos + mousePos.getY())))
                        < radius;
                clickEvent = hoverEvent && mouseListener.isDown(MouseButton.PRIMARY);
            }
        }
    }

    @Override
    public void render(SypherEngine engine) {
        float xPos = buttonPos.xPos;
        float yPos = buttonPos.yPos;

        switch (buttonShape) {
            case RECTANGLE -> {
                if (buttonGraphics == null) {
                    if (hoverEvent) {
                        engine.getRenderer()
                                .drawRectangle(
                                        buttonPos,
                                        (int) width,
                                        (int) height,
                                        true,
                                        color != null ? color.darker() : defaultColor.darker());
                        engine.getRenderer()
                                .drawText(
                                        buttonText,
                                        new Vector2((float) (xPos + width / 2), (float) (yPos + height / 2)),
                                        textColor != null ? textColor : defaultTextColour,
                                        font != null ? font : defaultFont);
                    }

                    engine.getRenderer()
                            .drawRectangle(
                                    buttonPos, (int) width, (int) height, true, color != null ? color : defaultColor);
                    engine.getRenderer()
                            .drawText(
                                    buttonText,
                                    new Vector2((float) (xPos + width / 2), (float) (yPos + height / 2)),
                                    textColor != null ? textColor : defaultTextColour,
                                    font != null ? font : defaultFont);
                } else {
                    if (hoverEvent)
                        engine.getRenderer()
                                .drawImage(buttonGraphicsOnHover, buttonPos, (int) buttonGraphics.getWidth(), (int)
                                        buttonGraphics.getHeight());
                    engine.getRenderer().drawImage(buttonGraphics, buttonPos, (int) buttonGraphics.getWidth(), (int)
                            buttonGraphics.getHeight());
                }
            }
            case CIRCLE -> engine.getRenderer()
                    .drawCircle(buttonPos, radius, true, color != null ? color : defaultColor);
        }
    }

    /**
     * <p>Sets the position of the button</p>
     * @param xPos The x position
     * @param yPos The y position
     * @since 0.3.1
     */
    public void setPosition(int xPos, int yPos) {
        buttonPos.setPosition(xPos, yPos);
    }

    /**
     * <p>Gets the x position</p>
     * @since 0.3.1
     */
    public float getxPos() {
        return buttonPos.xPos;
    }

    /**
     * <p>Gets the y position</p>
     * @since 0.3.1
     */
    public float getyPos() {
        return buttonPos.yPos;
    }

    /**
     * <p>Gets the button width (if it is rectangular)</p>
     * @since 0.3.1
     */
    public int getButtonWidth() {
        if (buttonShape == ButtonShape.RECTANGLE) return (int) width;
        else throw new Error("The Button is not a rectangle! It doesn't have a width!");
    }

    /**
     * <p>Gets the button height (if it is rectangular)</p>
     * @since 0.3.1
     */
    public int getButtonHeight() {
        if (buttonShape == ButtonShape.RECTANGLE) return (int) height;
        else throw new Error("The Button is not a rectangle! It doesn't have a height!");
    }

    /**
     * <p>Gets the radius of the button (if is it is round)</p>
     * @since 0.3.1
     */
    public int getRadius() {
        if (buttonShape == ButtonShape.CIRCLE) return radius;
        else throw new Error("The Button is not a circle! It doesn't have a radius!");
    }

    /**
     * <p>Gets the color of the button</p>
     * @since 0.3.1
     */
    public Color getColor() {
        return color;
    }

    /**
     * <p>Sets the width of the button (if it is rectangular)</p>
     * @param width The width of the button
     * @since 0.3.1
     */
    public void setWidth(int width) {
        if (buttonShape == ButtonShape.RECTANGLE) this.width = width;
        else throw new Error("The button is not a rectangle! You cannot set a width value");
    }

    /**
     * <p>Sets the button height (if it is rectangular)</p>
     * @param height The height of the button
     * @since 0.3.1
     */
    public void setHeight(int height) {
        if (buttonShape == ButtonShape.RECTANGLE) this.height = height;
        else throw new Error("The button is not a rectangle! You cannot set a height value");
    }

    /**
     * <p>Sets the button text (if it is rectangular)</p>
     * @param text The text of the button
     * @since 0.3.1
     */
    public void setButtonText(String text) {
        this.buttonText = text;
    }

    /**
     * <p>Sets the x position of the button</p>
     * @param xPos The x Position
     * @since 0.3.1
     */
    public void setxPos(int xPos) {
        this.buttonPos.xPos = xPos;
    }

    /**
     * <p>Sets the y position of the button</p>
     * @param yPos The y Position
     * @since 0.3.1
     */
    public void setyPos(int yPos) {
        this.buttonPos.yPos = yPos;
    }

    /**
     * <p>Sets the color of the button</p>
     * @param color The color of the button
     * @since 0.3.1
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * <p>Sets the text color of the button</p>
     * @param textColor The text Color
     * @since 0.3.1
     */
    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    /**
     * <p>Sets the radius of the button (if is it is round)</p>
     * @param radius The radius of the button
     * @since 0.3.1
     */
    public void setRadius(int radius) {
        if (buttonShape == ButtonShape.RECTANGLE) this.radius = radius;
        else throw new Error("The button is not a circle! You cannot set a radius value to a circle!");
    }

    /**
     * <p>Sets the font of the button</p>
     * @param font The font of the button
     * @since 0.3.1
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * <p>Sets the graphics of the button</p>
     * @param buttonGraphics The graphics of the button
     * @since 0.3.1
     */
    public void setButtonGraphics(Image buttonGraphics) {
        this.buttonGraphics = buttonGraphics;
    }

    /**
     * <p>Sets the graphics of the button (triggered on hover event)</p>
     * @param buttonGraphicsOnHover The graphics of the button (on hover)
     * @since 0.3.1
     */
    public void setButtonGraphicsOnHover(Image buttonGraphicsOnHover) {
        this.buttonGraphicsOnHover = buttonGraphicsOnHover;
    }

    public enum ButtonShape {
        RECTANGLE,
        CIRCLE,
        POLYGON
    }
}
