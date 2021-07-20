package dev.aurumbyte.sypherengine.graphics.renderer;

import dev.aurumbyte.sypherengine.SypherEngine;
import dev.aurumbyte.sypherengine.graphics.Shader;
import dev.aurumbyte.sypherengine.graphics.Texture2D;
import dev.aurumbyte.sypherengine.graphics.renderer.components.SpriteRenderer;
import dev.aurumbyte.sypherengine.utils.AssetPool;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class RenderBatch {
    /*
    * Vertex Format ----v
    *
    * Position                 Color                             Texture coordinates     Texture ID
    * float, float,            float, float, float, float,       float, float,           float
    *
    */

    private final int _PositionSize = 2;
    private final int _ColorSize = 4;
    private final int _VertexSize = 6;
    private final int _TextureCoordsSize = 2;
    private final int _TextureIDSize = 1;

    private final int _VertexSizeBytes = _VertexSize * Float.BYTES;

    private final int _PositionOffset = 0;
    private final int _ColorOffset = _PositionOffset + _PositionSize * Float.BYTES;
    private final int _TextureCoordsOffset = _ColorOffset + _ColorSize * Float.BYTES;
    private final int _TextureIDOffset = _TextureCoordsOffset + _TextureCoordsSize * Float.BYTES;

    private SpriteRenderer[] sprites;
    private int[] textureSlot = {0, 1, 2, 3, 4, 5, 6, 7};
    private int spriteNumber;
    private boolean hasRoom;
    private float[] vertices;

    private List<Texture2D> textures;
    private int vaoID, vboID;
    private int maxBatchSize;
    private Shader shader;

    public RenderBatch(int maxBatchSize){
        this.maxBatchSize = maxBatchSize;
        shader = AssetPool.getShader("assets/shaders/default.glsl");
        shader.compile();

        this.sprites = new SpriteRenderer[maxBatchSize];

        vertices = new float[maxBatchSize * 4 * _VertexSize];

        this.spriteNumber = 0;
        this.hasRoom = true;
        this.textures = new ArrayList<>();
    }

    public void addSprite(SpriteRenderer sprite){
        int index = this.spriteNumber;
        this.sprites[index] = sprite;
        this.spriteNumber++;

        if(sprite.getTexture() != null){
            if(!textures.contains(sprite.getTexture())) textures.add(sprite.getTexture());
        }

        loadVertexProperties(index);

        if(spriteNumber >= this.maxBatchSize){
            this.hasRoom = false;
        }
    }

    public void start(){
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, (long) vertices.length * Float.BYTES, GL_DYNAMIC_DRAW);

        int eboID = glGenBuffers();
        int[] indices = generateIndices();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices, GL_STATIC_DRAW);

        glVertexAttribPointer(0, _PositionSize, GL_FLOAT, false, _VertexSizeBytes, _PositionOffset);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, _ColorSize, GL_FLOAT, false, _VertexSizeBytes, _ColorOffset);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, _TextureCoordsSize, GL_FLOAT, false, _VertexSizeBytes, _TextureCoordsOffset);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, _TextureIDSize, GL_FLOAT, false, _VertexSizeBytes, _TextureIDOffset);
        glEnableVertexAttribArray(3);
    }

    public void render(){
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferSubData(GL_ARRAY_BUFFER, 0, vertices);

        //Use shader
        shader.use();
        shader.uploadMatrix4f("uProjection", SypherEngine.getScene().camera().getProjectionMatrix());
        shader.uploadMatrix4f("uView", SypherEngine.getScene().camera().getViewMatrix());

        for(int i = 0; i < textures.size(); i++){
            glActiveTexture(GL_TEXTURE0 + i + 1);
            textures.get(i).bind();
        }

        shader.uploadIntArray("uTextures", textureSlot);

        glBindVertexArray(vboID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, this.spriteNumber * 6, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glBindVertexArray(0);

        for(int i = 0; i < textures.size(); i++) textures.get(i).unBind();

        shader.disable();
    }

    private void loadVertexProperties(int index){
        SpriteRenderer sprite = this.sprites[index];

        int offset = index * 4 * _VertexSize;
        Vector4f color = sprite.getColor();
        Vector2f[] textureCoords = sprite.getTextureCoords();
        int textureID = 0;

        if(sprite.getTexture() != null){
            for(int i = 0; i < textures.size(); i++){
                if(textures.get(i) == sprite.getTexture()){
                    textureID = i + 1;
                    break;
                }
            }
        }

        float xAdd = 1.0f;
        float yAdd = 1.0f;
        for(int i = 0; i < 4; i++){
            switch(i){
                case 1 : yAdd = 0.0f; break;
                case 2 : xAdd = 0.0f; break;
                case 3 : yAdd = 1.0f; break;
            }

            //Position
            vertices[offset] = sprite.gameObject.transform.position.x * (xAdd * sprite.gameObject.transform.scale.x);
            vertices[offset + 1] = sprite.gameObject.transform.position.y * (yAdd * sprite.gameObject.transform.scale.y);

            //Color
            vertices[offset + 2] = color.x;
            vertices[offset + 3] = color.y;
            vertices[offset + 4] = color.z;
            vertices[offset + 5] = color.w;

            //Texture Coordinates
            vertices[offset + 6] = textureCoords[i].x;
            vertices[offset + 7] = textureCoords[i].y;

            //Texture ID
            vertices[offset + 8] = textureID;

            offset += _VertexSize;
        }
    }

    private int[] generateIndices(){
        int[] elements = new int[6 * maxBatchSize];
        for(int i = 0; i < maxBatchSize; i++){
            loadElementIndices(elements, i);
        }

        return elements;
    }

    private void loadElementIndices(int[] elements, int index){
        int offsetArrayIndex = 6 * index;
        int offset = 4 * index;

        elements[offsetArrayIndex] = offset + 3;
        elements[offsetArrayIndex + 1] = offset + 2;
        elements[offsetArrayIndex + 2] = offset;

        elements[offsetArrayIndex + 3] = offset + 0;
        elements[offsetArrayIndex + 4] = offset + 2;
        elements[offsetArrayIndex + 5] = offset + 1;
    }

    public boolean hasRoom(){
        return this.hasRoom;
    }
}
