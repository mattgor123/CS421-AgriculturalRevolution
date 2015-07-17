package rev.model.weather.generation;

import java.util.List;

/**
 * The current weather status.
 * 
 *
 * 
 */
public interface CurrentWeather {

	/**
	 * 
	 * @return
	 * 				Gets the average temperature over the island
	 */
    public Integer getTemperature();

    /**
     * 
     * @return
     * 				Gets any special weather events that are occurring due to the atmospheric conditions
     */
    public List<Object> getWeatherEvents();
}