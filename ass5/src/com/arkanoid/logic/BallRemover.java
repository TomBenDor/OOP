package com.arkanoid.logic;

import com.arkanoid.game.Game;
import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;
import com.arkanoid.utils.Counter;

/**
 * @author Tom Ben-Dor
 */
public class BallRemover implements HitListener {
    private final Game game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game           a Game.
     * @param remainingBalls Counter of the remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);

        remainingBalls.decrease(1);
    }
}
