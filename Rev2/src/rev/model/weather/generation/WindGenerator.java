package rev.model.weather.generation;
/*
 * WindGenerator interface
 * 
 * @author laurencegoldinger
 */

public interface WindGenerator{
	/**
	 * 
	 * @return
	 * 				Gets the next wind object that has information about the current status of the wind
	 */
	public CurrentWind getNextWind();
}
