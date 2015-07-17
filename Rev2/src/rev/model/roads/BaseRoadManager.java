package rev.model.roads;

import rev.model.core.BaseModelDelegate;
import rev.model.map.core.Tile;
import rev.model.map.core.TileMap;
import rev.model.settlement.core.Settlement;

public class BaseRoadManager implements RoadNetworkManager {

    /**
     * The set of placed roads.
     * 
     */
    private Road[][] roads;

    private final int width;

    private final int height;

    private final BaseModelDelegate model;

    /**
     * Initializes the manager.
     * 
     * @param model
     *            the model.
     */
    public BaseRoadManager(BaseModelDelegate model) {
        this.model = model;
        this.width = model.getTileMap().getWidth();
        this.height = model.getTileMap().getHeight();
        this.roads = new Road[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                this.roads[i][j] = null;
            }
        }
    }

    @Override
    public Boolean placeRoad(Tile tile) {

        if (this.hasRoad(tile)) {
            return false;
        }
        this.roads[tile.getX()][tile.getY()] = new BaseRoad(tile.getX(),
                tile.getY());
        return true;
    }

    @Override
    public Boolean usableRoadExistsBetween(Tile t1, Tile t2) {
        return (this.roadUsable(t1.getX(), t1.getY()) && this.roadUsable(
                t2.getX(), t2.getY()));
    }

    /**
     * Returns whether a usable road exists at the specified index.
     * 
     * @param x
     *            the x index of the road.
     * @param y
     *            the y index of the road.
     * @return true if a usable road exists, false otherwise.
     */
    private Boolean roadUsable(int x, int y) {
        Road road = this.roads[x][y];
        if (road != null) {
            return road.isUsable();
        }
        return false;
    }

    @Override
    public Boolean hasRoad(Tile tile) {
        TileMap map = this.model.getTileMap();
        if (tile.getX() < 0 || tile.getX() >= map.getWidth() || tile.getY() < 0
                || tile.getY() >= map.getHeight()) {
            return false;
        }
        return this.roads[tile.getX()][tile.getY()] != null;
    }

    @Override
    public Boolean placePaidRoad(Tile tile, Settlement settlement) {
        int x = tile.getX();
        int y = tile.getY();
        TileMap map = this.model.getTileMap();
        if (!(this.hasRoad(map.getTile(x + 1, y))
                || this.hasRoad(map.getTile(x - 1, y))
                || this.hasRoad(map.getTile(x, y + 1)) || this.hasRoad(map
                .getTile(x, y - 1)))) {
            return false;
        }

        if (settlement.getStorageOperation().getQuantity() >= RoadNetworkManager.ROAD_PRICE) {
            boolean result = this.placeRoad(tile);
            if (result) {
                settlement.getStorageOperation().withdrawQuantity(
                        RoadNetworkManager.ROAD_PRICE);
            }
            return result;
        }
        return false;
    }
}