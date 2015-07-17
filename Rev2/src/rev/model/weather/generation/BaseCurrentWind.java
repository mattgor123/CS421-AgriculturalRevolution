package rev.model.weather.generation;
/*
 * Base implementation of CurrentWind
 * 
 * @author laurencegoldinger
 */
import java.util.Random;

import rev.utility.Coordinate;
public class BaseCurrentWind implements CurrentWind {
	private int angle;
	private double strength;
	private static final int ANGLE_VAR = 2;
	private static final double STRENGTH_VAR = 0.5;
	private static final double MAX_STRENGTH = 10;

	public BaseCurrentWind() {
		Random r = new Random(System.nanoTime());
		angle = r.nextInt(360);
		strength = 0;
	}
	public Coordinate<Integer> getWind() {
		//Convert the polar coordinates 
		//The coordinates are kept in polar to avoid suddent massive shifts in wind direction that could be caused by x or y being close to 0 while the other is a large number and the first swaps sign
		return new Coordinate<Integer>(new Integer((int) (strength*Math.cos(Math.toRadians((double)angle)))),
										new Integer((int) (strength*Math.sin(Math.toRadians((double)angle)))));
	}

	public void update() {
		//Basic random number generation with certain limits attached
		Random r = new Random(System.currentTimeMillis());
		angle += r.nextInt(ANGLE_VAR*2 + 1) - ANGLE_VAR;
		//Keep angle within 360 degrees
		//First make sure its positive since negative numbers return negative mods in Java
		angle += 360;
		//Then mod it so we never have to worry about reaching past the integer limit
		angle %= 360;
		strength += r.nextFloat()*(STRENGTH_VAR*2) - STRENGTH_VAR;
		if(Math.abs(strength) > MAX_STRENGTH) {
			strength = MAX_STRENGTH * (strength/Math.abs(strength));
		}
		
	}

}