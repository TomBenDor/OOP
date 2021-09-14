import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class BouncingBallAnimation {
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;

    /**
     * Opens a gui with a bouncing ball animation.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        BouncingBallAnimation drawing = new BouncingBallAnimation();

        Point start = null;
        double dx = 0, dy = 0;
        try {
            double x = Double.parseDouble(args[0]), y = Double.parseDouble(args[1]);
            start = new Point(x, y);
            dx = Double.parseDouble(args[2]);
            dy = Double.parseDouble(args[3]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        drawing.drawAnimation(start, dx, dy);
    }

    /**
     * Draws the animation.
     *
     * @param start the ball's center at the beginning.
     * @param dx    the ball's speed.
     * @param dy    the ball's speed.
     */
    public void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing Ball Animation", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(start.getX(), start.getY(), 10, Color.BLACK);
        ball.setFrame(new Frame(0, 0, WIDTH, HEIGHT));
        ball.setVelocity(dx, dy);

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(10);
        }
    }
}
