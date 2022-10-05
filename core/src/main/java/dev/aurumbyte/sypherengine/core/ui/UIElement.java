/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.ui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p>The UIElement interface, implemented by all UI classes</p>
 */
public abstract class UIElement {
    int radius;

    Color color;
    Color defaultColor = Color.BEIGE;

    Color textColor;
    Color defaultTextColour = Color.BLACK;

    Font font;
    Font defaultFont = new Font("Tahoma", 20);
}
