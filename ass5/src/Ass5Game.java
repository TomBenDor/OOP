import com.arkanoid.game.Game;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Ass5Game</b> - running the game.
 */
public class Ass5Game {
    /**
     * Initializing the game and running.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
