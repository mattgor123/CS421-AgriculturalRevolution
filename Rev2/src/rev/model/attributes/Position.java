package rev.model.attributes;

/**
 * A two-dimensional position attribute.
 * 
 *
 * 
 */
public class Position {

    /**
     * The x position value.
     * 
     */
    int x;

    /**
     * The y position value.
     * 
     */
    int y;

    /**
     * Creates a new position attribute.
     * 
     * @param x
     *            the x position value.
     * @param y
     *            the y position value.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x value of the position.
     * 
     * @return the x value.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Returns the y value of the position.
     * 
     * @return the y value.
     */
    public int getY() {
        return this.y;
    }
}