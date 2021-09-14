package com.arkanoid.logic;

import com.arkanoid.sprites.Ball;
import com.arkanoid.sprites.Block;
import com.arkanoid.utils.Counter;

/**
 * @author Tom Ben-Dor
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
