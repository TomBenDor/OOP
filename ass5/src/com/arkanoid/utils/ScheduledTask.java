package com.arkanoid.utils;

/**
 * @author Tom Ben-Dor
 */
public class ScheduledTask {
    private final Runnable runnable;
    private final long delay;
    private final long createdOn;

    /**
     * Constructor.
     *
     * @param runnable the function to run.
     * @param delay    milliseconds before completion.
     */
    public ScheduledTask(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.createdOn = System.currentTimeMillis();
    }

    /**
     * Completing the task by running the function.
     */
    public void complete() {
        runnable.run();
    }

    /**
     * @return if the task is scheduled to current time.
     */
    public boolean ready() {
        return System.currentTimeMillis() - createdOn >= delay;
    }
}
