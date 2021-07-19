package dev.aurumbyte.sypherengine.graphics;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader {
    private int shaderProgramID;
    private String filePath;
    private String vertexSource, fragmentSource;
    private float[] vertexArray;
    private int[] elementArray;
    int vaoID, vboID, eboID;

    private boolean beingUsed = false;

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

    public void use(){
        if(!beingUsed) {
            glUseProgram(shaderProgramID);
            beingUsed = true;
        }
    }

    public void disable(){
        glUseProgram(0);
        beingUsed = false;
    }

    public void uploadMatrix4f(String varname, Matrix4f matrix4f){
        int varLoaction = glGetUniformLocation(shaderProgramID, varname);
        use();
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        matrix4f.get(matrixBuffer);

        glUniformMatrix4fv(varLoaction, false, matrixBuffer);
    }

    public void uploadMatrix3f(String varname, Matrix3f matrix3f){
        int varLoaction = glGetUniformLocation(shaderProgramID, varname);
        use();
        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(9);
        matrix3f.get(matrixBuffer);

        glUniformMatrix3fv(varLoaction, false, matrixBuffer);
    }

    public void uploadVector4f(String varname, Vector4f vector){
        int varLocation = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform4f(varLocation, vector.x, vector.y, vector.z, vector.w);
    }

    public void uploadVector3f(String varname, Vector3f vector){
        int varLocation = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform3f(varLocation, vector.x, vector.y, vector.z);
    }

    public void uploadVector2f(String varname, Vector2f vector){
        int varLocation = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform2f(varLocation, vector.x, vector.y);
    }

    public void uploadFloat(String varname, float val){
        int varLocation = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform1f(varLocation, val);
    }

    public void uploadInt(String varname, int val){
        int varLoaction = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform1i(varLoaction, val);
    }

    public void uploadTexture(String varname, int slot){
        int varLoaction = glGetUniformLocation(shaderProgramID, varname);
        use();
        glUniform1i(varLoaction, slot);
    }
}
