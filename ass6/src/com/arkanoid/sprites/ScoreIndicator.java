package com.arkanoid.sprites;

import biuoop.DrawSurface;
import com.arkanoid.configuration.Config;
import com.arkanoid.game.GameLevel;
import com.arkanoid.utils.Counter;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class ScoreIndicator implements Sprite {
    private final Counter score;

    /**
     * Constructor.
     *
     * @param score the score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(Config.WIN_WIDTH / 4, 18, String.format("Score: %d", score.getValue()), 18);
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adding the indicator to the sprites list.
     *
     * @param game a game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
