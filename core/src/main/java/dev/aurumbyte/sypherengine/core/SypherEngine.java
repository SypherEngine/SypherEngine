/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.core.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.event.WindowEventListener;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.ecs.Domain;
import dev.aurumbyte.sypherengine.physics.PhysicsSystem2D;
import dev.aurumbyte.sypherengine.util.logging.ByteLogger;
import dev.aurumbyte.sypherengine.util.logging.logUtils.LoggerLevel;
import dev.aurumbyte.sypherengine.util.math.Vector2;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * <p>The main class, the heart of the game engine, where everything is run</p>
 * @author AurumByte
 * @since v0.3.0
 */
public class SypherEngine extends Application {
    /**
     * The KeyListener
     */
    public static KeyListener keyListener = KeyListener.getInstance();

    /**
     * The MouseListener
     */
    public static MouseListener mouseListener = MouseListener.getInstance();

    public WindowEventListener windowEventListener = new WindowEventListener();

    static Image icon;

    /**
     * The Renderer
     */
    Renderer renderer;

    /**
     * The Scene
     */
    Scene scene;

    /**
     * The Logger
     */
    public static final ByteLogger LOGGER = new ByteLogger(false, LoggerLevel.DEBUG);

    /**
     * Frames Per Second
     */
    static float fps;

    /**
     * The fixed number times the engine updates
     */
    static float fixedUpdate;

    /**
     * The Game, The only entrypoint into the engine's inner workings
     */
    static GameManager game;

    public static Domain domain = new Domain();

    /**
     * The Game window title
     */
    static String title = "SypherEngine";

    /**
     * The width and height of the screen
     */
    static float width, height;

    //TODO: integrate this into the engine fully
    PhysicsSystem2D physicsSystem;

    boolean isRunning = false;
    static boolean isResizable = false;

    /**
     * <p>The main game loop</p>
     * @param stage The main stage
     * @throws IOException IOException
     * @since v0.3.0
     */
    public void start(Stage stage) throws IOException {
        LOGGER.info("Initializing Engine...");

        // And the physics system is up and running, with our specified fixed update and
        // a specific gravity
        physicsSystem = new PhysicsSystem2D(fixedUpdate, new Vector2(0, 90));

        // Sets everything needed for the objects to render
        Group group = new Group();

        //TODO: find out what group.setAutoSizeChildren() does


        scene = new Scene(group, width, height);

        // The canvas to be rendered upon
        Canvas canvas = new Canvas(width, height);

        // Window events
        stage.setOnCloseRequest(windowEventListener);
        stage.setOnHidden(windowEventListener);
        stage.setOnShowing(windowEventListener);
        stage.setOnShown(windowEventListener);
        stage.setOnHidden(windowEventListener);

        //window resizing listener
        /*
        stage.heightProperty().addListener((observableValue, oldHeight, newHeight) -> {
            height = ((float)newHeight);
        });
        stage.widthProperty().addListener((observableValue, oldWidth, newWidth) -> {
            width = ((float)newWidth);
        });
         */

        // Setting the title and the screen
        stage.setTitle(title);

        //adding an icon to the window, if one is specified in the engine config
        if(icon != null) stage.getIcons().add(icon);

        stage.setScene(scene);
        stage.setResizable(isResizable);
        //stage.setResizable(false);

        // Initialize the renderer
        renderer = new Renderer(group, scene, canvas);

        // listeners
        keyListener.pollScene(scene);
        mouseListener.pollScene(scene);

        // initialize the game
        game.gameInit(this);

        LOGGER.info("Engine Initialized Successfully! Running " + title + "...");

        // The main game loop
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(1 / fixedUpdate), // 60 FPS
                ae -> {
                    isRunning = true;
                    fps = fixedUpdate;

                    renderer.clear();
                    group.getChildren().add(canvas);
                    group.getChildren().add(game.getCurrentScene().getCamera().getPerspectiveCamera());
                    scene.setCamera(game.getCurrentScene().getCamera().getPerspectiveCamera());

                    game.gameUpdate(1 / fps);
                    game.gameRender(this.getRenderer());
                });

        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();

        // shows the screen
        stage.show();

        if (windowEventListener.windowCloseRequested) game.dispose();
    }

    /**
     * <p>Initializing the engine with a game</p>
     * @param game the game to be run
     * @since v0.3.0
     */
    public static void init(GameManager game) {
        SypherEngine.game = game;
        SypherEngine.width = 1280;
        SypherEngine.height = 720;
        SypherEngine.fixedUpdate = 60;
        SypherEngine.icon = null;
        SypherEngine.isResizable = false;

        SypherEngine.fps = fixedUpdate;
    }

    /**
     * <p>Initializing the engine with a game and a game title</p>
     * @param game the game to be run
     * @param title the title of the game
     * @since v0.3.0
     */
    public static void init(GameManager game, String title) {
        SypherEngine.game = game;
        SypherEngine.title = title;
        SypherEngine.width = 1280;
        SypherEngine.height = 720;
        SypherEngine.fixedUpdate = 60;
        SypherEngine.icon = null;
        SypherEngine.isResizable = false;

        SypherEngine.fps = fixedUpdate;
    }

    /**
     * <p>Initializing the engine with a game and a config</p>
     * @param game the game to be run
     * @param config the configuration by which the game should be run
     * @since v0.3.0
     */
    public static void init(GameManager game, EngineConfig config) {
        SypherEngine.game = game;
        SypherEngine.fixedUpdate = config.getFixedUpdate();
        SypherEngine.title = config.getTitle();
        SypherEngine.width = config.getScreenWidth();
        SypherEngine.height = config.getScreenHeight();
        SypherEngine.icon = config.getIcon();
        SypherEngine.isResizable = config.getResizable();

        SypherEngine.fps = fixedUpdate;
    }

    /**
     * Gets the renderer
     * @since v0.3.0
     */
    public Renderer getRenderer() {
        return renderer;
    }

    /**
     * Gets the screen height
     * @since v0.3.0
     */
    public float getScreenHeight() {
        return height;
    }

    /**
     * Gets the screen width
     * @since v0.3.0
     */
    public float getScreenWidth() {
        return width;
    }

    /**
     * Gets the scene
     * @since v0.3.0
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Runs the engine
     * @since v0.3.0
     */
    public static void run() {
        launch();
    }
}
