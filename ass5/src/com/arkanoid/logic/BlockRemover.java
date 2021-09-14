package com.arkanoid.logic;

import com.arkanoid.game.Game;
import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;
import com.arkanoid.utils.Counter;

/**
 * @author Tom Ben-Dor
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private final Game game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            a Game.
     * @param remainingBlocks Counting the remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);

        remainingBlocks.decrease(1);
    }
}
