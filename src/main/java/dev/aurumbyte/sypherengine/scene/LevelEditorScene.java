package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.renderer.Shader;

public class LevelEditorScene extends Scene{
    private Shader shader = new Shader("assets/shaders/default.glsl");

    private int vertexID, fragmentID, shaderProgram;
    private int vaoID, vboID, eboID;

    private float[] vertexArray = {
            //positions              //colors
        0.5f, -0.5f, 0.0f,        1.0f, 0.0f, 0.0f,1.0f,
        -0.5f, 0.5f, 0.0f,        0.0f, 1.0f, 0.0f, 1.0f,
        0.5f, 0.5f, 0.0f,         0.0f, 0.0f, 1.0f, 1.0f,
            -0.5f, -0.5f, 0.0f,       0.0f, 1.0f, 1.0f, 1.0f
    };

    private int[] elementArray = {
        2, 1, 0,
        0, 1, 3,
    };

    public LevelEditorScene(){

    }

    @Override
    public void init(){
        shader.compile();
        //Shader test
        //currently only works using GL_TRIANGLES
        shader.init(vertexArray, elementArray, 3, 4);
    }

    @Override
    public void update(float deltaTime) {
        shader.use();
        shader.disable();
    }
}
