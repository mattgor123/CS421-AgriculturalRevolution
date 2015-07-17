package rev.model.map.generation;

/**
 * Develops salt water maps over elevation maps.
 * 
 *
 * 
 */
public class SaltWaterMapDeveloper {

    /**
     * Produces a salt water map for the specified elevation map.
     * 
     * @param elevationMap
     *            the elevation map.
     * @return the salt water map.
     */
    public static SaltWaterMap developSaltWaterMap(ElevationMap elevationMap) {

        /*
         * All water tiles on the edge are made salt water by default. Any tiles
         * those touch become salt water, and so on. Identify salt water with 1,
         * fresh water with 0, and land with -1.
         */

        return new BaseSaltWaterMap(elevationMap);
    }
}