import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Frame</b>: representing a rectange within a window.
 */
public class Frame {
    private final int x1, x2, y1, y2;
    private Color color = Color.WHITE;

    /**
     * Constructor.
     *
     * @param x1 the top-left point's x coordinate.
     * @param y1 the top-left point's y coordinate.
     * @param x2 the bottom-right point's x coordinate.
     * @param y2 the bottom-right point's y coordinate.
     */
    public Frame(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Constructor.
     *
     * @param x1    the top-left point's x coordinate.
     * @param y1    the top-left point's y coordinate.
     * @param x2    the bottom-right point's x coordinate.
     * @param y2    the bottom-right point's y coordinate.
     * @param color the frame's background color.
     */
    public Frame(int x1, int y1, int x2, int y2, Color color) {
        this(x1, y1, x2, y2);

        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param topLeft     the top-left point.
     * @param bottomRight bottom-right point.
     */
    public Frame(Point topLeft, Point bottomRight) {
        this(((int) topLeft.getX()), ((int) topLeft.getY()), ((int) bottomRight.getX()), ((int) bottomRight.getY()));
    }

    /**
     * @return random point within the frame.
     */
    public Point getRandomPoint() {
        Random random = new Random();
        return new Point(random.nextInt(x2 - x1) + x1, random.nextInt(y2 - y1) + y1);
    }

    /**
     * @param margins a distance from the border.
     * @return random point within the frame (that is at least {@code margin} units from the border).
     */
    public Point getRandomPoint(int margins) {
        Frame frame = new Frame(x1 + margins, y1 + margins, x2 - margins, y2 - margins);
        return frame.getRandomPoint();
    }

    /**
     * @return random line within the frame.
     */
    public Line getRandomLine() {
        return new Line(getRandomPoint(), getRandomPoint());
    }

    /**
     * @return the top-left point's x coordinate.
     */
    public int getX1() {
        return x1;
    }

    /**
     * @return the top-left point's y coordinate.
     */
    public int getY1() {
        return y1;
    }

    /**
     * @return the bottom-right point's x coordinate.
     */
    public int getX2() {
        return x2;
    }

    /**
     * @return the bottom-right point's y coordinate.
     */
    public int getY2() {
        return y2;
    }

    /**
     * Draws the frame on the surface.
     *
     * @param surface a surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillRectangle(x1, y1, x2 - x1, y2 - y1);
    }
}
