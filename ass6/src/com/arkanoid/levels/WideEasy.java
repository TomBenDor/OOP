package com.arkanoid.levels;

import biuoop.DrawSurface;
import com.arkanoid.configuration.Config;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.sprites.Block;
import com.arkanoid.sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Tom Ben-Dor
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        for (int i = 0; i <= numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(new Random().nextInt(90) - 45, Config.BALL_SPEED));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

                d.setColor(new Color(239, 231, 176));
                d.fillCircle(150, 150, 60);
                for (int x = 10; x <= 780; x += 10) {
                    d.drawLine(150, 150, x, 250);
                }
                d.setColor(new Color(236, 215, 73));
                d.fillCircle(150, 150, 50);
                d.setColor(new Color(255, 255, 24));
                d.fillCircle(150, 150, 40);

            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Color color = Config.COLORS[i / 2 % Config.COLORS.length];
            Block block = new Block(new Rectangle(10 + i * 65, 250, 65, 30), color);

            blocks.add(block);
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
