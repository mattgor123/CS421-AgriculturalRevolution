package rev.model.map.generation;

import rev.model.map.core.BaseTileMap;
import rev.model.map.core.BaseLandTile;
import rev.model.map.core.Tile;
import rev.model.map.core.TileMap;
import rev.model.map.core.BaseWaterTile;

/**
 * Generates a random tile map.
 * 
 *
 * 
 */
class BaseMapGenerator implements TileMapGenerator {

    @Override
    public TileMap generateTileMap(int width, int height) {
        ElevationMap elevationMap = ElevationMapGenerator
                .createRandomElevationDataMap(width, height);
        SaltWaterMap saltWaterMap = SaltWaterMapDeveloper
                .developSaltWaterMap(elevationMap);
        FertilityMap fertilityMap = FertilityMapDeveloper.developFertilityMap(
                elevationMap, saltWaterMap);
        Tile[][] tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new BaseWaterTile(x, y);
            }
        }
        for (int x = 4; x < width - 4; x++) {
            for (int y = 4; y < height - 4; y++) {
                if (elevationMap.isLand(x, y)) {
                    tiles[x][y] = new BaseLandTile(x, y,
                            elevationMap.getElevation(x, y),
                            fertilityMap.getFertility(x, y));
                }
            }
        }
        return new BaseTileMap(width, height, tiles);
    }

}