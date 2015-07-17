package rev.model.map.core;

/**
 * The tile map.
 * 
 *
 * 
 */
public interface TileMap extends Iterable<Tile> {

    /**
     * Returns the tile at the specified index.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the tile.
     */
    public Tile getTile(int x, int y);

    /**
     * Returns the width of the tile map, in tiles.
     * 
     * @return the width of the map.
     */
    public int getWidth();

    /**
     * Returns the height of the tile map, in tiles.
     * 
     * @return the height of the map.
     */
    public int getHeight();
}