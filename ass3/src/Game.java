import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class Game {
    private final SpriteCollection sprites = new SpriteCollection();
    private final GameEnvironment environment = new GameEnvironment();
    private final GUI gui = new GUI("Game", Config.WIN_WIDTH, Config.WIN_HEIGHT);

    /**
     * Adds a collidable to the {@code environment}.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the {@code sprites}.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        initWalls();
        initBricks();
        initBalls();
        initPaddle();
    }

    /**
     * Initializes the paddle.
     */
    private void initPaddle() {
        Paddle paddle = new Paddle(gui.getKeyboardSensor(), Config.PADDLE_RECT, Config.PADDLE_COLOR);
        paddle.addToGame(this);
    }

    /**
     * Initializes the balls.
     */
    private void initBalls() {
        Ball[] balls = {
                new Ball(400, 400, Config.BALL_SIZE, Config.BALL_COLOR),
                new Ball(450, 450, Config.BALL_SIZE, Config.BALL_COLOR),
        };

        for (Ball ball : balls) {
            ball.setGameEnvironment(environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(180, Config.BALL_SPEED));
            ball.addToGame(this);
        }
    }

    /**
     * Initializes the bricks.
     */
    private void initBricks() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 12 - i; j++) {
                int x = (gui.getDrawSurface().getWidth() - Config.BRICKS_WIDTH)
                        - (j * Config.BRICKS_WIDTH + Config.WALLS_SIZE);
                int y = i * Config.BRICKS_HEIGHT + Config.WALLS_SIZE + 50;
                Color color = Config.COLORS[i % Config.COLORS.length];
                Block brick = new Block(new Rectangle(x, y, Config.BRICKS_WIDTH, Config.BRICKS_HEIGHT), color);
                brick.addToGame(this);
            }
        }
    }

    /**
     * Initializes the walls.
     */
    private void initWalls() {
        int size = Config.WALLS_SIZE, width = gui.getDrawSurface().getWidth(), height =
                gui.getDrawSurface().getHeight();
        Color color = Config.WALLS_COLOR;

        Block[] walls = {
                new Block(new Rectangle(0, 0, size, height), color),
                new Block(new Rectangle(0, 0, width, size), color),
                new Block(new Rectangle(width - size, 0, size, height), color),
                new Block(new Rectangle(0, height - size, width, size), color)
        };
        for (Block wall : walls) {
            wall.addToGame(this);
        }
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        int millisecondsPerFrame = 1000 / Config.FRAMES_PER_SECOND;
        Sleeper sleeper = new Sleeper();

        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();

            d.setColor(Config.WIN_BG_COLOR);
            d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

            sprites.drawAllOn(d);
            gui.show(d);
            sprites.notifyAllTimePassed();

            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
