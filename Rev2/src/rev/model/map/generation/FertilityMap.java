package rev.model.map.generation;

/**
 * A fertility map, mapping agricultural potential to tiles.
 * 
 *
 * 
 */
public interface FertilityMap extends PrecursorDataMap {

    /**
     * Returns the fertility at a specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the fertility.
     */
    public int getFertility(int x, int y);
}