package rev.model.properties;

import rev.model.data.TileOccupiedException;

/**
 * An entity with a mutable position.
 * 
 *
 * 
 */
public interface Movable extends Positioned {

    /**
     * Moves the entity to the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if the operation could be performed, false otherwise.
     */
    public Boolean moveTo(Integer x, Integer y) throws TileOccupiedException;
}