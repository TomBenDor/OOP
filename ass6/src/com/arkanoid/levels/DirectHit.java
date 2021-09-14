package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.sprites.Block;
import com.arkanoid.sprites.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class DirectHit implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return List.of(new Velocity(0, -3));
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(Color.BLACK);
                d.drawCircle(400, 215, 50);
                d.drawCircle(400, 215, 100);
                d.drawCircle(400, 215, 150);

                d.drawLine(220, 215, 370, 215);
                d.drawLine(430, 215, 580, 215);
                d.drawLine(400, 35, 400, 185);
                d.drawLine(400, 245, 400, 395);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        return List.of(new Block(new Rectangle(385, 200, 30, 30), Color.RED));
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
