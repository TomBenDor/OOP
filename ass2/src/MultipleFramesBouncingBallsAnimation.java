import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Creates the two frames and initializes the balls.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation drawing = new MultipleBouncingBallsAnimation(700, 700,
                "Multiple Frames Bouncing Ball Animation");

        Frame frame1 = new Frame(50, 50, 500, 500, Color.GRAY);
        Frame frame2 = new Frame(450, 450, 600, 600, Color.YELLOW);

        Ball[] balls = new Ball[args.length];
        for (int i = 0; i < args.length; i++) {
            balls[i] = MultipleBouncingBallsAnimation.initBall(args[i], (i < args.length / 2) ? frame1 : frame2);
        }

        drawing.drawAnimation(balls);
    }
}
