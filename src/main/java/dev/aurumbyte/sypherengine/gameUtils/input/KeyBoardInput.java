package dev.aurumbyte.sypherengine.gameUtils.input;

import dev.aurumbyte.sypherengine.SypherEngine;

import java.awt.event.*;

public class KeyBoardInput implements KeyListener {
    private SypherEngine engine;

    private final int NUM_KEYS = 256;
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];

    public KeyBoardInput(SypherEngine engine){
        this.engine = engine;
        engine.getWindow().getCanvas().addKeyListener(this);
    }

    public void update(){
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
    }

    public boolean isKey(int keyCode){
        return keys[keyCode];
    }

    public boolean isKeyUp(int keyCode){
        return !keys[keyCode] && keysLast[keyCode];
    }

    public boolean isKeyDown(int keyCode){
        return keys[keyCode] && !keysLast[keyCode];
    }

    public boolean horizontalAxis() {
        return isKey(Keys.D) || isKey(Keys.A) || isKey(Keys.RIGHT) || isKey(Keys.LEFT);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    public int getMovement() {
        if(isKey(Keys.D) || isKey(Keys.RIGHT)) return  1;
        if(isKey(Keys.A) || isKey(Keys.LEFT)) return -1;
        return 0;
    }
}
