package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.config.EngineConfig;
import dev.aurumbyte.sypherengine.core.event.KeyListener;
import dev.aurumbyte.sypherengine.core.event.MouseListener;
import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class SypherEngine extends Application {
    public KeyListener keyListener = KeyListener.getInstance();
    public MouseListener mouseListener = MouseListener.getInstance();

    static GameManager game;
    static String title = "SypherEngine";
    SypherEngine INSTANCE = this;

    static int width, height;

    public void start(Stage stage)
    {
        stage.setTitle(title);

        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);

        Renderer renderer = new Renderer(root, scene, canvas);

        //listeners
        keyListener.pollScene(scene);
        mouseListener.pollScene(scene);

        game.init();

        Timer timer = new Timer() {
            @Override
            public void tick(float deltaTime) {
                renderer.clear();
                game.gameUpdate(INSTANCE, deltaTime);
                game.gameRender(renderer);
            }
        };

        timer.start();


        stage.show();
    }

    public static void init(GameManager game){
        SypherEngine.game = game;
    }

    public static void init(GameManager game, String title){
        SypherEngine.game = game;
        SypherEngine.title = title;
        SypherEngine.width = 1280;
        SypherEngine.height = 720;
    }

    public static void init(GameManager game, EngineConfig config){
        SypherEngine.game = game;
        SypherEngine.title = config.getTitle();
        SypherEngine.width = config.getScreenWidth();
        SypherEngine.height = config.getScreenHeight();
    }

    public void setScene(dev.aurumbyte.sypherengine.components.scene.Scene scene){
        game.setCurrentScene(scene);
    }

    public static void run(String[] args)
    {
        launch(args);
    }
}