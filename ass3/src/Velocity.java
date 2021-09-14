/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Velocity</b>: each {@code Ball} has velocity when moving on the screen.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructor.
     *
     * @param dx the differential of the x coordinate.
     * @param dy the differential of the y coordinate.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Takes an angle and a speed and returns velocity.
     *
     * @param angle an angle (in degrees).
     * @param speed any speed.
     * @return velocity with direction and speed as received.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = -Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return dx.
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return dy.
     */
    public double getDy() {
        return dy;
    }

    /**
     * Take a point with position (<i>x</i>,&nbsp;<i>y</i>)
     * and return a new point with position (<i>x+dx</i>,&nbsp;<i>y+dy</i>).
     *
     * @param point a point.
     * @return a point with its new position.
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + dx, point.getY() + dy);
    }
}