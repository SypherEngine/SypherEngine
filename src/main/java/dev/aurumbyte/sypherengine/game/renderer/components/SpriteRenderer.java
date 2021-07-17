package dev.aurumbyte.sypherengine.game.renderer.components;

import dev.aurumbyte.sypherengine.game.components.Component;

public class SpriteRenderer extends Component {
    private boolean firstTime = false;

    @Override
    public void start(){
        System.out.println("Starting bois");
    }

    @Override
    public void update(float deltaTime) {
        if(!firstTime){
            System.out.println("Updating bois");
            firstTime = true;
        }
    }
}
