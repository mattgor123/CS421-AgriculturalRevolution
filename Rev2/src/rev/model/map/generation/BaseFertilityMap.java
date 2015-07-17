package rev.model.map.generation;

/**
 * A fertility map.
 * 
 * @author laurencegoldinger
 * 
 */
public class BaseFertilityMap implements FertilityMap{
	private static final int IDEAL_HEIGHT = 5; 
	private static final int MAX_HEIGHT = 20;
	private static final int IDEAL_DISTANCE = 1;
	private static final int HEIGHT_EFFECT = 90;
	private static final int DISTANCE_BASE = 9;
	private static final int DISTANCE_EFFECT = 1;
	private static final double BAD_EFFECT = (double)HEIGHT_EFFECT / (double)((IDEAL_HEIGHT*IDEAL_HEIGHT/4));
	private static final double MILD_EFFECT = BAD_EFFECT / 8;
	private static final int NO_WATER_DISTANCE = 1000;
	private int savedHeight;
	private int savedWidth;
	private Integer fertilityMapping[][];
	ElevationMap m;
	public BaseFertilityMap(ElevationMap elevationMap, SaltWaterMap saltWaterMap){
		m = elevationMap;
		savedHeight = elevationMap.getHeight();
		savedWidth = elevationMap.getWidth();
		fertilityMapping = new Integer[getWidth()][getHeight()];
		Integer distanceMap[][] = new Integer[getWidth()][getHeight()];
		//Find all fresh water
		for(int i = 0; i < getWidth(); i ++) {
			for( int j = 0; j < getHeight(); j++) {
				if( saltWaterMap.isLand(i, j)) {
					if ( 
							(i > 0 && saltWaterMap.isFreshWater(i-1, j)) ||
							(j > 0 && saltWaterMap.isFreshWater(i, j-1)) ||
							(j < getHeight() - 1 && saltWaterMap.isFreshWater(i, j+1)) ||
							(i < getHeight() - 1 && saltWaterMap.isFreshWater(i + 1, j))
							) {
						
						distanceMap[i][j] = 1;
					}					
				}
			}
		}
		boolean changed = true;
		//Infect to find distance from fresh water
		while(changed) {
			changed = false;
			for(int i = 0; i < getWidth(); i++) {
				for (int j = 0; j < getHeight(); j++) {
					if(distanceMap[i][j] != null) {
						if(j < getHeight() - 3 && (distanceMap[i][j+1] != null && distanceMap[i][j] + 1 < distanceMap[i][j+1] || distanceMap[i][j+1] == null) ) {
							distanceMap[i][j+1] = distanceMap[i][j]+1;
							changed = true;
						}
						if(j > 0 && (distanceMap[i][j-1] != null && distanceMap[i][j] + 1 < distanceMap[i][j-1] || distanceMap[i][j-1] == null)  ) {
							distanceMap[i][j-1] = distanceMap[i][j]+1;
							changed = true;
						}
						if(i < getWidth() - 2 && (distanceMap[i+1][j] != null && distanceMap[i][j] + 1 < distanceMap[i+1][j] || distanceMap[i+1][j] == null )) {
							distanceMap[i+1][j] = distanceMap[i][j]+1;
							changed = true;
						}
						if(i > 0 && (distanceMap[i-1][j] != null && distanceMap[i][j] + 1 < distanceMap[i-1][j] || distanceMap[i-1][j] == null) ) {
							distanceMap[i-1][j] = distanceMap[i][j]+1;
							changed = true;
						}
					}
				}
			}

		}
		//Finally calculate effects based on distance from fresh water and height
		for(int i = 0; i < getWidth(); i++) {
			for(int j = 0; j < getHeight(); j++) {
				if(distanceMap[i][j] != null) {
					fertilityMapping[i][j] = calcHeightEffect(elevationMap.getElevation(i, j));
					fertilityMapping[i][j] = calcDistanceEffect(distanceMap[i][j], fertilityMapping[i][j]);
				}
			}
		}
		//And account for tinier islands that only have salt
		for( int i = 0; i < getWidth(); i++) {
			for (int j = 0; j < getHeight(); j++) {
				if(saltWaterMap.isLand(i,j)) {
					if (fertilityMapping[i][j] == null) {
						fertilityMapping[i][j] = calcHeightEffect(elevationMap.getElevation(i,j));
						if(elevationMap.getElevation(i, j) <= MAX_HEIGHT && elevationMap.getElevation(i, j) >= IDEAL_HEIGHT) {
							fertilityMapping[i][j] = calcDistanceEffect(NO_WATER_DISTANCE, fertilityMapping[i][j]);
						}
					}
				}
			}
		}
	}
	private int calcHeightEffect(int height) {
		if(height == IDEAL_HEIGHT) {
			return HEIGHT_EFFECT;
		}
		else if(height > IDEAL_HEIGHT) {
			if(height > MAX_HEIGHT) {
				return 0;
			}
			else {
				return Math.max((int)(HEIGHT_EFFECT - (height - IDEAL_HEIGHT) * MILD_EFFECT),0);
			}
		}
		else {
			return 0;
		}
	}
	private int calcDistanceEffect(int distance, int currentEffect) {
		return currentEffect + Math.max(0,  DISTANCE_BASE + DISTANCE_EFFECT * (1 - Math.abs(IDEAL_DISTANCE - distance)));
	}
	@Override
	public int getWidth() {
		return savedWidth;
	}
	@Override
	public int getHeight() {
		return savedHeight;
	}
	@Override
	public int getFertility(int x, int y) {
		return fertilityMapping[x][y];
	}
	

}
