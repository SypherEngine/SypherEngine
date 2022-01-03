package dev.aurumbyte.sypherengine.physics;

import dev.aurumbyte.sypherengine.gameUtils.GameManager;

import java.util.ArrayList;

public class Physics<T extends GameManager<T>> {
    private final ArrayList<AABB<T>> aabbList = new ArrayList<>();
    public static boolean notCollided = true;


    public void addAABBComponent(AABB<T> aabb){
        aabbList.add(aabb);
    }

    public void update(){
        for(int i = 0; i < aabbList.size(); i++){
            for(int j = i + 1; j < aabbList.size(); j++) {
                AABB<T> a = aabbList.get(i);
                AABB<T> b = aabbList.get(j);

                notCollided = !AABBvsAABB(a, b);

                if (notCollided) {
                    b.getParent().event(a.getParent());
                    a.getParent().event(b.getParent());
                }
            }
        }

        aabbList.clear();
    }

    public boolean AABBvsAABB(AABB<T> a, AABB<T> b){
        if(a.getMax().getX() < b.getMin().getX() || a.getMin().getX() > b.getMax().getX()) return false;
        if(a.getMax().getY() < b.getMin().getY() || a.getMin().getY() > b.getMax().getY()) return false;


        return true;
    }
}
