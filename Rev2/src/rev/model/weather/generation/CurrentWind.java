package rev.model.weather.generation;
/*
 * CurrentWind interface
 * 
 * @author laurencegoldinger
 */

import rev.utility.Coordinate;

public interface CurrentWind {
	/**
	 * 
	 * @return
	 * 				The Cartesian direction and intensity of the wind 
	 */
	public Coordinate<Integer> getWind();
}
