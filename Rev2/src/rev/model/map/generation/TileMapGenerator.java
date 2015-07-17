package rev.model.map.generation;

import rev.model.map.core.TileMap;

/**
 * Generates tile map with specified size.
 * 
 *
 * 
 */
public interface TileMapGenerator {

    /**
     * Generates a tile map.
     * 
     * @param width
     *            the width of the map, in tiles.
     * @param height
     *            the height of the map, in tiles.
     * @return
     */
    public TileMap generateTileMap(int width, int height);
}