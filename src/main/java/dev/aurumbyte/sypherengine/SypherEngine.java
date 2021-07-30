package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.game.IGame;
import dev.aurumbyte.sypherengine.game.input.KeyBoardInput;
import dev.aurumbyte.sypherengine.game.input.MouseInput;
import dev.aurumbyte.sypherengine.utils.Renderer;
import dev.aurumbyte.sypherengine.utils.GameWindow;

import java.awt.event.KeyEvent;

public class SypherEngine implements Runnable{
    private static SypherEngine engine;

    private Thread thread;
    private final double UPDATE_LIMIT = 1.0 / 60.0;

    private GameWindow window;
    private Renderer renderer;

    private MouseInput mouseInput;
    private KeyBoardInput keyBoardInput;
    private IGame game;

    private double frameTime = 0;
    private int frames = 0;
    private int fps = 0;

    private int width;
    private int height;
    private float scale;
    private String title;

    public SypherEngine(IGame game){
        this.title = "SypherEngine";
        this.scale = 3f;
        this.width = 320;
        this.height = 240;
        this.game = game;
    }

    /*
    public static SypherEngine get(){
        if(engine == null) engine = new SypherEngine();
        return engine;
    }



    public void init(IGame game){
        SypherEngine.game = game;
    }
     */

    public void start(){
        window = new GameWindow(this);
        renderer = new Renderer(this);
        keyBoardInput = new KeyBoardInput(this);
        mouseInput = new MouseInput(this);

       thread = new Thread(this);
       thread.start();
    }

    public void run(){
        boolean running = true;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

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
                game.update(this, (float) UPDATE_LIMIT);

                if(keyBoardInput.isKeyUp(KeyEvent.VK_A)){
                    System.out.println("A input successful");
                }


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
                game.render(this, renderer);
                renderer.process();
                window.update();
                frames++ ;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
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

}
