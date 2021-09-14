package com.arkanoid.game;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class EndScreen implements Animation {
    private final String message;

    /**
     * Constructor.
     *
     * @param s message to show.
     */
    public EndScreen(String s) {
        this.message = s;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        d.drawText(100, d.getHeight() / 2, message, 40);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
