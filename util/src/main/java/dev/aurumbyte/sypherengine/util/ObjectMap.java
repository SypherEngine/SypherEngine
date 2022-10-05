package dev.aurumbyte.sypherengine.util;

import dev.aurumbyte.sypherengine.util.math.Vector2;
import dev.aurumbyte.sypherengine.util.primitives.Box2D;
import dev.aurumbyte.sypherengine.util.primitives.Circle;
import dev.aurumbyte.sypherengine.util.primitives.Primitive;

import java.util.*;

public class ObjectMap<T> {
    public Map<Primitive, T> objects = new HashMap<>();

    public void addObject(Primitive object, T color){
        this.objects.put(object, color);
    }

    public void addObjects(T color, Primitive... objects){
        for(Primitive object : objects){
            this.objects.put(object, color);
        }
    }

    public List<Primitive> objectsWithPosition(Vector2 position){
        List<Primitive> objectList = new ArrayList<>();

        for(Primitive object : objects.keySet()){
            //Only returns rectangles and circle that are at a particular position

            if(object instanceof Box2D || object instanceof Circle){
                if(object.getPosition() == position) objectList.add(object);
            }
        }

        return objectList;
    }

    /*
    public ObjectList objectsWithColor(Color color){
        ObjectList objectList = new ObjectList();

        for(Primitive object : objects){
            if(object.getColor() == color){
                objectList.addObject(object);
            }
        }

        return objectList;
    }

     */

    public void clear(){
        this.objects.clear();
    }
}
