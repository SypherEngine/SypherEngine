/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core.graphics;

import dev.aurumbyte.sypherengine.core.gameObject.GameObject;
import dev.aurumbyte.sypherengine.core.graphics.tiles.TilesetImage;
import dev.aurumbyte.sypherengine.util.ObjectMap;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import dev.aurumbyte.sypherengine.util.primitives.Box2D;
import dev.aurumbyte.sypherengine.util.primitives.Polygon2D;
import javafx.geometry.Point2D;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

import java.util.Optional;

/**
 * The renderer/graphics backend, containing all the rendering helper methods
 * @author AurumByte
 * @since v0.3.0
 */
public class Renderer {
    /**
     * The JavaFX group, used for adding shapes
     */
    Group group;

    /**
     * The Scene on which everything is rendered
     */
    Scene scene;

    /**
     * The JavaFX canvas, on which images and other stuff are rendered
     */
    Canvas canvas;

    /**
     * The GraphicsContext is used to render images and image tiles to the screen
     */
    GraphicsContext graphicsContext;

    /**
     * The background image to be rendered, if it is not null
     */
    Image background;

    /**
     *  A list to keep track of every shape/object to be rendered
     */
    ObjectMap<Color> renderedObjects = new ObjectMap<>();

    /**
     * Default background color
     */
    Color backgroundColor = Color.WHITE;

    /**
     * Default font
     */
    Font defaultFont = Font.font("Poppins", FontWeight.NORMAL, 18);

