package rev.model.weather.generation;

import java.util.Random;
import java.util.List;
/**
 * Base implementation of CurrentWeather Object
 * 
 * @author laurencegoldinger
 * 
 */
public class BaseCurrentWeather implements CurrentWeather {
	private static final int VOLATILITY = 30;
	private static final int EXPECTED_TEMPERATURE = 60;
	private static final int EXPECTED_PRESSURE = 50;
	private static final int TEMPERATURE_VARIETY = 20;
	private static final int PRESSURE_VARIETY = 10;
	private static final int CHART_SIZE = 100;
	private int progress;
	private int temperature[][] = new int[CHART_SIZE][CHART_SIZE];
	private int airPressure[][] = new int[CHART_SIZE][CHART_SIZE];
	
	public BaseCurrentWeather() {
		Random r = new Random(System.nanoTime());
		for(int i = 0; i < CHART_SIZE; i++) {
			for(int j = 0; j < CHART_SIZE; j++) {
				temperature[i][j] = getTemp(r);
				airPressure[i][j] = getPressure(r);
			}
			this.updateWeather();
		}
		
	}
	private int getPressure(Random r) {
		return r.nextInt(PRESSURE_VARIETY) - PRESSURE_VARIETY/2 + EXPECTED_PRESSURE;
	}
	private int getTemp(Random r) {
		return r.nextInt(TEMPERATURE_VARIETY) - TEMPERATURE_VARIETY/2 + EXPECTED_TEMPERATURE;
	}
	public void update() {
		Random r = new Random(System.currentTimeMillis());
		progress += r.nextInt(3);
		if(progress >= VOLATILITY) {
			progress %= 30;
			updateWeather();
		}
		
		
	}

	private void updateWeather() {
		Random r = new Random(System.currentTimeMillis());
		int newTemperature[][] = new int[CHART_SIZE][CHART_SIZE];
		int newPressure[][] = new int[CHART_SIZE][CHART_SIZE];
		int westTemp = getTemp(r);
		int westPressure = getPressure(r);
		int eastTemp = getTemp(r);
		int eastPressure = getPressure(r);
		int northTemp = getTemp(r);
		int northPressure = getPressure(r);
		int southTemp = getTemp(r);
		int southPressure = getPressure(r);
		//Get "weighted" averages based on the difference in air pressure
		//Keep the new values in a separate chart for dual purpose first make sure we don't get a lopsided chart 
		//Second by getting all the values separately it prevents the chart from becoming too blended because something must overshoot compared to the final values
		for(int i = 0; i < CHART_SIZE; i++) {
			for(int j = 0; j < CHART_SIZE; j++) {
				int tempMod = 0;
				int presMod = 0;
				if(i == 0) {
					tempMod += (westTemp - temperature[i][j]) * (Math.abs(westPressure - airPressure[i][j]));
					presMod += (westPressure - airPressure[i][j])/2;
					tempMod += (temperature[i+1][j] - temperature[i][j]) * (Math.abs(airPressure[i+1][j] - airPressure[i][j]));
					presMod += (airPressure[i+1][j] - temperature[i][j])/2;
				}
				else if(i == CHART_SIZE) {
					tempMod += (temperature[i-1][j] - temperature[i][j]) * (Math.abs(airPressure[i-1][j] - airPressure[i][j]));
					presMod += (airPressure[i-1][j] - temperature[i][j])/2;
					tempMod += (eastTemp - temperature[i][j]) * (Math.abs(eastPressure - airPressure[i][j]));
					presMod += (eastPressure - airPressure[i][j])/2;
				}
				else {
					tempMod += (temperature[i-1][j] - temperature[i][j]) * (Math.abs(airPressure[i-1][j] - airPressure[i][j]));
					presMod += (airPressure[i-1][j] - temperature[i][j])/2;
					tempMod += (temperature[i+1][j] - temperature[i][j]) * (Math.abs(airPressure[i+1][j] - airPressure[i][j]));
					presMod += (airPressure[i+1][j] - temperature[i][j])/2;
				}
				if(j == 0) {
					tempMod += (southTemp - temperature[i][j]) * (Math.abs(southPressure - airPressure[i][j]));
					presMod += (southPressure - airPressure[i][j])/2;
					tempMod += (temperature[i][j+1] - temperature[i][j]) * (Math.abs(airPressure[i][j+1] - airPressure[i][j]));
					presMod += (airPressure[i][j+1] - temperature[i][j])/2;
				}
				else if(j == CHART_SIZE) {
					tempMod += (temperature[i][j-1] - temperature[i][j]) * (Math.abs(airPressure[i][j-1] - airPressure[i][j]));
					presMod += (airPressure[i][j-1] - temperature[i][j])/2;
					tempMod += (northTemp - temperature[i][j]) * (Math.abs(northPressure - airPressure[i][j]));
					presMod += (northPressure - airPressure[i][j])/2;
				}
				else {
					tempMod += (temperature[i][j-1] - temperature[i][j]) * (Math.abs(airPressure[i][j-1] - airPressure[i][j]));
					presMod += (airPressure[i][j-1] - temperature[i][j])/2;
					tempMod += (temperature[i][j+1] - temperature[i][j]) * (Math.abs(airPressure[i][j+1] - airPressure[i][j]));
					presMod += (airPressure[i][j+1] - temperature[i][j])/2;
				}
				newTemperature[i][j] = temperature[i][j] + tempMod;
				newPressure[i][j] = airPressure[i][j] + presMod;
			}
		}
		temperature = newTemperature;
		airPressure = newPressure;
		cosmicRay();
	}
	@Override
	public Integer getTemperature() {
		double avg = 0;
		for(int[] arr : temperature) {
			for(int i : arr) {
				avg += (double)i/(double)(CHART_SIZE * CHART_SIZE);
			}
		}
		return (int) avg;
	}
	
	public void cosmicRay() {
		//External source to prevent stagnating weather, increases temperature and pressure at a random point on the map
		Random r = new Random(System.nanoTime());
		int x = r.nextInt(CHART_SIZE);
		int y = r.nextInt(CHART_SIZE);
		temperature[x][y] += r.nextInt(1+TEMPERATURE_VARIETY/2);
		airPressure[x][y] += r.nextInt(1+PRESSURE_VARIETY/2);
	}


	@Override
	public List<Object> getWeatherEvents() {
		// Look for certain pressure + temperature paterns
		//if a 5x5 block has abnormally high temp and pressure, heat wave, low pressure tropical storm
		//low temp and pressure rain, very low pressure thunder, high pressure chill
		return null;
	}

}
