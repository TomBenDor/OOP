package com.arkanoid.game;

import biuoop.KeyboardSensor;
import com.arkanoid.levels.LevelInformation;
import com.arkanoid.utils.Counter;

import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class GameFlow {
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter score = new Counter();

    /**
     * Constructor.
     *
     * @param ar an animation runner.
     * @param ks a sensor.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        runner = ar;
        keyboard = ks;
    }

    /**
     * Runs the selected levels.
     *
     * @param levels levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, keyboard, runner, score);

            level.initialize();
            level.run();

            if (level.status().equals("lost")) {
                runner.run(
                        new KeyPressStoppableAnimation(keyboard,
                                "space",
                                new EndScreen("Game Over. Your score is " + score.getValue())));
                return;
            }
        }
        runner.run(
                new KeyPressStoppableAnimation(keyboard,
                        "space",
                        new EndScreen("You Win! Your score is " + score.getValue())));
    }
}