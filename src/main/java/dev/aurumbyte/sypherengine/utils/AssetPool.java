package dev.aurumbyte.sypherengine.utils;

import dev.aurumbyte.sypherengine.graphics.Shader;
import dev.aurumbyte.sypherengine.graphics.Texture2D;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AssetPool {
    private static Map<String, Shader> shaders = new HashMap<>();
    private static Map<String, Texture2D> textures = new HashMap<>();

    public static Shader getShader(String resourceName){
        File file = new File(resourceName);
        if(shaders.containsKey(file.getAbsolutePath())) return shaders.get(file.getAbsolutePath());
        else {
            Shader shader = new Shader(resourceName);
            shader.compile();
            AssetPool.shaders.put(file.getAbsolutePath(), shader);
            return shader;
        }
    }

    public static Texture2D getTexture2D(String resourceName){
        File file = new File(resourceName);
        if(textures.containsKey(file.getAbsolutePath())) return textures.get(file.getAbsolutePath());
        else {
            Texture2D texture = new Texture2D(resourceName);
            AssetPool.textures.put(file.getAbsolutePath(), texture);
            return texture;
        }
    }
}
