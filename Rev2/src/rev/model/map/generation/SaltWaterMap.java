package rev.model.map.generation;

/**
 * Precursor data map that identifies water tiles with sea access (salt water)
 * or those completely enclosed by the land tiles (fresh water). Used to compute
 * base fertility.
 * 
 *
 * 
 */
public interface SaltWaterMap extends PrecursorDataMap {

    /**
     * Returns whether the specified tile is salt water.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if salt water, false otherwise.
     */
    public boolean isSaltWater(int x, int y);

    /**
     * Returns whether the specified tile is fresh water.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if fresh water, false otherwise.
     * 
     */
    public boolean isFreshWater(int x, int y);

    /**
     * Returns whether the specified tile is land.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return true if land, false otherwise.
     */
    public boolean isLand(int x, int y);

    public static class SaltWaterInfo implements SaltWaterMap {
        /**
         * Implementation of salt map
         * 
         * @author laurencegoldinger
         * 
         */

        public SaltWaterInfo(ElevationMap elevationMap) {
            map = new int[elevationMap.getWidth()][elevationMap.getHeight()];
            setup(elevationMap);
        }

        private void setup(ElevationMap elevationMap) {
            // First set all land to land tiles and all water tiles to fresh
            // water
            for (int i = 0; i < getWidth(); i++) {
                for (int j = 0; j < getHeight(); j++) {
                    if (elevationMap.isWater(i, j)) {
                        map[i][j] = 0;
                    } else {
                        map[i][j] = -1;
                    }
                }
            }
            // Now set the outer boundary water tiles to salt water
            for (int i = 0; i < map.length; i++) {
                if (map[i][0] == 0) {
                    map[i][0] = 1;
                } else if (map[i][getHeight() - 1] == 0) {
                    map[i][getHeight() - 1] = 1;
                }
            }
            for (int i = 0; i < map.length; i++) {
                if (map[0][i] == 0) {
                    map[0][i] = 1;
                } else if (map[getWidth() - 1][i] == 0) {
                    map[getWidth() - 1][i] = 1;
                }
            }
            // Now "infect" the reachable water tiles from fresh to salt
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = 0; i < getWidth(); i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        if (map[i][j] == 1) {
                            if (i < getWidth()) {
                                if (map[i + 1][j] == 0) {
                                    map[i + 1][j] = 1;
                                    changed = true;
                                }
                            }
                            if (i > 0) {
                                if (map[i - 1][j] == 0) {
                                    map[i - 1][j] = 1;
                                    changed = true;
                                }
                            }
                            if (j < getHeight()) {
                                if (map[i][j + 1] == 0) {
                                    map[i][j + 1] = 1;
                                    changed = true;
                                }
                            }
                            if (j > 0) {
                                if (map[i][j - 1] == 0) {
                                    map[i][j - 1] = 1;
                                    changed = true;
                                }
                            }
                        }

                    }
                }

            }
        }

        private int map[][];

        @Override
        public boolean isSaltWater(int x, int y) {
            return map[x][y] == 1;
        }

        @Override
        public boolean isFreshWater(int x, int y) {
            return map[x][y] == 0;
        }

        @Override
        public boolean isLand(int x, int y) {
            return map[x][y] == -1;
        }

        @Override
        public int getWidth() {
            return map.length;
        }

        @Override
        public int getHeight() {
            return map[0].length;
        }

    }

}
