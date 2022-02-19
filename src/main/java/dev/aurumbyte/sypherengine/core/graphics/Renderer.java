package dev.aurumbyte.sypherengine.core.graphics;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.ArrayList;
import java.util.List;

public class Renderer {
    Group group;
    Scene scene;
    Canvas canvas;
    GraphicsContext graphicsContext;
    List<Entity> entities = new ArrayList<>();

    Image background;
    Color backgroundColor = Color.WHITE;
    Font defaultFont = Font.font("Poppins", FontWeight.NORMAL, 18);

    public Renderer(Group group, Scene scene, Canvas canvas){
        this.group = group;
        this.scene = scene;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public void clear(){
        group.getChildren().clear();
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void addEntities(List<Entity> entityList){
        entities.addAll(entityList);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void transformContext(Entity entity){
        Vector2 centre = entity.getCenter();
        Rotate r = new Rotate(entity.getRotation(), centre.xPos, centre.yPos);
        graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /*
     * =======================================================================
     * ========================== RENDER FUNCTIONS ===========================
     * =======================================================================
     */

    public void drawRectangle(Vector2 position, int width, int height, boolean isFilled, Paint paint) {
        Rectangle rectangle = new Rectangle();

        rectangle.setX(position.xPos);
        rectangle.setY(position.yPos);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        rectangle.setRotate(position.getRotation());

        if(isFilled) rectangle.setFill(paint);
        else rectangle.setStroke(paint);

        group.getChildren().add(rectangle);
    }

    public void drawCircle(Vector2 position, int radius, boolean isFilled, Paint paint) {
        Circle circle = new Circle();
        circle.setCenterX(position.xPos);
        circle.setCenterY(position.yPos);
        circle.setRadius(radius);
        circle.setRotate(position.getRotation());

        if(isFilled) circle.setFill(paint);
        else circle.setStroke(paint);

        group.getChildren().add(circle);
    }

    public void drawImage(Image image, Vector2 position, int width, int height) {
        graphicsContext.drawImage(image, position.xPos, position.yPos, width, height);
    }

    public void drawImageTile(ImageTile imageTile, Vector2 position, int tileX, int tileY){
        graphicsContext.drawImage(
                imageTile.getImageTile(tileX, tileY),
                position.xPos,
                position.yPos,
                imageTile.getTileWidth(),
                imageTile.getTileHeight()
        );
    }

    public void drawText(String textContent, Vector2 position, Paint paint, Font font){
        Text text = new Text();
        text.setText(textContent);
        text.setFill(paint);
        text.setX(position.xPos);
        text.setY(position.yPos);
        text.setFont(font);
        text.setRotate(position.getRotation());

        group.getChildren().add(text);
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
