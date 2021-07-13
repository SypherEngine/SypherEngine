package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.game.GameManager;
import dev.aurumbyte.sypherengine.input.KeyBoardInput;
import dev.aurumbyte.sypherengine.input.MouseInput;
import dev.aurumbyte.sypherengine.utils.GameRenderer;
import dev.aurumbyte.sypherengine.utils.GameWindow;

import java.awt.event.KeyEvent;

public class SypherEngine implements Runnable{
    private boolean render = false;
    private Thread thread;
    private boolean running = false;
    private double UPDATE_LIMIT = 1.0 / 60.0;

    private GameWindow window;
    private GameRenderer renderer;

    private MouseInput mouseInput;
    private KeyBoardInput keyBoardInput;
    private GameManager game;

    private double frameTime = 0;
    private int frames = 0;
    private int fps = 0;

    private int width = 320;
    private int height = 240;
    private float scale = 3F;
    private String title = "SypherEngine v.0.0.1";

    public SypherEngine(GameManager gameManager) {
        this.game = gameManager;
    }

    public void start(){
        window = new GameWindow(this);
        renderer = new GameRenderer(this);
        keyBoardInput = new KeyBoardInput(this);
        mouseInput = new MouseInput(this);

       thread = new Thread(this);
       thread.run();
    }

    public void run(){
        running = true;

        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        while(running){
            render = false;

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
                window.update();
                frames++ ;
            } else {
                try {
                    thread.sleep(1);
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
