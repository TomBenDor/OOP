package com.arkanoid.sprites;

import biuoop.KeyboardSensor;
import com.arkanoid.configuration.Config;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.logic.Collidable;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class Paddle extends Block implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private final int speed;

    /**
     * Constructor.
     *
     * @param keyboard  a keyboard from a {@code GUI}.
     * @param rectangle the rectangle.
     * @param color     the paddle's color.
     * @param speed     the paddle's speed.
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color, int speed) {
        super(rectangle, color);
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * Moving the paddle one step right.
     */
    public void moveRight() {
        setCollisionRectangle(new Rectangle(
                Math.min(getCollisionRectangle().getUpperLeft().getX() + speed,
                        Config.WIN_WIDTH - Config.WALLS_SIZE - getCollisionRectangle().getWidth()),
                getCollisionRectangle().getUpperLeft().getY(),
                getCollisionRectangle().getWidth(),
                getCollisionRectangle().getHeight()));
    }

    /**
     * Moving the paddle one step left.
     */
    public void moveLeft() {
        setCollisionRectangle(
                new Rectangle(Math.max(getCollisionRectangle().getUpperLeft().getX() - speed, Config.WALLS_SIZE),
                        getCollisionRectangle().getUpperLeft().getY(),
                        getCollisionRectangle().getWidth(),
                        getCollisionRectangle().getHeight()));
    }

    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double s = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        double x = collisionPoint.getX();

        if (x <= getCollisionRectangle().getUpperLeft().getX() + getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(300, s);
        }
        if (x <= getCollisionRectangle().getUpperLeft().getX() + 2 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(330, s);
        }
        if (x >= getCollisionRectangle().getUpperLeft().getX() + 4 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(60, s);
        }
        if (x >= getCollisionRectangle().getUpperLeft().getX() + 3 * getCollisionRectangle().getWidth() / 5) {
            return Velocity.fromAngleAndSpeed(30, s);
        }

        return Velocity.fromAngleAndSpeed(0, s);
    }
}
