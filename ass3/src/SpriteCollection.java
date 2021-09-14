import biuoop.DrawSurface;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class SpriteCollection {
    private final List<Sprite> sprites = new LinkedList<>();

    /**
     * Adds a sprite to the list of sprites.
     *
     * @param s a sprite.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Calls drawOn(d) on all sprites.
     *
     * @param d drawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
