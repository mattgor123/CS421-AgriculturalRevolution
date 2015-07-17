package rev.model.units.core;

/**
 * Manages unit operations on the map.
 * 
 *
 * 
 */
public interface UnitsManager {

    /**
     * Returns the unit at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the unit, if one exists.
     */
    public Unit getUnit(Integer x, Integer y);

    /**
     * Returns whether the specified tile is occupied by a unit.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if the tile is occupied, false otherwise.
     */
    public Boolean isOccupied(Integer x, Integer y);
}