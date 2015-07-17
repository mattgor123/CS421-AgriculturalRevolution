package rev.utility;

/**
 * A two-dimensional coordinate.
 * 
 *
 * 
 * @param <T>
 *            the coordinate component data type.
 */
public class Coordinate<T> {

	/**
	 * The x component value.
	 * 
	 */
	private T x;

	/**
	 * The y component value.
	 * 
	 */
	private T y;

	/**
	 * Instantiates a new coordinate.
	 * 
	 * @param x
	 *            the initial x component value.
	 * @param y
	 *            the initial y component value.
	 */
	public Coordinate(T x, T y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Sets the x component value.
	 * 
	 * @param x
	 *            the x component value.
	 */
	public void setX(T x) {
		this.x = x;
	}

	/**
	 * Sets the y component value.
	 * 
	 * @param y
	 *            the y component value.
	 */
	public void setY(T y) {
		this.y = y;
	}

	/**
	 * Returns the x component value.
	 * 
	 * @return the x component value.
	 */
	public T getX() {
		return this.x;
	}

	/**
	 * Returns the y component value.
	 * 
	 * @return the y component value.
	 */
	public T getY() {
		return this.y;
	}
}