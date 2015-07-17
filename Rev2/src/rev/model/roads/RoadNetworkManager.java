package rev.model.roads;

import rev.model.map.core.Tile;
import rev.model.settlement.core.Settlement;

/**
 * Manages the road network of the model.
 * 
 *
 * 
 */
public interface RoadNetworkManager {

    /**
     * The price of a road.
     * 
     */
    public static final int ROAD_PRICE = 100;

    /**
     * Places a road between the specified tiles.
     * 
     * @param tile
     *            the tile.
     */
    public Boolean placeRoad(Tile tile);

    /**
     * Places a road that must be paid for.
     * 
     * @param tile
     *            the tile on which to place the road.
     * @param settlement
     *            the settlement paying for the road.
     */
    public Boolean placePaidRoad(Tile tile, Settlement settlement);

    /**
     * Returns whether the specified tile has a road.
     * 
     * @param tile
     *            the tile.
     * @return true if the tile has a road, false otherwise.
     */
    public Boolean hasRoad(Tile tile);

    /**
     * Returns whether a usable road exists between two tiles.
     * 
     * @param t1
     *            the first tile.
     * @param t2
     *            the second tile.
     * @return true if a usable road exists, false otherwise.
     */
    public Boolean usableRoadExistsBetween(Tile t1, Tile t2);
}