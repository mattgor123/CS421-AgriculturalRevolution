package rev.model.map.generation;

import rev.utility.RandomUtility;

/**
 * Generates elevation maps.
 * 
 *
 * 
 */
public class ElevationMapGenerator {

    /**
     * Creates a random elevation map.
     * 
     * @param width
     *            the width of the map.
     * @param height
     *            the height of the map.
     * @return the random elevation map.
     */
	   public static int[][] createRandomElevationMap(int width, int height) {

	        int[][] map = new int[width][height];

	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                map[i][j] = 0;
	            }
	        }

	        int midWidth = width / 2;
	        int midHeight = height / 2;
	        int quarterWidth = midWidth / 2;
	        int quarterHeight = midWidth / 2;

	        for (int i = 0; i < 60; i++) {

	            int dx = RandomUtility.randomInteger(-quarterWidth, quarterWidth);
	            int dy = RandomUtility.randomInteger(-quarterHeight, quarterHeight);
	            raisePoint(map, midWidth + dx, midHeight + dy, 0.95);
	        }

	        return map;
	    }

	    /**
	     * Commences hill generation about the specified point.
	     * 
	     * @param map
	     *            the map.
	     * @param x
	     *            the x index of the point.
	     * @param y
	     *            the y index of the point.
	     * @param prob
	     *            the probability of continuing generation.
	     */
	    private static void raisePoint(int[][] map, int x, int y, double prob) {
	        int maxX = map.length-1;
	        int maxY = map[0].length-1;
	        map[x][y] += 1;
	        map[x - 1][y]++;
	        map[x - 1][y - 1]++;
	        map[x - 1][y + 1]++;
	        map[x + 1][y]++;
	        map[x + 1][y - 1]++;
	        map[x + 1][y + 1]++;     		
	        if (prob > 0.2 && RandomUtility.nextBoolean(prob)) {
	        	int newx = x + RandomUtility.randomInteger(-1,2);
	        	int newy = y + RandomUtility.randomInteger(-1,2);
	            if (newx > 0 && newx < maxX && newy > 0 && newy < maxY)
	            	raisePoint(map, newx, newy, prob);
	        }
	    }

    public static ElevationMap createRandomElevationDataMap(int width,
            int height) {
        return new BaseElevationMap(width, height, createRandomElevationMap(
                width, height));
    }
}