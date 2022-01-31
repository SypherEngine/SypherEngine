package dev.aurumbyte.sypherengine.core;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Timer extends AnimationTimer {
    long pauseStart;
    long animationStart;
    DoubleProperty animationDuration = new SimpleDoubleProperty(0L);

    long lastFrameTimeNanos;

    boolean isPaused;
    boolean isActive;

    boolean pauseScheduled;
    boolean playScheduled;
    boolean restartScheduled;

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isActive() {
        return isActive;
    }

    public DoubleProperty animationDurationProperty() {
        return animationDuration;
    }

    public void pause() {
        if (!isPaused) {
            pauseScheduled = true;
        }
    }

    public void play() {
        if (isPaused) {
            playScheduled = true;
        }
    }

    @Override
    public void start() {
        super.start();
        isActive = true;
        restartScheduled = true;
    }

    @Override
    public void stop() {
        super.stop();
        pauseStart = 0;
        isPaused = false;
        isActive = false;
        pauseScheduled = false;
        playScheduled = false;
        animationDuration.set(0);
    }

    @Override
    public void handle(long now) {
        if (pauseScheduled) {
            pauseStart = now;
            isPaused = true;
            pauseScheduled = false;
        }

        if (playScheduled) {
            animationStart += (now - pauseStart);
            isPaused = false;
            playScheduled = false;
        }

        if (restartScheduled) {
            isPaused = false;
            animationStart = now;
            restartScheduled = false;
        }

        if (!isPaused) {
            long animationDuration = now - animationStart;
            this.animationDuration.set(animationDuration / 1e9);

            float deltaTime = (float) ((now - lastFrameTimeNanos) / 1e9);
            lastFrameTimeNanos = now;
            tick(deltaTime);
        }
    }

    public abstract void tick(float deltaTime);
}
