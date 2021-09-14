package com.arkanoid.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Tom Ben-Dor
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboardSensor;
    private final String key;
    private final Animation animation;
    private boolean running = true;
    private boolean isAlreadyPressed = true;

    /**
     * Constructor.
     *
     * @param keyboardSensor a sensor.
     * @param key            a key to listen to.
     * @param animation      animation to stop.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (keyboardSensor.isPressed(key)) {
            if (!isAlreadyPressed) {
                this.running = false;
            }
        } else {
            isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
}
