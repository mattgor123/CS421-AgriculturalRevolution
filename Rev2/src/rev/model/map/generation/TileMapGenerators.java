package rev.model.map.generation;

/**
 * The set of tile map generators.
 * 
 *
 * 
 */
public class TileMapGenerators {

    /**
     * The random map generator.
     * 
     */
    private static final TileMapGenerator RANDOM_GENERATOR = new BaseMapGenerator();

    /**
     * Returns a random tile map generator.
     * 
     * @return the random tile map generator.
     */
    public static TileMapGenerator getBaseTileMapGenerator() {
        return RANDOM_GENERATOR;
    }
}