    /**
     * <p>Initializing the renderer</p>
     * @param group The group
     * @param scene The scene
     * @param canvas The canvas
     * @since 0.3.0
     */
    public Renderer(Group group, Scene scene, Canvas canvas) {
        this.group = group;
        this.scene = scene;
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    /**
     * <p>Clears all objects from the renderer queue</p>
     * @since 0.3.0
     */
    public void clear() {
        group.getChildren().clear();
        //renderedObjects.clear();
        graphicsContext.setFill(backgroundColor);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    /**
     * <p>Transforms on an entity(image)</p>
     * @since 0.3.0
     */
    public void transformContext(GameObject gameObject) {
        Vector2 centre = gameObject.getCenter();
        Rotate r = new Rotate(gameObject.getTransform().getRotation(), centre.xPos, centre.yPos);
        graphicsContext.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /*
     * =======================================================================
     * ========================== RENDER FUNCTIONS ===========================
     * =======================================================================
     */

    /**
     * <p>Drawing a rectangle of specified measurements</p>
     * @param position The position of the rectangle
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     * @param isFilled Specify whether the rectangle should be filled in or not
     * @param paint The Color or gradient to be filled in
     * @since 0.3.0
     */
    @Deprecated
    public void drawRectangle(Vector2 position, float width, float height, boolean isFilled, Paint paint) {
        Rectangle rectangle = new Rectangle();

        rectangle.setX(position.xPos);
        rectangle.setY(position.yPos);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        rectangle.setRotate(position.getRotation());

        if (isFilled) rectangle.setFill(paint);
        else {
            rectangle.setStroke(paint);
            rectangle.setFill(backgroundColor);
        }

        group.getChildren().add(rectangle);
    }

    public void drawBox(Box2D box, Paint paint){
        Rectangle rectangle = new Rectangle();

        // The coordinates entered by the user will be the center of the box
        rectangle.setX(box.getPosition().xPos - box.getWidth() / 2);
        rectangle.setY(box.getPosition().yPos - box.getHeight() / 2);
        rectangle.setWidth(box.getWidth());
        rectangle.setHeight(box.getHeight());

        rectangle.setRotate(box.getPosition().getRotation());
        if(box.isFilled()) rectangle.setFill(paint);
        else {
            rectangle.setStroke(paint);
            rectangle.setStrokeWidth(box.getStroke());
            rectangle.setFill(backgroundColor);
        }

        group.getChildren().add(rectangle);
    }

    /**
     * <p>Drawing a circle of specified measurements</p>
     * @param position The position of the circle
     * @param radius The radius of the circle
     * @param isFilled Specify whether the circle should be filled in or not
     * @param paint The Color or gradient to be filled in
     * @since 0.3.0
     */
    @Deprecated
    public void drawCircle(Vector2 position, float radius, boolean isFilled, Paint paint) {
        Circle circle = new Circle();
        circle.setCenterX(position.xPos);
        circle.setCenterY(position.yPos);
        circle.setRadius(radius);
        circle.setRotate(position.getRotation());

        if (isFilled) circle.setFill(paint);
        else{
            circle.setStroke(paint);
            circle.setFill(backgroundColor);
        }

        group.getChildren().add(circle);
    }

    public void drawCircle(dev.aurumbyte.sypherengine.util.primitives.Circle c, Paint paint){
        Circle circle = new Circle();
        circle.setCenterX(c.getPosition().xPos);
        circle.setCenterY(c.getPosition().yPos);
        circle.setRadius(c.getRadius());
        circle.setRotate(c.getPosition().getRotation());

        if (c.isFilled()) circle.setFill(paint);
        else{
            circle.setStroke(paint);
            circle.setStrokeWidth(c.getStroke());
            circle.setFill(backgroundColor);
        }

        group.getChildren().add(circle);
    }

    /**
     * <p>Drawing a image of specified measurements</p>
     * @param image The image to be rendered
     * @param position The position of the image
     * @param width The width of the image
     * @param height The height of the image
     * @since 0.3.0
     */
    public void drawImage(Image image, Vector2 position, float width, float height) {
        graphicsContext.drawImage(image, position.xPos - width/2, position.yPos - height/2, width, height);
    }

    /**
     * <p>Drawing a image tile of specified measurements</p>
     * @param tilesetImage The image tile to be rendered
     * @param position The position of the image
     * @param tileX The tile count from left to right, starting from 0
     * @param tileY The tile count from the top to bottom, starting from 0
     * @since 0.3.0
     */
    public void drawImageTile(TilesetImage tilesetImage, Vector2 position, int tileX, int tileY) {
        graphicsContext.drawImage(
                tilesetImage.getImageTile(tileX, tileY),
                position.xPos - tilesetImage.getTileWidth()/2f,
                position.yPos - tilesetImage.getTileHeight()/2f,
                tilesetImage.getTileWidth(),
                tilesetImage.getTileHeight());
    }

    /**
     * <p>Drawing text of specified measurements</p>
     * @param position The position of the text
     * @param textContent The text to be rendered
     * @param font The font of the text
     * @param paint The Color or gradient to be filled in
     * @since 0.3.0
     */
    public void drawText(String textContent, Vector2 position, Paint paint, Font font) {
        Text text = new Text();
        text.setText(textContent);
        text.setFill(paint);
        text.setX(position.xPos);
        text.setY(position.yPos);
        text.setFont(font);
        text.setRotate(position.getRotation());

        group.getChildren().add(text);
    }

    /**
     * <p>Drawing text of specified measurements</p>
     * @param start The start position of the line
     * @param end The end position of the line
     * @param paint The Color or gradient to be filled in
     * @since 0.3.0
     */
    @Deprecated
    public void drawLine(Vector2 start, Vector2 end, Optional<Paint> paint) {
        Line line = new Line();
        line.setStartX(start.xPos);
        line.setStartY(start.yPos);
        line.setEndX(end.xPos);
        line.setEndY(end.yPos);

        paint.ifPresent(line::setStroke);

        group.getChildren().add(line);
    }

    public void drawLine(dev.aurumbyte.sypherengine.util.primitives.Line l, Optional<Paint> paint){
        Line line = new Line();
        line.setStartX(l.getStart().xPos);
        line.setStartY(l.getStart().yPos);
        line.setEndX(l.getEnd().xPos);
        line.setEndY(l.getEnd().yPos);

        line.setStrokeWidth(l.getStroke());
        paint.ifPresent(line::setStroke);

        group.getChildren().add(line);
    }

    /**
     * <p>Drawing text of specified measurements</p>
     * @param coords the coordinates of the polygon vertices
     * @param isFilled Specify whether the polygon should be filled or not
     * @param paint The color or gradient to be filled in
     * @since 0.3.0
     */
    @Deprecated
    public void drawPolygon(double[] coords, boolean isFilled, Paint paint) {
        Polygon polygon = new Polygon(coords);
        if (isFilled) polygon.setFill(paint);
        else {
            polygon.setFill(backgroundColor);
            polygon.setStroke(paint);
        }

        group.getChildren().add(polygon);
    }

    public void drawPolygon(Polygon2D polygon2D, Paint paint){
        Polygon polygon = new Polygon(polygon2D.getPointsAsSingleArray());
        if (polygon2D.isFilled()) polygon.setFill(paint);
        else {
            polygon.setFill(backgroundColor);
            polygon.setStrokeWidth(polygon2D.getStroke());
            polygon.setStroke(paint);
        }

        group.getChildren().add(polygon);
    }

    public void drawLight(Lighting lightType, Vector2 position, Color color){
        javafx.scene.effect.Lighting lighting = new javafx.scene.effect.Lighting();

        switch (lightType){
            case SPOT -> {
                Light.Spot light = new Light.Spot();
                light.setColor(color);
                light.setX(position.xPos);
                light.setY(position.yPos);

                lighting.setLight(light);
            }

            case POINT -> {
                Light.Point light = new Light.Point();
                light.setColor(color);
                light.setX(position.xPos);
                light.setY(position.yPos);

                lighting.setLight(light);
            }

            default -> {
                Light.Distant light = new Light.Distant();
                light.setColor(color);

                lighting.setLight(light);
            }
        }

        setEffect(lighting);
    }

    public void drawDistantLightSource(Color color, float elevation, float azimuth){
        Light.Distant distantLight = new Light.Distant();
        distantLight.setElevation(elevation);
        distantLight.setAzimuth(azimuth);
        distantLight.setColor(color);

        javafx.scene.effect.Lighting lighting = new javafx.scene.effect.Lighting();
        lighting.setLight(distantLight);

        setEffect(lighting);
    }

    public void drawAmbientLight(AmbientLight ambientLight){
        if(!ambientLight.isLightOn()) ambientLight.setLightOn(true);
        group.getChildren().add(ambientLight);
    }
    public enum Lighting {
        DISTANT, POINT, SPOT, AMBIENT
    }

    /*
     * =======================================================================
     * ======================== GETTERS AND SETTERS ==========================
     * =======================================================================
     */

    /**
     * Gets the Graphics context
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    /**
     * Gets the background color
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the Graphics context
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Gets the default font
     */
    public Font getDefaultFont() {
        return defaultFont;
    }

    /**
     * Sets the default font
     */
    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    /**
     * Gets the screen center
     */
    public Point2D getScreenCenter() {
        return new Point2D(canvas.getWidth() / 2, canvas.getHeight() / 2);
    }

    public void setEffect(Effect effect){
        for(Node node : group.getChildren()){
            if(!(node instanceof Canvas)){
                node.setEffect(effect);
            }
        }
    }

    protected void drawObjects(){
        group.getChildren().addAll();
    }
}
