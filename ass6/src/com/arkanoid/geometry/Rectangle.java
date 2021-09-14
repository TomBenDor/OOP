package com.arkanoid.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class Rectangle {
    private final Point upperLeft;
    private final double width;
    private final double height;

    /**
     * Constructor.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the rectangle's width.
     * @param height    the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor.
     *
     * @param x      the upper left point's x coordinate.
     * @param y      the upper left point's y coordinate.
     * @param width  the rectangle's width.
     * @param height the rectangle's height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * @param line a line.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Line[] sides = {
                new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY()),
                new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height),
                new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                        upperLeft.getY() + height),
                new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                        upperLeft.getY() + height),
        };

        for (int i = 0; i < 4; i++) {
            Point intersection = line.intersectionWith(sides[i]);
            if ((intersection != null) && !points.contains(intersection)) {
                points.add(intersection);
            }
        }
        return points;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
}
