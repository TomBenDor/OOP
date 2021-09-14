package com.arkanoid.game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import com.arkanoid.collections.GameEnvironment;
import com.arkanoid.collections.SpriteCollection;
import com.arkanoid.configuration.Config;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.logic.BallRemover;
import com.arkanoid.logic.BlockRemover;
import com.arkanoid.logic.Collidable;
import com.arkanoid.logic.ScoreTrackingListener;
import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;
import com.arkanoid.sprites.Paddle;
import com.arkanoid.sprites.ScoreIndicator;
import com.arkanoid.sprites.Sprite;
import com.arkanoid.utils.Counter;
import com.arkanoid.utils.ScheduledTask;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * @author Tom Ben-Dor
 */
public class Game {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final GUI gui = new GUI("Game", Config.WIN_WIDTH, Config.WIN_HEIGHT);
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score = new Counter();
    private final List<ScheduledTask> scheduledTasks = new LinkedList<>();

    /**
     * Adds a collidable to the {@code environment}.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Removes a collidable from the {@code environment}.
     *
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * Adds a sprite to the {@code sprites}.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a sprite from the {@code sprites}.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initWalls();
        initBricks();
        initPaddle();
        initBalls();
        initDeathRegion();
        initScoreBoard();
    }

    /**
     * Initializes the score board.
     */
    private void initScoreBoard() {
        ScoreIndicator scoreIndicator =
                new ScoreIndicator(new Rectangle(0, 0, gui.getDrawSurface().getWidth(), 20), score);
        scoreIndicator.addToGame(this);
    }

    /**
     * Initializes the death region.
     */
    private void initDeathRegion() {
        int width = gui.getDrawSurface().getWidth(), height = gui.getDrawSurface().getHeight();
        Color color = Config.WALLS_COLOR;

        Block deathRegion = new Block(new Rectangle(0, height + Config.BALL_SIZE * 2, width, 0), color);
        deathRegion.addHitListener(new BallRemover(this, remainingBalls));
        addCollidable(deathRegion);
    }

    /**
     * Initializes the paddle.
     */
    private void initPaddle() {
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), Config.PADDLE_RECT, Config.PADDLE_COLOR);
        paddle.addToGame(this);
    }

    /**
     * Initializes the balls.
     */
    private void initBalls() {
        for (int i = 0; i < Config.NUMBER_OF_BALLS; i++) {
            Ball ball = new Ball(Config.PADDLE_RECT.getUpperLeft().getX() + Config.PADDLE_RECT.getWidth() / 2,
                    Config.PADDLE_RECT.getUpperLeft().getY() - Config.BALL_SIZE,
                    Config.BALL_SIZE,
                    Config.BALL_COLOR);
            ball.setGameEnvironment(environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(new Random().nextInt(90) - 45, Config.BALL_SPEED));
            ball.addToGame(this);
            remainingBalls.increase(1);
        }
    }

    /**
     * Initializes the bricks.
     */
    private void initBricks() {
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(score);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                int x = (gui.getDrawSurface().getWidth() - Config.BRICKS_WIDTH)
                        - (j * Config.BRICKS_WIDTH + Config.WALLS_SIZE);
                int y = i * Config.BRICKS_HEIGHT + Config.WALLS_SIZE + 50;
                Color color = Config.COLORS[i % Config.COLORS.length];
                Block brick = new Block(new Rectangle(x, y, Config.BRICKS_WIDTH, Config.BRICKS_HEIGHT), color);
                brick.addHitListener(blockRemover);
                brick.addHitListener(scoreTracker);

                remainingBlocks.increase(1);

                addTask(new Runnable() {
                    @Override
                    public void run() {
                        brick.addToGame(Game.this);
                    }
                }, new Random().nextInt(500));
            }
        }
    }

    /**
     * Initializes the walls.
     */
    private void initWalls() {
        int size = Config.WALLS_SIZE, width = gui.getDrawSurface().getWidth(), height =
                gui.getDrawSurface().getHeight();
        Color color = Config.WALLS_COLOR;

        Block[] walls = {
                new Block(new Rectangle(0, 20, size, height), color),
                new Block(new Rectangle(0, 20, width, size), color),
                new Block(new Rectangle(width - size, 20, size, height), color),
        };
        for (Block wall : walls) {
            wall.setBorder(false);
            wall.addToGame(this);
        }
    }

    /**
     * Adds a new task to the list.
     *
     * @param scheduledTask a task.
     */
    public void addTask(ScheduledTask scheduledTask) {
        scheduledTasks.add(scheduledTask);
    }

    /**
     * Adds a new task to the list.
     *
     * @param runnable the function to run.
     * @param delay    milliseconds before completion.
     */
    public void addTask(Runnable runnable, long delay) {
        addTask(new ScheduledTask(runnable, delay));
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int millisecondsPerFrame = 1000 / Config.FRAMES_PER_SECOND;
        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();

            d.setColor(Config.WIN_BG_COLOR);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

            sprites.drawAllOn(d);
            gui.show(d);
            sprites.notifyAllTimePassed();

            if (remainingBlocks.getValue() == 0) {
                score.increase(100);
                break;
            } else if (remainingBalls.getValue() == 0) {
                break;
            }

            for (ScheduledTask scheduledTask : new LinkedList<>(scheduledTasks)) {
                if (scheduledTask.ready()) {
                    scheduledTask.complete();
                    scheduledTasks.remove(scheduledTask);
                }
            }

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }

        gui.close();
    }
}
