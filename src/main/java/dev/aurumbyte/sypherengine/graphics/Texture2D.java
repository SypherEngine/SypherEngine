package dev.aurumbyte.sypherengine.graphics;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;

public class Texture2D {
    private String filePath;
    private int textureID;

    public Texture2D(String filePath){
        this.filePath = filePath;

        //Generate textures on GPU
        textureID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, textureID);

        //Set texture params
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        //Pixelate
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        stbi_set_flip_vertically_on_load(true);

        ByteBuffer image = stbi_load(filePath, width, height, channels, 0);

        if(image != null){
            if(channels.get(0) == 3) {
                glTexImage2D(
                        GL_TEXTURE_2D,
                        0,
                        GL_RGB,
                        width.get(0),
                        height.get(0),
                        0,
                        GL_RGB,
                        GL_UNSIGNED_BYTE,
                        image
                );
            } else if(channels.get(0) == 4){
                glTexImage2D(
                        GL_TEXTURE_2D,
                        0,
                        GL_RGBA,
                        width.get(0),
                        height.get(0),
                        0,
                        GL_RGBA,
                        GL_UNSIGNED_BYTE,
                        image
                );
            } else assert false: "Error: Not a texture";
        } else assert false: "ERROR: Could not load image '" + filePath + "'";

    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    public void unBind(){
        glBindTexture(GL_TEXTURE_2D,0);
    }
}
