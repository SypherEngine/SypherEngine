package dev.aurumbyte.sypherengine.core;

import dev.aurumbyte.sypherengine.core.graphics.Renderer;
import dev.aurumbyte.sypherengine.core.logic.GameManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class SypherEngine extends Application {
    static GameManager game;
    static String title = "SypherEngine";

    static int width = 1280, height = 720;

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
        game.keyListener.pollScene(scene);
        game.mouseListener.pollScene(scene);

        game.init();

        Timer timer = new Timer() {
            @Override
            public void tick(float deltaTime) {
                renderer.clear();
                game.gameUpdate(deltaTime);
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
    }

    public static void run(String[] args)
    {
        launch(args);
    }

    public static void setScreenResolution(int width, int height){
        SypherEngine.width = width;
        SypherEngine.height = height;
    }
}