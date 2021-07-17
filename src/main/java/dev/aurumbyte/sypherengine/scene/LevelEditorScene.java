package dev.aurumbyte.sypherengine.scene;

import dev.aurumbyte.sypherengine.game.components.GameObject;
import dev.aurumbyte.sypherengine.game.renderer.Shader;
import dev.aurumbyte.sypherengine.game.renderer.Texture2D;
import dev.aurumbyte.sypherengine.game.renderer.components.SpriteRenderer;
import dev.aurumbyte.sypherengine.scene.camera.Camera;
import dev.aurumbyte.sypherengine.utils.Time;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene{
    private Shader shader = new Shader("assets/shaders/default.glsl");
    private Texture2D texture;
    GameObject gameObject;
    private boolean firstTime = false;

    private int vertexID, fragmentID, shaderProgram;
    private int vaoID, vboID, eboID;

    private float[] vertexArray = {
            //positions              //colors
        100.5f, -0.5f, 0.0f,        1.0f, 0.0f, 0.0f,1.0f,           1, 0,// Bottom right
        -0.5f, 100.5f, 0.0f,        0.0f, 1.0f, 0.0f, 1.0f,          0, 1,// Top left
        100.5f, 100.5f, 0.0f,         0.0f, 0.0f, 1.0f, 1.0f,        1, 1,// Top right
            -0.5f, -0.5f, 0.0f,       0.0f, 1.0f, 1.0f, 1.0f,        0, 0 // Bottom left
    };

    private int[] elementArray = {
        2, 1, 0,
        0, 1, 3,
    };

    public LevelEditorScene(){

    }

    @Override
    public void init(){
        System.out.println("Creating bois");
        this.gameObject = new GameObject("Testing go brrrrrrrrr");
        this.gameObject.addComponent(new SpriteRenderer());
        this.adGameObjectToScene(this.gameObject);

        this.camera = new Camera(new Vector2f());
        this.texture = new Texture2D("assets/test.png");
        shader.compile();
        //Shader test
        //currently only works using GL_TRIANGLES
        //shader.init(vertexArray, elementArray, 3, 4);

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int positionsSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int vertexSizeBytes = (positionsSize + colorSize + uvSize) * Float.BYTES;

        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionsSize + colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);
    }

    @Override
    public void update(float deltaTime) {
        camera.position.x -= deltaTime * 50.0f;
        camera.position.y -= deltaTime * 20.0f;

        shader.use();

        shader.uploadTexture("SLOT", 0);
        glActiveTexture(GL_TEXTURE0);
        texture.bind();

        shader.uploadMatrix4f("uProj", camera.getProjectionMatrix());
        shader.uploadMatrix4f("uView", camera.getViewMatrix());
        shader.uploadFloat("uTime", Time.getTime());
        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
        shader.disable();

        if(!firstTime) {
            System.out.println("Creating new");
            GameObject object = new GameObject("Object 2");
            object.addComponent(new SpriteRenderer());
            this.adGameObjectToScene(object);
            firstTime = true;
        }

        for (GameObject gameObject : this.gameObjects){
            gameObject.update(deltaTime);
        }
    }
}
