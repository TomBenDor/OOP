package com.arkanoid.geometry;

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
     * @param x the x coordinate.
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) {
            return false;
        }
        return Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
