package rev.model.map.generation;

/**
 * An elevation map.
 * 
 *
 * 
 */
public interface ElevationMap extends PrecursorDataMap {

    /**
     * Returns the elevation at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the elevation.
     */
    public int getElevation(int x, int y);

    /**
     * Returns whether the specified tile is land.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if land, false otherwise.
     */
    public boolean isLand(int x, int y);

    /**
     * Returns whether the specified tile is water.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if water, false otherwise.
     */
    public boolean isWater(int x, int y);
}