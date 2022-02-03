package io.github.sypherengine.ui;

import io.github.sypherengine.components.Entity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class UIElement extends Entity {
    int xPos, yPos, width, height, radius;

    Color color;
    Color defaultColor = Color.BEIGE;

    Color textColor;
    Color defaultTextColour = Color.BLACK;

    Font font;
    Font defaultFont = new Font("Tahoma", 20);
}
