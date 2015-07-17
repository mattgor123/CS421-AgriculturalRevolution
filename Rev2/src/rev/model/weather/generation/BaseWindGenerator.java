package rev.model.weather.generation;
/*
 * Base implementation of WindGenerator
 * 
 * @author laurencegoldinger
 */

public class BaseWindGenerator implements WindGenerator{

	private BaseCurrentWind currentWind;
	public BaseWindGenerator() {
		currentWind = new BaseCurrentWind();
	}
	public CurrentWind getNextWind() {
		currentWind.update();
		return currentWind;
	}

}
