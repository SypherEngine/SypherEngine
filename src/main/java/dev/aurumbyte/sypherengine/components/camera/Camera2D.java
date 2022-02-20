package dev.aurumbyte.sypherengine.components.camera;

import dev.aurumbyte.sypherengine.components.Entity;
import dev.aurumbyte.sypherengine.math.Vector2;
import javafx.scene.ParallelCamera;
import javafx.scene.transform.Translate;

public class Camera2D {
    //still wip

    Vector2 cameraPosition;
    Entity parent;
    ParallelCamera camera = new ParallelCamera();

    public Camera2D(Entity parent){
        this.parent = parent;
        this.cameraPosition = parent.getDrawPosition();

        camera.setNearClip(0.1);
        camera.setFarClip(2000);

        camera.setTranslateX(parent.getDrawPosition().xPos);
        camera.setTranslateY(parent.getDrawPosition().yPos);
    }

    public Camera2D(){
        this.cameraPosition = new Vector2();

        camera.setNearClip(0.1);
        camera.setFarClip(2000);

        camera.setTranslateX(0);
        camera.setTranslateY(0);
    }

    public void attachToEntity(Entity entity){
        this.parent = entity;
    }

    public void setCameraPos(int x, int y){
        Translate translate = new Translate();
        translate.setX(x);
        translate.setY(y);
        translate.setZ(0);

        camera.getTransforms().add(translate);
    }

    public void setCameraPos(Vector2 cameraPos){

    }

    public ParallelCamera getCamera() {
        return camera;
    }

    public void update(){
        Translate translate = new Translate();
        translate.setX(cameraPosition.xPos);
        translate.setY(cameraPosition.yPos);
        translate.setZ(0);

        camera.getTransforms().add(translate);
    }
}
