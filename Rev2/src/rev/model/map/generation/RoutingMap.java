package rev.model.map.generation;

import java.util.Arrays;

import rev.model.data.Motion;

/**
 * Provides fast access for finding paths to land from sea and to sea from land
 * (i.e. pirate behavior).
 * 
 *
 * 
 */
public interface RoutingMap extends PrecursorDataMap {

    /**
     * Returns the fastest direction to sea at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the direction.
     */
    public Motion directionToSea(int x, int y);

    /**
     * Returns the fastest direction to land at the specified tile.
     * 
     * @param x
     *            the x index of the tile.
     * @param y
     *            the y index of the tile.
     * @return the direction.
     */
    public Motion directionToLand(int x, int y);

    public static class RoutingInfo implements RoutingMap {
        private Motion toSeaMap[][];
        private Motion fromSeaMap[][];

        public RoutingInfo(SaltWaterMap map) {
            toSeaMap = new Motion[map.getWidth()][map.getHeight()];
            fromSeaMap = new Motion[map.getWidth()][map.getHeight()];
            setup(map);
        }

        @Override
        public int getWidth() {
            return toSeaMap.length;
        }

        @Override
        public int getHeight() {
            return toSeaMap[0].length;
        }

        private void setup(SaltWaterMap map) {
            routeFromSea(map);
            routeToSea(map);

        }

        private void routeToSea(SaltWaterMap map) {
            // Find all salt water next to a land tile set its distance to 0
            Integer tempMap[][] = new Integer[getWidth()][getHeight()];
            for (Integer[] row : tempMap) {
                Arrays.fill(row, null);
            }

            for (int i = 0; i < getWidth(); i++) {
                for (int j = 0; j < getHeight(); j++) {
                    if (map.isSaltWater(i, j)) {
                        if ((i > 0 && map.isLand(i - 1, j))
                                || (i < getWidth() && map.isLand(i + 1, j))
                                || (j > 0 && map.isLand(i, j - 1))
                                || (j < getHeight() && map.isLand(i, j + 1))) {
                            tempMap[i][j] = 0;
                        }
                    }
                }
            }
            distanceAndDirect(map, tempMap, toSeaMap);

        }

        private void distanceAndDirect(SaltWaterMap map, Integer[][] tempMap,
                Motion[][] operativeMap) {
            // Now "infect" the surrounding sea water increasing the distance
            // (but choosing the smallest distance if choosing between two
            // options
            boolean changed = true;
            while (changed) {
                changed = false;
                for (int i = 0; i < getWidth(); i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        if (tempMap[i][j] != null) {
                            if (i > 0 && map.isSaltWater(i - 1, j)) {
                                if (tempMap[i - 1][j] == null
                                        || tempMap[i - 1][j] > tempMap[i][j] + 1) {
                                    tempMap[i - 1][j] = tempMap[i][j] + 1;
                                    changed = true;
                                }
                            }
                            if (i < getWidth() && map.isSaltWater(i + 1, j)) {
                                if (tempMap[i + 1][j] == null
                                        || tempMap[i + 1][j] > tempMap[i][j] + 1) {
                                    tempMap[i + 1][j] = tempMap[i][j] + 1;
                                    changed = true;
                                }
                            }
                            if (j > 0 && map.isSaltWater(i, j - 1)) {
                                if (tempMap[i][j - 1] == null
                                        || tempMap[i][j - 1] > tempMap[i][j] + 1) {
                                    tempMap[i][j - 1] = tempMap[i][j] + 1;
                                    changed = true;
                                }
                            }
                            if (j < getHeight() && map.isSaltWater(i, j + 1)) {
                                if (tempMap[i][j + 1] == null
                                        || tempMap[i][j + 1] > tempMap[i][j] + 1) {
                                    tempMap[i][j + 1] = tempMap[i][j] + 1;
                                    changed = true;
                                }
                            }
                        }

                    }
                }
            }
            // Now turn the numbers into directions
            for (Motion[] row : operativeMap) {
                Arrays.fill(row, null);
            }
            changed = true;
            int distance = 0;
            while (changed) {
                for (int i = 0; i < getWidth(); i++) {
                    for (int j = 0; j < getHeight(); j++) {
                        if (tempMap[i][j] == distance) {
                            if (i < getWidth()
                                    && tempMap[i + 1][j] == distance + 1) {
                                operativeMap[i][j] = Motion.EAST;
                            } else if (i > 0
                                    && tempMap[i - 1][j] == distance + 1) {
                                operativeMap[i][j] = Motion.WEST;
                            } else if (j > 0
                                    && tempMap[i][j - 1] == distance + 1) {
                                operativeMap[i][j] = Motion.SOUTH;
                            } else {
                                operativeMap[i][j] = Motion.NORTH;
                            }
                        }
                    }
                }
                distance++;
            }
        }

        private void routeFromSea(SaltWaterMap map) {
            // First find all water tiles on the edge of the map give them a
            // distance of 0
            Integer tempMap[][] = new Integer[getWidth()][getHeight()];
            for (Integer[] row : tempMap) {
                Arrays.fill(row, null);
            }
            for (int i = 0; i < this.getWidth(); i++) {
                if (map.isSaltWater(i, 0)) {
                    tempMap[i][0] = 0;
                }
                if (map.isSaltWater(i, this.getHeight() - 1)) {
                    tempMap[i][getHeight() - 1] = 0;
                }
            }
            for (int i = 0; i < this.getHeight(); i++) {
                if (map.isSaltWater(0, i)) {
                    tempMap[0][i] = 0;
                }
                if (map.isSaltWater(this.getWidth() - 1, i)) {
                    tempMap[getWidth() - 1][i] = 0;
                }
            }
            distanceAndDirect(map, tempMap, fromSeaMap);
        }

        @Override
        public Motion directionToSea(int x, int y) {
            return toSeaMap[x][y];
        }

        @Override
        public Motion directionToLand(int x, int y) {
            return fromSeaMap[x][y];
        }

    }
}