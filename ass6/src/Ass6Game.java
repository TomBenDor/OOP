import biuoop.GUI;
import com.arkanoid.configuration.Config;
import com.arkanoid.game.AnimationRunner;
import com.arkanoid.game.GameFlow;
import com.arkanoid.levels.DirectHit;
import com.arkanoid.levels.FinalFour;
import com.arkanoid.levels.Green3;
import com.arkanoid.levels.LevelInformation;
import com.arkanoid.levels.WideEasy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 * <p>
 * Class <b>Ass6Game</b> - running the game.
 */
public class Ass6Game {
    /**
     * Initializing the game and running.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();

        for (String i : args) {
            int anInt = 0;
            try {
                anInt = Integer.parseInt(i);
            } catch (NumberFormatException e) {
                continue;
            }
            if (anInt < 1 || anInt > 4) {
                continue;
            }
            switch (anInt) {
                case 1:
                    levels.add(new DirectHit());
                    break;
                case 2:
                    levels.add(new WideEasy());
                    break;
                case 3:
                    levels.add(new Green3());
                    break;
                case 4:
                    levels.add(new FinalFour());
                    break;
                default:
            }
        }

        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }


        GUI gui = new GUI("Game", Config.WIN_WIDTH, Config.WIN_HEIGHT);
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, Config.FRAMES_PER_SECOND), gui.getKeyboardSensor());
        gameFlow.runLevels(levels);
        gui.close();
    }
}
