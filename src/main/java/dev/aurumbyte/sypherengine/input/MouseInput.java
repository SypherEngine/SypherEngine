package dev.aurumbyte.sypherengine.input;

import dev.aurumbyte.sypherengine.SypherEngine;

import java.awt.event.*;

public class MouseInput implements MouseListener, MouseMotionListener, MouseWheelListener {
    private SypherEngine engine;

    private final int NUM_BUTTONS = 5;
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

    private int mouseX;
    private int mouseY;
    private int mouseScroll;

    public MouseInput(SypherEngine engine){
        this.engine = engine;

        mouseX = 0;
        mouseY = 0;
        mouseScroll = 0;

        engine.getWindow().getCanvas().addMouseListener(this);
        engine.getWindow().getCanvas().addMouseMotionListener(this);
        engine.getWindow().getCanvas().addMouseWheelListener(this);
    }

    public void update(){
        mouseScroll = 0;
        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
    }

    public boolean isButton(int button){
        return buttons[button];
    }

    public boolean isButtonUp(int button){
        return !buttons[button] && buttonsLast[button];
    }

    public boolean isButtonDown(int button){
        return buttons[button] && !buttonsLast[button];
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttons[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = (int)(e.getX() / engine.getScale());
        mouseY = (int)(e.getY() / engine.getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        mouseScroll = e.getWheelRotation();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public int getMouseScroll() {
        return mouseScroll;
    }
}
