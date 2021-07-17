package dev.aurumbyte.sypherengine;

import dev.aurumbyte.sypherengine.game.listeners.KeyListener;
import dev.aurumbyte.sypherengine.game.listeners.MouseListener;
import dev.aurumbyte.sypherengine.scene.LevelEditorScene;
import dev.aurumbyte.sypherengine.scene.LevelScene;
import dev.aurumbyte.sypherengine.scene.Scene;
import dev.aurumbyte.sypherengine.utils.Time;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class SypherEngine {
    private int width, height;
    private String title;
    private static SypherEngine engine = null;
    private long window;

    private static int currentSceneIndex = -1;
    private static Scene currentScene;


    private SypherEngine(){
        this.width = 1280;
        this.height = 720;
        this.title = "Sypher Engine";
    }

    public static SypherEngine get(){
        if(SypherEngine.engine == null) engine = new SypherEngine();
        return engine;
    }

    public static void changeScene(int newScene){
        switch (newScene){
            case 0:
                currentScene = new LevelEditorScene();
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                currentScene = new LevelScene();
                currentScene.init();
                currentScene.start();
                break;
            default:
                assert false : "Unknown scene " + newScene + ".";
                break;
        }
    }

    public void run(){
        init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    public void init(){
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable



        // Create the window
        window = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);

        if ( window == NULL ) throw new IllegalStateException("Failed to create the GLFW window");

        glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);

        glfwSetKeyCallback(window, KeyListener::keyCallback);

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        SypherEngine.changeScene(0);
    }

    public void loop(){
        float startTime = Time.getTime();
        float endTime = Time.getTime();
        float deltaTime = -1.0f;

        while(!glfwWindowShouldClose(window)){
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            if(deltaTime >= 0) currentScene.update(deltaTime);

            glfwSwapBuffers(window);

            endTime = Time.getTime();
            deltaTime = endTime - startTime;
            startTime = endTime;
        }
    }
}
