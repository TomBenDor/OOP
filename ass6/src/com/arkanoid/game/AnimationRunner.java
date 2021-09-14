package com.arkanoid.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import com.arkanoid.utils.ScheduledTask;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class AnimationRunner {
    private final Sleeper sleeper = new Sleeper();
    private final GUI gui;
    private final int framesPerSecond;
    private final List<ScheduledTask> scheduledTasks = new LinkedList<>();

    /**
     * Constructor.
     *
     * @param gui             a gui.
     * @param framesPerSecond number of frames to draw per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Runs the animation loop.
     *
     * @param animation an animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);

            for (ScheduledTask scheduledTask : new LinkedList<>(scheduledTasks)) {
                if (scheduledTask.ready()) {
                    scheduledTask.complete();
                    scheduledTasks.remove(scheduledTask);
                }
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }


    /**
     * Adds a new task to the list.
     *
     * @param scheduledTask a task.
     */
    public void addTask(ScheduledTask scheduledTask) {
        scheduledTasks.add(scheduledTask);
    }

    /**
     * Adds a new task to the list.
     *
     * @param runnable the function to run.
     * @param delay    milliseconds before completion.
     */
    public void addTask(Runnable runnable, long delay) {
        addTask(new ScheduledTask(runnable, delay));
    }
}

