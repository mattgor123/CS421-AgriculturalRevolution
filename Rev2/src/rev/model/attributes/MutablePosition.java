package rev.model.attributes;

/**
 * A mutable position attribute.
 * 
 *
 * 
 */
public class MutablePosition extends Position {

    /**
     * Instantiates the position attribute.
     * 
     * @param x
     *            the initial x position value.
     * @param y
     *            the initial y position value.
     */
    public MutablePosition(int x, int y) {
        super(x, y);
    }

    /**
     * Sets the x position value.
     * 
     * @param x
     *            the x position value.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y position value.
     * 
     * @param y
     *            the y position value.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Translates the position.
     * 
     * @param dx
     *            the x position value change.
     * @param dy
     *            the y position value change.
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}