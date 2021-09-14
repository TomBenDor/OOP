package com.arkanoid.sprites;

import biuoop.DrawSurface;
import com.arkanoid.game.Game;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.utils.Counter;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class ScoreIndicator implements Sprite {
    private final Rectangle rectangle;
    private final Counter score;

    /**
     * Constructor.
     *
     * @param rectangle where to draw the indicator.
     * @param score     the score counter.
     */
    public ScoreIndicator(Rectangle rectangle, Counter score) {
        this.rectangle = rectangle;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.GRAY);
        surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawText((int) (rectangle.getUpperLeft().getX() + rectangle.getWidth() / 2),
                (int) (rectangle.getUpperLeft().getY() + rectangle.getHeight() * 9 / 10),
                String.format("Score: %d", score.getValue()), 20);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adding the indicator to the sprites list.
     *
     * @param game a game.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}
