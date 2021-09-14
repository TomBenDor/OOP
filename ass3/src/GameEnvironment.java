import java.util.LinkedList;
import java.util.List;

/**
 * @author Tom Ben-Dor
 */
public class GameEnvironment {
    private final List<Collidable> collidables = new LinkedList<>();

    /**
     * Add the given collidable to the environment.
     *
     * @param c a collidable.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that will occur.
     *
     * @param trajectory a line.
     * @return the information about the closest collision that will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double minLength = Double.MAX_VALUE;
        Point closestCollisionPoint = null;
        Collidable closestCollidable = null;

        for (Collidable collidable : collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            if (collisionPoint != null) {
                double distance = collisionPoint.distance(trajectory.start());
                if (distance < minLength) {
                    minLength = distance;
                    closestCollisionPoint = collisionPoint;
                    closestCollidable = collidable;
                }
            }
        }

        if (closestCollisionPoint != null) {
            return new CollisionInfo(closestCollisionPoint, closestCollidable);
        }
        return null;
    }
}
