package rev.model.map.generation;

/**
 * A precursor data map is a "layer" of the game map. Examples of precursor maps
 * include elevation maps and fertility map. Precursor maps are developed from
 * other precursor, and eventually combined to form a game map.
 * 
 *
 * 
 */
public interface PrecursorDataMap {

    /**
     * The width of the data map, in tiles.
     * 
     * @return the width.
     */
    public int getWidth();

    /**
     * The height of the data map, in tiles.
     * 
     * @return the height.
     */
    public int getHeight();
}