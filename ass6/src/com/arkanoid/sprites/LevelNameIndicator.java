package com.arkanoid.sprites;

import biuoop.DrawSurface;
import com.arkanoid.configuration.Config;
import com.arkanoid.game.GameLevel;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class LevelNameIndicator implements Sprite {
    private final String name;

    /**
     * Constructor.
     *
     * @param name the name.
     */
    public LevelNameIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawText(Config.WIN_WIDTH * 2 / 3, 18, "Level Name: " + name, 18);
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