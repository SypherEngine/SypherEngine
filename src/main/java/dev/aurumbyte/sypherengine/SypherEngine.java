package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.gameUtils.GameManager;
import dev.aurumbyte.sypherengine.gameUtils.input.KeyBoardInput;
import dev.aurumbyte.sypherengine.gameUtils.input.MouseInput;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.GameWindow;
import dev.aurumbyte.sypherengine.utils.logging.ByteLogger;
import dev.aurumbyte.sypherengine.utils.logging.logUtils.LoggerLevel;

import java.io.IOException;

public class SypherEngine implements Runnable{
    public static ByteLogger logger = new ByteLogger(false, LoggerLevel.DEBUG);

    private Thread thread;
    private final double UPDATE_LIMIT = 1.0 / 60.0;

    private GameWindow window;

    private Renderer renderer;

    private MouseInput mouseInput;
    private KeyBoardInput keyBoardInput;
    private GameManager game;

    private double frameTime = 0;
    private int frames = 0;
    private int fps = 0;

    private int width;
    private int height;
    private float scale;
    private String title;
    private final String version = "0.1.0";

    public SypherEngine(GameManager game){
        this.title = "SypherEngine";
        this.scale = 1f;
        this.width = 1280;
        this.height = 720;
        this.game = game;
    }

    /*
    public static SypherEngine get(){
        if(engine == null) engine = new SypherEngine();
        return engine;
    }



    public void init(IGame sypherengine.test.game){
        SypherEngine.sypherengine.test.game = sypherengine.test.game;
    }
     */

    public void start(){
        try {
            logger.info("Initializing Engine... [Usage : SypherEngine v" + getVersion() + "]");
            window = new GameWindow(this);
            renderer = new Renderer(this);
            keyBoardInput = new KeyBoardInput(this);
            mouseInput = new MouseInput(this);

            thread = new Thread(this);

            logger.info("Engine initialized successfully! Running Game " + getTitle().replace(" - SypherEngine", ""));
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        boolean running = true;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        game.gameInit(this);

        while(running){
            boolean render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime =  firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_LIMIT){
                unprocessedTime -= UPDATE_LIMIT;
                render = true;
                game.gameUpdate(this, (float) UPDATE_LIMIT);

                keyBoardInput.update();
                mouseInput.update();

                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if(render){
                renderer.clear();
                game.gameRender(this, renderer);
                renderer.process();
                window.update();
                frames++ ;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    try {
                        logger.error(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        dispose();
    }

    public void stop(){

    }

    private void dispose(){

    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title){ this.title = title + " - SypherEngine"; }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public GameWindow getWindow() { return window; }

    public KeyBoardInput getKeyBoardInput() { return keyBoardInput; }

    public MouseInput getMouseInput() { return mouseInput; }

    public Renderer getRenderer() {
        return renderer;
    }

    public String getVersion() {
        return version;
    }
}
