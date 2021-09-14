package com.arkanoid.levels;

import com.arkanoid.geometry.Velocity;
import com.arkanoid.sprites.Block;
import com.arkanoid.sprites.Sprite;

import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public interface LevelInformation {
    /**
     * @return number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball Note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return list of speeds.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * @return a sprite with the background of the level.
     */
    Sprite getBackground();

    /**
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * @return number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     */
    int numberOfBlocksToRemove();
}
