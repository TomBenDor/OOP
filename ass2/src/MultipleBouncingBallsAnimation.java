import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Tom Ben-Dor
 */
public class MultipleBouncingBallsAnimation {
    private final String title;
    private final int width;
    private final int height;

    /**
     * Constructor.
     *
     * @param width  the window's width.
     * @param height the window's height.
     * @param title  the window's title.
     */
    public MultipleBouncingBallsAnimation(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    /**
     * Opens a gui with multiple bouncing balls animation.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation drawing =
                new MultipleBouncingBallsAnimation(200, 200, "Multiple Bouncing Ball Animation");

        Frame frame = new Frame(0, 0, drawing.width, drawing.height);

        Ball[] balls = new Ball[args.length];

        for (int i = 0; i < args.length; i++) {
            balls[i] = initBall(args[i], frame);
        }

        drawing.drawAnimation(balls);
    }

    /**
     * Getting the ball's radius and returning a ball object.
     *
     * @param radius the ball's size.
     * @param frame  the ball's frame.
     * @return a ball.
     */
    public static Ball initBall(String radius, Frame frame) {
        int radiusAsInt = 0;
        try {
            radiusAsInt = Integer.parseUnsignedInt(radius);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
            System.exit(-1);
        }

        Point center = frame.getRandomPoint(radiusAsInt);
        Random random = new Random();
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.CYAN, Color.PINK, Color.ORANGE};
        Ball ball = new Ball(center, radiusAsInt, colors[random.nextInt(colors.length)]);
        ball.setFrame(frame);
        double d = Math.max(1, (double) (50 - ball.getSize()) / 10);
        ball.setVelocity(d, d);
        return ball;
    }

    /**
     * Main loop that updates the window.
     *
     * @param balls the balls.
     */
    public void drawAnimation(Ball[] balls) {
        GUI gui = new GUI(title, width, height);
        Sleeper sleeper = new Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Ball ball : balls) {
                ball.getFrame().drawOn(d);
            }
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10);
        }
    }

}
