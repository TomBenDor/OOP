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
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(50, 200, 50));
                d.fillRectangle(0, 20, d.getWidth(), d.getHeight());

                d.setColor(Color.BLACK);
                d.fillRectangle(110, 200, 10, 200);

                d.setColor(Color.RED);
                d.fillCircle(115, 200, 12);

                d.setColor(Color.ORANGE);
                d.fillCircle(115, 200, 8);

                d.setColor(Color.WHITE);
                d.fillCircle(115, 200, 3);

                d.setColor(Color.BLACK);
                d.fillRectangle(100, 400, 30, 200);

                d.setColor(Color.BLACK);
                d.fillRectangle(65, 450, 100, 200);

                d.setColor(Color.WHITE);

                for (int x = 0; x < 5; ++x) {
                    for (int y = 0; y < 5; ++y) {
                        d.fillRectangle(75 + x * 18, 460 + y * 32, 10, 25);
                    }
                }
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                int x = (Config.WIN_WIDTH - Config.BRICKS_WIDTH) - (j * Config.BRICKS_WIDTH + Config.WALLS_SIZE);
                int y = i * Config.BRICKS_HEIGHT + Config.WALLS_SIZE + 50;
                Color color = Config.COLORS[i % Config.COLORS.length];
                Block block = new Block(new Rectangle(x, y, Config.BRICKS_WIDTH, Config.BRICKS_HEIGHT), color);

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
