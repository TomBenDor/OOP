package com.arkanoid.logic;

import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;

/**
 * @author Tom Ben-Dor
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit a block.
     * @param hitter   a ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
