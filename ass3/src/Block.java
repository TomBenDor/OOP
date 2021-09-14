import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;

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

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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

        return new Velocity(dx, dy);
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
        surface.setColor(Color.GRAY);
        surface.drawRectangle(((int) rectangle.getUpperLeft().getX()), ((int) rectangle.getUpperLeft().getY()),
                ((int) rectangle.getWidth()), ((int) rectangle.getHeight()));
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
}
