package rev.model.weather;

import rev.utility.Coordinate;

/**
 * Manages game weather.
 * 
 *
 * 
 */
public interface WeatherManager {

    /**
     * Returns the current temperature, which affects agriculture.
     * 
     * @return the current temperature.
     */
    public Integer getCurrentTemperature();

    /**
     * Returns the current wind direction.
     * 
     * @return the current wind direction.
     */
    public Coordinate<Integer> getWindDirection();
}