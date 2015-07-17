package rev.model.map.generation;

/**
 * Generates a salt water map, given an elevation map as input.
 * 
 *
 * 
 */
public class BaseSaltWaterMap implements SaltWaterMap {
    /**
     * Implementation of salt map
     * 
     * @author laurencegoldinger
     * 
     */

    public BaseSaltWaterMap(ElevationMap elevationMap) {
        map = new int[elevationMap.getWidth()][elevationMap.getHeight()];
        setup(elevationMap);
    }

    private void setup(ElevationMap elevationMap) {
        // First set all land to land tiles and all water tiles to fresh water
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
                        if (i < getWidth() - 1) {
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
                        if (j < getHeight() - 1) {
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

        if (x >= 0 && x < getWidth() && y >= 0 && y < getHeight()) {
            return map[x][y] == -1;
        } else {
            return false;
        }
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