import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * @author Tom Ben-Dor
 */
public class AbstractArtDrawing {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    /**
     * Main function.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        AbstractArtDrawing drawing = new AbstractArtDrawing();
        drawing.drawRandomLines();
    }

    /**
     * Opens a GUI and draws random lines on it.
     */
    public void drawRandomLines() {
        GUI gui = new GUI("Abstract Art Drawing", WIDTH, HEIGHT);
        DrawSurface drawSurface = gui.getDrawSurface();

        drawSurface.setColor(Color.WHITE);
        drawSurface.fillRectangle(0, 0, WIDTH, HEIGHT);

        Line[] lines = new Line[10];
        Frame frame = new Frame(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < lines.length; i++) {
            Line line = frame.getRandomLine();

            drawSurface.setColor(Color.BLACK);
            drawSurface.drawLine(((int) line.start().getX()), ((int) line.start().getY()),
                    ((int) line.end().getX()), ((int) line.end().getY()));

            Point middle = line.middle();
            drawSurface.setColor(Color.BLUE);
            drawSurface.fillCircle((int) middle.getX(), ((int) middle.getY()), 3);

            for (int j = 0; j < i; j++) {
                Point intersection = line.intersectionWith(lines[j]);
                if (intersection == null) {
                    continue;
                }
                drawSurface.setColor(Color.RED);
                drawSurface.fillCircle((int) intersection.getX(), ((int) intersection.getY()), 3);
            }

            lines[i] = line;
        }

        gui.show(drawSurface);
    }
}