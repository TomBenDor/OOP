package com.arkanoid.game;

import biuoop.DrawSurface;
import com.arkanoid.collections.SpriteCollection;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection gameScreen;
    private final AnimationRunner runner;
    private final double numOfSeconds;
    private final int countFrom;
    private int secondsLeft;
    private boolean running;

    /**
     * Constructor.
     *
     * @param numOfSeconds number of seconds to show the animation.
     * @param countFrom    count from.
     * @param gameScreen   the sprites on the game.
     * @param runner       an animation runner.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, AnimationRunner runner) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.secondsLeft = countFrom;
        this.runner = runner;
        this.running = true;
        this.gameScreen = gameScreen;

        runner.addTask(new Runnable() {
            @Override
            public void run() {
                CountdownAnimation.this.countDown();
            }
        }, (long) ((numOfSeconds / countFrom) * 1000));
    }

    /**
     * Updating the number.
     */
    private void countDown() {
        if (secondsLeft == 1) {
            running = false;
        } else {
            secondsLeft--;
            runner.addTask(new Runnable() {
                @Override
                public void run() {
                    CountdownAnimation.this.countDown();
                }
            }, (long) ((numOfSeconds / countFrom) * 1000));
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(d.getWidth() / 2 - 20, d.getHeight() * 2 / 3, String.valueOf(secondsLeft), 80);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
