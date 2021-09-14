package com.arkanoid.game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class PauseScreen implements Animation {
    private final boolean stop = false;

    /**
     * Constructor.
     *
     * @param d a draw surface.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * @return if the animation should be over.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

