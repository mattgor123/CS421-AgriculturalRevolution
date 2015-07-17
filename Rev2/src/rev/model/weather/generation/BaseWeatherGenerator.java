package rev.model.weather.generation;
/**
 * Base implementation of Weather Object
 * 
 * @author laurencegoldinger
 * 
 */
public class BaseWeatherGenerator implements WeatherGenerator{
	BaseCurrentWeather baseWeather;
	public BaseWeatherGenerator() {
		baseWeather = new BaseCurrentWeather();
	}
	public CurrentWeather getNextWeather() {
		baseWeather.update();
		return baseWeather;
	}

}
