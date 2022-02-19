package dev.aurumbyte.sypherengine.components.camera;

import dev.aurumbyte.sypherengine.components.Entity;
import javafx.scene.ParallelCamera;

public class Camera2D {
    //still wip

    ParallelCamera camera = new ParallelCamera();

    public Camera2D(Entity parent){
        camera.setNearClip(0.1);
        camera.setFarClip(2000);
    }

    public ParallelCamera getCamera() {
        return camera;
    }

    public void update(){

    }
}
