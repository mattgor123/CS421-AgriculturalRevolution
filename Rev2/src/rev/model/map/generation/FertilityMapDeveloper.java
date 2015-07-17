package rev.model.map.generation;

/**
 * Produces fertility maps.
 * 
 *
 * 
 */
public class FertilityMapDeveloper {

    /**
     * Develops a fertility map, given an elevation and salt water map.
     * 
     * @param elevationMap
     *            the elevation map.
     * @param saltWaterMap
     *            the salt water map.
     * @return the fertility map.
     */
    public static FertilityMap developFertilityMap(ElevationMap elevationMap,
            SaltWaterMap saltWaterMap) {

        /*
         * Develops a fertility map, given an elevation and salt water map.
         * Proximity to salt water and high elevation penalize fertility.
         * Proximity to fresh water and lower elevation is good for fertility.
         */

        // RETURNS NULL IF ON water
        return new BaseFertilityMap(elevationMap, saltWaterMap);
    }
}