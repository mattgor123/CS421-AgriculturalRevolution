package rev.model.map.generation;

/**
 * An elevation data map.
 * 
 *
 * 
 */
public class BaseElevationMap implements ElevationMap {

    /**
     * The map width, in tiles.
     * 
     */
    private final int width;

    /**
     * The map height, in tiles.
     * 
     */
    private final int height;

    /**
     * The elevation data.
     * 
     */
    private final int[][] elevation;

    /**
     * Initializes the elevation map data.
     * 
     * @param width
     *            the map width, in tiles.
     * @param height
     *            the map height, in tiles.
     * @param elevation
     *            the map data.
     */
    public BaseElevationMap(int width, int height, int[][] elevation) {
        this.width = width;
        this.height = height;
        this.elevation = elevation;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getElevation(int x, int y) {
        return this.elevation[x][y];
    }

    @Override
    public boolean isLand(int x, int y) {
        return this.elevation[x][y] > 0;
    }

    @Override
    public boolean isWater(int x, int y) {
        return !this.isLand(x, y);
    }

}