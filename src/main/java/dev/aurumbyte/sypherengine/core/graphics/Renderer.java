package dev.aurumbyte.sypherengine.core.graphics;

import dev.aurumbyte.sypherengine.components.Entity;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    Pane pane;
    Scene scene;
    Canvas canvas;
    GraphicsContext graphicsContext;
    List<Entity> entities = new ArrayList<>();

    Image background;
    Color backgroundColor;
    Font defaultFont = Font.font("Poppins", FontWeight.NORMAL, 18);

    public Renderer(Pane pane, Scene scene, Canvas canvas){
        this.pane = pane;
        this.scene = scene;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void clear(){
        graphicsContext.setFill( backgroundColor != null ? backgroundColor : new Color(0, 0, 0, 0) );
        graphicsContext.fillRect(0,0, canvas.getWidth(),canvas.getHeight());
    }

    public void addEntities(List<Entity> entityList){
        entities.addAll(entityList);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void renderEntities() {
        graphicsContext.save();

        if(background != null) graphicsContext.drawImage(background, 0, 0);

        entities.forEach(entity -> {
            transformContext(entity);
            entity.render(this);
        });

        graphicsContext.restore();
    }

    public void transformContext(Entity entity){
        Point2D centre = entity.getCenter();
        Rotate r = new Rotate(entity.getRotation(), centre.getX(), centre.getY());
        graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /*
     * =======================================================================
     * ========================== RENDER FUNCTIONS ===========================
     * =======================================================================
     */

    public void drawRectangle(int xPos, int yPos, int width, int height, boolean isFilled, Color color) {
        Rectangle rectangle = new Rectangle();
        rectangle.setX(xPos);
        rectangle.setY(yPos);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        if(isFilled) rectangle.setFill(color);
        else rectangle.setStroke(color);

        pane.getChildren().add(rectangle);
    }

    public void drawCircle(int xPos, int yPos, int radius, boolean isFilled, Color color) {
        Circle circle = new Circle();
        circle.setCenterX(xPos);
        circle.setCenterY(yPos);
        circle.setRadius(radius);

        if(isFilled) circle.setFill(color);
        else circle.setStroke(color);

        pane.getChildren().add(circle);
    }

    public void drawImage(Image image, int xPos, int yPos, int width, int height) {
        graphicsContext.drawImage(image, xPos, yPos, width, height);
    }

    public void drawImageTile(ImageTile imageTile, int xPos, int yPos, int tileX, int tileY){
        graphicsContext.drawImage(
                imageTile.getImageTile(tileX, tileY),
                xPos,
                yPos,
                imageTile.getTileWidth(),
                imageTile.getTileHeight()
        );
    }

    public void drawText(String textContent, int xPos, int yPos, Color color, Font font){
        Text text = new Text();
        text.setText(textContent);
        text.setFill(color);
        text.setX(xPos);
        text.setY(yPos);
        text.setFont(font);

        pane.getChildren().add(text);
    }

    /*
    * =======================================================================
    * ======================== GETTERS AND SETTERS ==========================
    * =======================================================================
    */

    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Font getDefaultFont() {
        return defaultFont;
    }

    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    public Point2D getScreenCenter(){
        return new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2);
    }
}