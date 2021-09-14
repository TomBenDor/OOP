import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public final class Config {
    public static final int FRAMES_PER_SECOND;
    public static final Color[] COLORS;
    public static final Color PADDLE_COLOR;
    public static final Rectangle PADDLE_RECT;
    public static final int WIN_WIDTH, WIN_HEIGHT;
    public static final Color WIN_BG_COLOR;
    public static final Color WALLS_COLOR;
    public static final int WALLS_SIZE;
    public static final int BRICKS_WIDTH, BRICKS_HEIGHT;
    public static final int BALL_SIZE;
    public static final int BALL_SPEED;
    public static final Color BALL_COLOR;


    static {
        FRAMES_PER_SECOND = 60;

        COLORS = new Color[] {
                Color.GREEN,
                Color.YELLOW,
                Color.ORANGE,
                Color.RED,
                Color.MAGENTA,
                Color.CYAN
        };

        WIN_WIDTH = 800;
        WIN_HEIGHT = 600;
        WIN_BG_COLOR = Color.WHITE;

        WALLS_COLOR = Color.DARK_GRAY;
        WALLS_SIZE = 10;

        PADDLE_COLOR = Color.DARK_GRAY;
        final int paddleWidth = 200, paddleHeight = 20;
        PADDLE_RECT = new Rectangle((WIN_WIDTH - paddleWidth) / ((double) 2), WIN_HEIGHT - paddleHeight - WALLS_SIZE,
                paddleWidth, paddleHeight);

        BRICKS_WIDTH = 50;
        BRICKS_HEIGHT = 25;

        BALL_SIZE = 10;
        BALL_SPEED = 5;
        BALL_COLOR = Color.DARK_GRAY;
    }

    /**
     * Preventing instance creation.
     */
    private Config() {
    }
}
