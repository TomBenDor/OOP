/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Point</b>: representing a 2-dimensional point.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructor.
     *
     * @param x - the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x coordinate.
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y coordinate.
     */
    public double getY() {
        return y;
    }

    /**
     * Calculate the distance between two points.
     *
     * @param other another point.
     * @return the distance.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    /**
     * Checks whether 2 points are identical.
     *
     * @param other another point.
     * @return whether the coordinates are the same.
     */
    public boolean equals(Point other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }

        return (Math.abs(other.y - y) < Math.pow(10, -5)) && (Math.abs(other.x - x) < Math.pow(10, -5));
    }
}
