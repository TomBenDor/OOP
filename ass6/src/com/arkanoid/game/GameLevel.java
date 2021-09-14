package com.arkanoid.game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import com.arkanoid.collections.GameEnvironment;
import com.arkanoid.collections.SpriteCollection;
import com.arkanoid.configuration.Config;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.levels.LevelInformation;
import com.arkanoid.logic.BallRemover;
import com.arkanoid.logic.BlockRemover;
import com.arkanoid.logic.Collidable;
import com.arkanoid.logic.ScoreTrackingListener;
import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;
import com.arkanoid.sprites.LevelNameIndicator;
import com.arkanoid.sprites.Paddle;
import com.arkanoid.sprites.ScoreIndicator;
import com.arkanoid.sprites.Sprite;
import com.arkanoid.utils.Counter;
import com.arkanoid.utils.ScheduledTask;

import java.awt.Color;
import java.util.List;

import static com.arkanoid.configuration.Config.WALLS_SIZE;
import static com.arkanoid.configuration.Config.WIN_HEIGHT;
import static com.arkanoid.configuration.Config.WIN_WIDTH;

/**
 * @author Tom Ben-Dor
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final KeyboardSensor keyboard;
    private final Counter remainingBlocks = new Counter();
    private final Counter remainingBalls = new Counter();
    private final Counter score;
    private final AnimationRunner runner;
    private final LevelInformation level;
    private boolean running = false;
    private Paddle paddle;
    private String status = null;

    /**
     * Constructor.
     *
     * @param level    level to run.
     * @param keyboard a sensor.
     * @param runner   an animation runner.
     * @param score    the score counter.
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.level = level;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
    }

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
        addSprite(level.getBackground());

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
        ScoreIndicator scoreIndicator = new ScoreIndicator(score);
        scoreIndicator.addToGame(this);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(level.levelName());
        levelNameIndicator.addToGame(this);
    }

    /**
     * Initializes the death region.
     */
    private void initDeathRegion() {
        Color color = Config.WALLS_COLOR;

        Block deathRegion = new Block(new Rectangle(0, WIN_HEIGHT + Config.BALL_SIZE * 2, WIN_WIDTH, 0), color);
        deathRegion.addHitListener(new BallRemover(this, remainingBalls));
        addCollidable(deathRegion);
    }

    /**
     * Initializes the paddle.
     */
    private void initPaddle() {
        int width = level.paddleWidth();
        Rectangle rectangle = new Rectangle((WIN_WIDTH - width) / ((double) 2),
                WIN_HEIGHT - Config.PADDLE_HEIGHT - WALLS_SIZE, width, Config.PADDLE_HEIGHT);
        this.paddle = new Paddle(keyboard, rectangle, Config.PADDLE_COLOR, level.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * Initializes the balls.
     */
    private void initBalls() {
        List<Velocity> velocities = level.initialBallVelocities();

        for (int i = 0; i < level.numberOfBalls(); i++) {
            Ball ball = new Ball(paddle.getCollisionRectangle().getUpperLeft().getX()
                    + paddle.getCollisionRectangle().getWidth() / 2,
                    paddle.getCollisionRectangle().getUpperLeft().getY() - Config.BALL_SIZE,
                    Config.BALL_SIZE,
                    Config.BALL_COLOR);
            ball.setGameEnvironment(environment);
            ball.setVelocity(velocities.get(i));
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

        for (Block block : level.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);

            remainingBlocks.increase(1);
            block.addToGame(this);
        }
    }

    /**
     * Initializes the walls.
     */
    private void initWalls() {
        int size = WALLS_SIZE, width = WIN_WIDTH, height = WIN_HEIGHT;
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
        runner.addTask(scheduledTask);
    }

    /**
     * Adds a new task to the list.
     *
     * @param runnable the function to run.
     * @param delay    milliseconds before completion.
     */
    public void addTask(Runnable runnable, long delay) {
        runner.addTask(runnable, delay);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        runner.run(new CountdownAnimation(2, 3, sprites, runner));
        this.running = true;
        runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        sprites.drawAllOn(d);
        sprites.notifyAllTimePassed();

        if (level.numberOfBlocksToRemove() <= level.blocks().size() - remainingBlocks.getValue()) {
            this.running = false;
            this.status = "win";
            this.score.increase(100);
        } else if (remainingBalls.getValue() == 0) {
            this.running = false;
            this.status = "lost";
        }

        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(keyboard, "space", new PauseScreen()));
        }
    }

    /**
     * @return "win" or "lost"
     */
    public String status() {
        return status;
    }

    @Override
    public boolean shouldStop() {
        return !running;
    }
}
