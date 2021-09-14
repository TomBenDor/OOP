import java.util.List;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Line</b>: representing a line (between 2 points).
 */
public class Line {
    private final Point start;
    private final Point end;

    /**
     * Constructor.
     *
     * @param start a point.
     * @param end   a point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor that receives the coordinates.
     *
     * @param x1 start's x coordinate.
     * @param y1 start's y coordinate.
     * @param x2 end's x coordinate.
     * @param y2 end's y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * @return the line's length.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * @return the start point.
     */
    public Point start() {
        return start;
    }

    /**
     * @return the end point.
     */
    public Point end() {
        return end;
    }

    /**
     * @param other another line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (isOnLine(other.start) || isOnLine(other.end)) {
            return true;
        }

        return intersectionWith(other) != null;
    }

    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     *
     * @param other another point.
     * @return the intersection point between this and other, if exists.
     */
    public Point intersectionWith(Line other) {
        if (equals(other)) {
            return null;
        }

        if ((start.equals(other.start) || start.equals(other.end)) && !(other.isOnLine(end))) {
            return start;
        }

        if ((end.equals(other.start) || end.equals(other.end)) && !(other.isOnLine(start))) {
            return end;
        }

        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * (this.start.getX()) + b1 * (this.start.getY());

        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * (other.start.getX()) + b2 * (other.start.getY());

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            return null;
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;

            Point intersection = new Point(x, y);

            if (this.isOnLine(intersection) && other.isOnLine(intersection)) {
                return intersection;
            } else {
                return null;
            }
        }
    }

    /**
     * @param point a point.
     * @return if the point is on the line.
     */
    public boolean isOnLine(Point point) {
        return Math.abs(point.distance(start) + point.distance(end) - length()) < Math.pow(10, -5);
    }

    /**
     * @param other another line (can be null).
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
            return false;
        }

        return (start.equals(other.start)) && (end.equals(other.end));
    }

    /**
     * @param rect a rectangle.
     * @return the closest intersection with {@code rect} to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }

        Point closest = new Point(Double.MAX_VALUE, Double.MAX_VALUE);
        for (Point point : intersectionPoints) {
            if (point.distance(start) < closest.distance(start)) {
                closest = point;
            }
        }

        return closest;
    }

    /**
     * @param l any number.
     * @return a new line that is {@code length() + l} long.
     */
    public Line extendBy(double l) {
        double newLength = l + length();
        double x = ((end.getX() * newLength) - l * start.getX()) / length();
        double y = ((end.getY() * newLength) - l * start.getY()) / length();

        return new Line(start, new Point(x, y));
    }
}
