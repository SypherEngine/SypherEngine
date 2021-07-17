package dev.aurumbyte.sypherengine.game.listeners;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double xPos, yPos, lastX, lasY;
    private boolean[] mouseButtonPressed = new boolean[3];
    private boolean isDragging;

    private MouseListener(){
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lasY = 0.0;
    }

    private static MouseListener get(){
        if(MouseListener.instance == null) instance = new MouseListener();
        return instance;
    }

    public static void mousePosCallback(long window, double xPos, double yPos){
        get().lastX = get().xPos;
        get().lasY = get().yPos;
        get().xPos = xPos;
        get().yPos = yPos;

        get().isDragging = get().mouseButtonPressed[0] || get().mouseButtonPressed[1] || get().mouseButtonPressed[2];
    }

    public static void mouseButtonCallback(long window, int button, int action, int mods){
        if(action == GLFW_PRESS) if(button < get().mouseButtonPressed.length) get().mouseButtonPressed[button] = true;

        else if(action == GLFW_RELEASE) {
            if(button < get().mouseButtonPressed.length) {
                get().mouseButtonPressed[button] = false;
                get().isDragging = false;
            }
        }
    }

    public static void mouseScrollCallback(long window, double xOffset, double yOffset){
        get().scrollX = xOffset;
        get().scrollY = yOffset;
    }

    public static void endFrame(){
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().xPos;
        get().lasY = get().yPos;
    }

    public static float getX(){
        return (float) get().xPos;
    }

    public static float getY(){
        return (float) get().yPos;
    }

    public static float getDifferenceX(){
        return (float) (get().lastX - get().xPos);
    }

    public static float getDifferenceY(){
        return (float) (get().lastX - get().xPos);
    }

    public static float scrollX(){
        return (float) get().scrollX;
    }

    public static float scrollY(){
        return (float) get().scrollY;
    }

    public static boolean isDragging(){
        return get().isDragging;
    }

    public static boolean getMouseButtonDown(int button){
        if(button < get().mouseButtonPressed.length) return get().mouseButtonPressed[button];
        else return false;
    }
}
