package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SypherEngine extends Application {
    public KeyListener keyListener = KeyListener.getInstance();
    public MouseListener mouseListener = MouseListener.getInstance();
    private Renderer renderer;

    static float fps;
    static float fixedUpdate;

    static GameManager game;
    static String title = "SypherEngine";
    SypherEngine INSTANCE = this;

    static int width, height;

    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        Canvas canvas = new Canvas(width, height);

        stage.setTitle(title);
        stage.setScene(scene);

        pane.getChildren().add(canvas);

        renderer = new Renderer(pane, scene, canvas);

        //listeners
        keyListener.pollScene(scene);
        mouseListener.pollScene(scene);

        game.gameInit(INSTANCE);

        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount( Timeline.INDEFINITE );

        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(1 / fixedUpdate),                // 60 FPS
                ae -> {
                    fps = (float)(1 / 0.016);

                    // Clear the canvas
                    renderer.clear();

                    game.gameUpdate(INSTANCE, 0.017f);
                    game.gameRender(renderer);
                });

        gameLoop.getKeyFrames().add(keyFrame);
        gameLoop.play();


        stage.show();
    }

    public static void init(GameManager game){
        SypherEngine.game = game;
        SypherEngine.width = 1280;
        SypherEngine.height = 720;
        SypherEngine.fixedUpdate = 60;
    }

    public static void init(GameManager game, String title){
        SypherEngine.game = game;
        SypherEngine.title = title;
        SypherEngine.width = 1280;
        SypherEngine.height = 720;
        SypherEngine.fixedUpdate = 60;
    }

    public static void init(GameManager game, EngineConfig config){
        SypherEngine.game = game;
        SypherEngine.title = config.getTitle() + (config.showFPS ? " | FPS: " + SypherEngine.fps : "");
        SypherEngine.width = config.getScreenWidth();
        SypherEngine.height = config.getScreenHeight();
        SypherEngine.fixedUpdate = config.getFixedUpdate();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setScene(dev.aurumbyte.sypherengine.components.scene.Scene scene){
        game.setCurrentScene(scene);
    }

    public static void run(String[] args)
    {
        launch(args);
    }
}