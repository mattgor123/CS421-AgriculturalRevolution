package rev.model.weather;

import rev.utility.Coordinate;

public class BaseWeatherManager implements WeatherManager {
    
    @Override
    public Integer getCurrentTemperature() {
        // TODO Auto-generated method stub
        return 72;
    }

    @Override
    public Coordinate<Integer> getWindDirection() {
        return new Coordinate<Integer>(1, 1);
    }
}