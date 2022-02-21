package dev.aurumbyte.sypherengine.ui;

import dev.aurumbyte.sypherengine.components.Entity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class UIElement extends Entity {
    int radius;

    Color color;
    Color defaultColor = Color.BEIGE;

    Color textColor;
    Color defaultTextColour = Color.BLACK;

    Font font;
    Font defaultFont = new Font("Tahoma", 20);
}
