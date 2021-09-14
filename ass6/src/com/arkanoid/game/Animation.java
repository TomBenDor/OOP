package com.arkanoid.game;

import biuoop.DrawSurface;

/**
 * @author Tom Ben-Dor
 */
public interface Animation {
    /**
     * Draws the animation.
     *
     * @param d a draw surface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return whether the animation is over.
     */
    boolean shouldStop();
}
