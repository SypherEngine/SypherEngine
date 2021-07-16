package dev.aurumbyte.sypherengine.renderer;

import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Shader {
    private int shaderProgramID;
    private String filePath;
    private String vertexSource, fragmentSource;
    private float[] vertexArray;
    private int[] elementArray;
    int vaoID, vboID, eboID;

    public Shader(String filePath) {
        this.filePath = filePath;
        String source = null;

        try {
            source = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "Error could not open file for shader : '" + filePath +"'";
        }

        String[] splitString = source.split("(#type)( )+([a-zA-Z]+)");

        if (splitString.length < 2) assert false : "Error shader '" + filePath + "' is not a valid shader";

        String[] shadertype = new String[splitString.length-1];

        int count = 1;
        int start = 0;
        int end = 0;

        while (count < splitString.length) {
            start = source.indexOf("#type", end) + 6;
            end = source.indexOf("\r\n", start);
            shadertype[count-1] = source.substring(start, end).trim();

            switch (shadertype[count-1]) {
                case "vertex":
                    vertexSource = splitString[count];
                    //System.out.println("vertex source = \n" + vertexSource);
                    break;

                case "fragment":
                    fragmentSource = splitString[count];
                    //System.out.println("fragment source = \n" + fragmentSource);
                    break;

                default:
                    assert false : "Error shader '" + filePath + "' has invalid types";
                    break;

            }
            ++count;
        }
    }

    public void compile(){
        int fragmentID, vertexID;

        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Default shader\n\t Vertex shader failed");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false: "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Default shader\n\t Fragment shader failed");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false: "";
        }

        shaderProgramID = glCreateProgram();
        glAttachShader(shaderProgramID, vertexID);
        glAttachShader(shaderProgramID, fragmentID);
        glLinkProgram(shaderProgramID);

        success = glGetProgrami(shaderProgramID, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(shaderProgramID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Default shader program\n\t Shader program failed");
            System.out.println(glGetProgramInfoLog(shaderProgramID, len));
            assert false: "";
        }
    }

    public void init(float[] vertexArray, int[] elementArray, int positionsSize, int colorSize){
        this.vertexArray = vertexArray;
        this.elementArray = elementArray;

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

        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize + colorSize) * floatSizeBytes;

        glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * floatSizeBytes);
        glEnableVertexAttribArray(1);
    }

    public void use(){
        glUseProgram(shaderProgramID);
        glBindVertexArray(vaoID);

        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);
    }

    public void disable(){
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);
        glUseProgram(0);
    }
}
