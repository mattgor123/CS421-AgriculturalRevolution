package rev.utility;

import java.util.Random;

/**
 * A statically-available random number generator.
 * 
 *
 * 
 */
public class RandomUtility {

	/**
	 * The random number variable.
	 * 
	 */
	private static Random random = new Random();

	/**
	 * Returns the next random integer in the range [0, max).
	 * 
	 * @param max
	 *            the upper bound, exclusive.
	 * @return the random integer.
	 */
	public static int randomInteger(int max) {
		return random.nextInt(max);
	}

	/**
	 * Returns the next random integer in the range [lower, upper).
	 * 
	 * @param lower
	 *            the lower bound, inclusive.
	 * @param upper
	 *            the upper bound, exclusive.
	 * @return the random integer.
	 */
	public static int randomInteger(int lower, int upper) {
		return random.nextInt(upper - lower) + lower;
	}

	/**
	 * Returns the next random double in the range [0, 1).
	 * 
	 * @return the random double.
	 */
	public static double nextDouble() {
		return random.nextDouble();
	}

	/**
	 * Returns the next random boolean value.
	 * 
	 * @return the random boolean value.
	 */
	public static boolean nextBoolean() {
		return random.nextBoolean();
	}

	/**
	 * Returns the next random boolean value, with the specified chance of being
	 * true.
	 * 
	 * @param chance
	 *            the chance of a true value [0, 100].
	 * @return the random boolean value.
	 */
	public static boolean nextBoolean(double chance) {
		if (random.nextDouble() < chance) {
			return true;
		}
		return false;
	}
}