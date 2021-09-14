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
public class FinalFour implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return Config.NUMBER_OF_BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();

        for (int i = 0; i <= numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(new Random().nextInt(90) - 45, 10));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.decode("#1788d0"));
                d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(200 + i * 10, 400, 230 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(200, 400, 25);
                d.fillCircle(220, 420, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(240, 390, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(260, 420, 23);
                d.fillCircle(280, 400, 35);

                d.setColor(Color.WHITE);
                for (int i = 0; i < 10; i++) {
                    d.drawLine(500 + i * 10, 350, 530 + i * 10, 600);
                }

                d.setColor(new Color(100, 100, 100));
                d.fillCircle(500, 350, 25);
                d.fillCircle(520, 370, 27);
                d.setColor(new Color(150, 150, 150));
                d.fillCircle(540, 340, 29);
                d.setColor(new Color(200, 200, 200));
                d.fillCircle(560, 370, 23);
                d.fillCircle(580, 350, 35);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 15; j++) {
                Color color = Config.COLORS[i % Config.COLORS.length];
                Block block = new Block(new Rectangle(10 + j * 52, 100 + i * 30, 52, 30), color);

                blocks.add(block);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
