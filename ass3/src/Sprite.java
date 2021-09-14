import biuoop.DrawSurface;

/**
 * @author Tom Ben-Dor
 */
public interface Sprite {
    /**
     * Draw the sprite to the screen.
     *
     * @param d a DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
