import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Ball</b>: a ball within a frame.
 */
public class Ball {
    private final int size;
    private final Color color;
    private Frame frame = null;
    private Point point;
    private Velocity velocity = new Velocity(0, 0);

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
     * @return the ball's frame.
     */
    public Frame getFrame() {
        return frame;
    }

    /**
     * Updates the ball's frame.
     *
     * @param f a frame.
     */
    public void setFrame(Frame f) {
        this.frame = f;
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
     * Draw the ball on the given DrawSurface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), size);
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
     * Moving the point one step according to the velocity.
     * If the ball hits the frame's border, changing the velocity.
     */
    public void moveOneStep() {
        this.point = velocity.applyToPoint(point);

        if (frame == null) {
            return;
        }

        if (point.getX() + size >= frame.getX2()) {
            setVelocity(-Math.abs(velocity.getDx()), velocity.getDy());
        }
        if (point.getX() - size <= frame.getX1()) {
            setVelocity(Math.abs(velocity.getDx()), velocity.getDy());
        }
        if (point.getY() + size >= frame.getY2()) {
            setVelocity(velocity.getDx(), -Math.abs(velocity.getDy()));
        }
        if (point.getY() - size <= frame.getY1()) {
            setVelocity(velocity.getDx(), Math.abs(velocity.getDy()));
        }
    }
}