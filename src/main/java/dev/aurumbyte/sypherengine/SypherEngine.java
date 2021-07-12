package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.utils.GameWindow;

public class SypherEngine implements Runnable{
    private boolean render = false;
    private Thread thread;
    private boolean running = false;
    private double UPDATE_LIMIT = 1.0 / 60.0;

    private GameWindow window;

    private double frameTime = 0;
    private int frames = 0;
    private int fps = 0;

    private int width = 320;
    private int height = 240;
    private float scale = 4F;
    private String title = "SypherEngine v.0.0.1";

    public void SypherEngine(){

    }

    public void start(){
        window = new GameWindow(this);

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
                //TODO : Update game

                if(frameTime >= 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if(render){
                //TODO : render game
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

    public static void main(String[] args){
        SypherEngine engine = new SypherEngine();
        engine.start();
    }
}
