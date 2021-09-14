package com.arkanoid.sprites;

import biuoop.DrawSurface;
import com.arkanoid.collections.GameEnvironment;
import com.arkanoid.game.GameLevel;
import com.arkanoid.geometry.Line;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.logic.CollisionInfo;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Ball</b>.
 */
public class Ball implements Sprite {
    private final int size;
    private final Color color;
    private Point point;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;

    /**
     * Constructor.
     *
     * @param point the center of the ball.
     * @param r     the size of the ball.
     * @param color the color of the ball.
     */
    public Ball(Point point, int r, Color color) {
        this.point = point;
        this.size = r;
        this.color = color;
    }

    /**
     * Constructor that works just like {@link Ball#Ball(Point, int, Color)}
     * but receives the coordinates of the center.
     *
     * @param x     the center's x coordinate.
     * @param y     the center's y coordinate.
     * @param r     the size of the ball.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * @return the ball center's x coordinate.
     */
    public int getX() {
        return ((int) point.getX());
    }

    /**
     * @return the ball center's y coordinate.
     */
    public int getY() {
        return ((int) point.getY());
    }

    /**
     * @return the ball's radius.
     */
    public int getSize() {
        return size;
    }

    /**
     * @return the ball's color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return velocity;
    }

    /**
     * Updates the ball's velocity.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Updates the ball's velocity.
     *
     * @param dx the new velocity's dx.
     * @param dy the new velocity's dy.
     * @see Velocity#Velocity(double, double)
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Sets the game environment.
     *
     * @param environment GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moving the point one step according to the velocity.
     * If the ball hits a collidable, changing the velocity.
     */
    public void moveOneStep() {
        Line trajectory = trajectory();
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory.extendBy(size));

        if (collisionInfo == null) {
            this.point = trajectory.end();
        } else {

            this.velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);

            while (true) {
                CollisionInfo futureCollisionInfo = gameEnvironment.getClosestCollision(trajectory().extendBy(size));

                if ((futureCollisionInfo == null)
                        || (futureCollisionInfo.collisionObject() != collisionInfo.collisionObject())) {
                    break;
                }

                this.point = trajectory().end();

            }
        }
    }

    /**
     * @return the ball's trajectory.
     */
    private Line trajectory() {
        return new Line(point, velocity.applyToPoint(point));
    }

    /**
     * Adds the ball to the sprites collection in the game.Adds the ball to the sprites collection in the game.
     *
     * @param game a Game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Removes the ball to the sprites collection in the game.Adds the ball to the sprites collection in the game.
     *
     * @param game a Game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}