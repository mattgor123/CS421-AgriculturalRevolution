package rev.model.weather.generation;
/*
 * WeatherGenerator interface
 * 
 * @author laurencegoldinger
 */

public interface WeatherGenerator {
    /**
     * 
     * @return
     * 				The next object that holds the status of the weather
     */
    public CurrentWeather getNextWeather();
}