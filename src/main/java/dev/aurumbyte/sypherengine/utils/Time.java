package dev.aurumbyte.sypherengine.utils;

public class Time {
    private static Time instance;
    public static float startTime = System.nanoTime();

    public static float getTime(){
        return (float) ((System.nanoTime() - startTime) * 1E-9);
    }

}
