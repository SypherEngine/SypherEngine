/* (C)2022 AurumByte */
package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import dev.aurumbyte.sypherengine.logging.ByteLogger;
import dev.aurumbyte.sypherengine.logging.logUtils.LoggerLevel;
import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * <p>The main class, the heart of the game engine, where everything is run</p>
 * @author AurumByte
 * @since v0.3.0
 */
public class SypherEngine extends Application {
    /**
     * The KeyListener
     */
    public KeyListener keyListener = KeyListener.getInstance();

    /**
     * The MouseListener
     */
    public MouseListener mouseListener = MouseListener.getInstance();

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
    private final ByteLogger LOGGER = new ByteLogger(false, LoggerLevel.DEBUG);

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

    /**
     * The Game window title
     */
    static String title = "SypherEngine";

    /**
     * The width and height of the screen
     */
    static int width, height;

    /**
     * <p>The main game loop</p>
     * @param stage The main stage
     * @throws IOException IOException
     * @since v0.3.0
     */
    public void start(Stage stage) throws IOException {
        LOGGER.info("Initializing Engine...");

        Group group = new Group();
        scene = new Scene(group, width, height);
        Canvas canvas = new Canvas(width, height);

        stage.setTitle(title);
        stage.setScene(scene);
        stage.setResizable(false);

        renderer = new Renderer(group, scene, canvas);

        // listeners
        keyListener.pollScene(scene);
        mouseListener.pollScene(scene);

        game.gameInit(this);

        LOGGER.info("Engine Initialized Successfully! Running Game " + title + "...");

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(1 / fixedUpdate), // 60 FPS
                ae -> {
                    fps = fixedUpdate;

                    renderer.clear();
                    group.getChildren().add(canvas);
                    group.getChildren().add(game.getCurrentScene().getCamera().getParallelCamera());

                    game.gameUpdate(1 / fps);
                    game.gameRender(this);
                });

        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();

        stage.show();
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
    public int getScreenHeight() {
        return height;
    }

    /**
     * Gets the screen width
     * @since v0.3.0
     */
    public int getScreenWidth() {
        return width;
    }

    /**
     * Gets the logger
     * @since v0.3.0
     */
    public ByteLogger getLogger() {
        return LOGGER;
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
     * @param args The arguments by which to run the game
     * @since v0.3.0
     */
    public static void run(String[] args) {
        launch(args);
    }
}
