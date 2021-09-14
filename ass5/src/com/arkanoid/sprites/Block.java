package com.arkanoid.sprites;

import biuoop.DrawSurface;
import com.arkanoid.game.Game;
import com.arkanoid.geometry.Point;
import com.arkanoid.geometry.Rectangle;
import com.arkanoid.geometry.Velocity;
import com.arkanoid.logic.Collidable;
import com.arkanoid.logic.HitListener;
import com.arkanoid.logic.HitNotifier;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final List<HitListener> hitListeners = new LinkedList<>();
    private Rectangle rectangle;
    private Color color;
    private boolean border = true;

    /**
     * Constructor.
     *
     * @param rectangle the collision rectangle.
     * @param color     the block's color.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * Changing the default border option.
     *
     * @param b whether or not to draw a border.
     */
    public void setBorder(boolean b) {
        this.border = b;
    }

    /**
     * @return the color.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color.
     *
     * @param c a {@link Color}.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Sets the rectangle field.
     *
     * @param rect a {@link Rectangle}.
     */
    public void setCollisionRectangle(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * Notifying all listeners that a hit accrued.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        // Notify all listeners about a hit event:
        for (HitListener hl : new LinkedList<>(hitListeners)) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        if (Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX()) < Math.pow(10, -5)
                || (Math.abs(collisionPoint.getX() - rectangle.getUpperLeft().getX() - rectangle.getWidth()))
                < Math.pow(10, -5)) {
            dx *= -1;
        }
        if (Math.abs(collisionPoint.getY() - rectangle.getUpperLeft().getY()) < Math.pow(10, -5)
                || (Math.abs(collisionPoint.getY() - rectangle.getUpperLeft().getY() - rectangle.getHeight()))
                < Math.pow(10, -5)) {
            dy *= -1;
        }

        notifyHit(hitter);

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        if (border) {
            surface.setColor(Color.GRAY);
            surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                    ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        }
    }

    @Override
    public void timePassed() {

    }

    /**
     * Adds the block to the collections of collidables and sprites in the game.
     *
     * @param game a {@link Game}.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Removes the block to the collections of collidables and sprites in the game.
     *
     * @param game a {@link Game}.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
