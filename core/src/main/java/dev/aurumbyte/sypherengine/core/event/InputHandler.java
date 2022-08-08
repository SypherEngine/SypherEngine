package dev.aurumbyte.sypherengine.core.event;

public class InputHandler {
    public KeyListener keyListener;
    public MouseListener mouseListener;

    public InputHandler(KeyListener keyListener, MouseListener mouseListener){
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;
    }
}
